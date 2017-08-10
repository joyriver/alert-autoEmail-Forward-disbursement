package com.kbers.dist;

import com.kbers.common.Application;
import com.kbers.common.I18NHelper;
import com.kbers.dist.DeployMode;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.amzass.dist.Extension;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 * Deployment Program for OrderMan, MailMan and WatchDog
 * It will download programming files in dropbox root folder to local installation folder
 * </pre>
 * @author <a href="mailto:nathanael4ever@gmail.com>Nathanael Yang</a> Nov 13, 2014 2:31:36 PM
 */
public class DeployMan extends JDialog {
	private static final long serialVersionUID = 51130642679011360L;
	private static final Logger LOGGER = LogManager.getLogger(com.amzass.dist.DeployMan.class.getName());
	private static final String BACKUP = "/bak", BETA = "BETA";
	private final Application application;
	private final DeployMode deployMode;
	private final DropboxAssistant dropboxAssistant;
	private String destDir;
	private int threadCount = 10;

	public DeployMan(DeployMode deployMode, Application application, String initDir) {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(com.amzass.dist.DeployMan.class.getResource("/icon/deploy.png")));
		this.setTitle(I18N.text("title.deployman", application));
		this.application = application;
		this.deployMode = deployMode;
		this.dropboxAssistant = new DropboxAssistant(application);
		this.destDir = initDir;
		this.fileChooser = new JFileChooser(destDir);

		this.initComponents();
		DeployUtils.centered(this);
		this.setVisible(true);

		LOGGER.info(I18N.text("message.start"));
		LOGGER.debug("DeployMan for Application " + application + " Initialized Successfully.");
	}

	public DeployMan(DeployMode deployMode, Application application, String initDir, int threadCount) {
		this(deployMode, application, initDir);
		if (threadCount > 0) {
			this.threadCount = threadCount;
		}
	}

	private void switchStatus(boolean enabled) {
		installBtn.setEnabled(enabled);
		deltaUpdateBtn.setEnabled(enabled);
		betaBtn.setEnabled(enabled);
        instDirTxt.setEnabled(enabled);
	}

    /**
     * 部署重复最大次数, 目前Dropbox并发请求数量逐步增加, 设定重复直到{@value}次
     */
	private static final int ATTEMPT_TIMES = 4;

	private void startDeployIgnoringException(boolean delta, boolean beta) {
	    String errorMessage = null;
        for (int i = 0; i < ATTEMPT_TIMES; i++) {
            try {
                this.startDeploy(delta, beta);
                errorMessage = null;
                break;
            } catch (DbxException e) {
                LOGGER.error("第" + (i + 1) + "次部署" + application.name() + "失败, 尝试等待若干时间后重复部署:", e);
                errorMessage = DeployUtils.getExceptionMsg(e);
                logArea.append(I18N.text("message.fail.dbx", application.name(), e.getMessage()) + NEW_LINE);
                if (i < ATTEMPT_TIMES - 1) {
                    if (DeployUtils.containsAny(e.getMessage(), "Too Many Requests")) {
                        DeployUtils.sleep(32 + new Random().nextInt(64), TimeUnit.SECONDS);
                    } else {
                        DeployUtils.sleep(7, TimeUnit.SECONDS);
                    }
                }
            }
        }

        if (errorMessage != null) {
            DeployUtils.error(I18N.text("message.error.dropbox", errorMessage));
            this.switchStatus(true);
        }
    }

	/**
	 * Start to deploy programming files
	 * @param delta		in delta mode or not
	 * @param beta		fetch beta version or not
	 */
	private void startDeploy(boolean delta, boolean beta) throws DbxException {
		destDir = instDirTxt.getText().trim();
		if (deployMode == DeployMode.StandAlone) {
			try {
				Locale locale = I18NHelper.getCurrentLocale();
				String[] options = {UIManager.getString("OptionPane.yesButtonText", locale), UIManager.getString("OptionPane.noButtonText", locale)};
                String path = new File(destDir, Extension.EXE.append(application.name())).getAbsolutePath();
                boolean running = DeployUtils.isProcessRunning(path);
				if (running && JOptionPane.showOptionDialog(null, I18N.text("warning.application.running", path), I18N.text("title.confirm"), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]) != JOptionPane.YES_OPTION) {
					return;
				}
			} catch (Exception e) {
				DeployUtils.error(DeployUtils.getExceptionMsg(e));
			}
		}

		final long start = System.currentTimeMillis();
		switchStatus(false);
		logArea.setText(null);
		progressBar.setString(null);
		progressBar.setValue(0);
		failedRecords.clear();
		this.backup();

		if (beta) {
			dropboxAssistant.setRootPath(DeployUtils.SLASH + BETA + DeployUtils.SLASH + application.name());
		} else {
			dropboxAssistant.setRootPath(DeployUtils.SLASH + application.name());
		}
		List<DbxEntry.File> files = dropboxAssistant.getAllFilesUnderRootPath(Extension.ALL);
		if (files == null || files.size() == 0) {
			logArea.setText(I18N.text("warn.files.empty", application));
			switchStatus(true);
			return;
		}

		dropboxAssistant.filter(files, delta);
		long sum = dropboxAssistant.sumFileSize(files);

		final int total = files.size();
		count.set(total);
		processed.set(0);
		LOGGER.info(String.format("Successfully read %s files in %s MS", total, System.currentTimeMillis() - start));

		final CountDownLatch latch = new CountDownLatch(threadCount);
		List<DbxEntry.File> list4Task = new ArrayList<>();
		long average = sum / threadCount, sum4Task = 0L;
		LOGGER.info(String.format("There are %s files, %s KB, each task will be assigned %s KB", total, sum / 1024, average / 1024));
		int taskCount = threadCount, _count = 0, number = 0;
		for (DbxEntry.File file : files) {
			sum4Task += file.numBytes;
			list4Task.add(file);

			if (sum4Task >= average) {
				new DropboxWorker(++number, latch, dropboxAssistant, new ArrayList<>(list4Task)).execute();
				taskCount--;
				_count += list4Task.size();
				LOGGER.info(String.format("Current worker will take %s files, %s KB, %s workers available", list4Task.size(), sum4Task / 1024, taskCount));
				list4Task.clear();
				sum4Task = 0L;
			}
		}

		if (taskCount > 0 && list4Task.size() > 0) {
			new DropboxWorker(++number, latch, dropboxAssistant, list4Task).execute();
			taskCount--;
			_count += list4Task.size();
		}
		LOGGER.info(String.format("There are %s files, %s of them processed, %s Worker idle", count, _count, taskCount));
		for (int i = 0; i < taskCount; i++) {
			latch.countDown();
		}

		SwingWorker<Void, String> hook = new SwingWorker<Void, String>() {
			@Override
			protected Void doInBackground() throws Exception {
				latch.await();
				LOGGER.info(String.format("Deployment finished processing %s files in %s S", total, (System.currentTimeMillis() - start) / 1000));
				if (!failedRecords.isEmpty()) {
					StringBuilder sb = new StringBuilder();
					int i = 0;
					for (String s : failedRecords) {
						sb.append(i++ == 0 ? "" : "\n").append(s);
					}
					DeployUtils.error(I18N.text("message.error.repeat", sb.toString()));
					failedRecords.clear();
				} else if (deployMode.popup()) {
					DeployUtils.info(deployMode.msg(start));
					if (!new File("Customize", "debugOptions.json").exists()) {
						if (deployMode == DeployMode.StandAlone) {
							System.exit(0);
						} else if (deployMode == DeployMode.AfterStart) {
							dispose();
							setVisible(false);
						}
					}
				}
				switchStatus(true);
				return null;
			}
		};

		hook.execute();
		// 阻塞式使用场景：主程序启动检测到需要升级，用户也选择马上升级，此时必须保证升级完成后进行后续操作; 静默快速部署模式亦然
		if (deployMode == DeployMode.SilentStandAlone || deployMode == DeployMode.BeforeStart) {
			try {
				hook.get();
			} catch (InterruptedException | ExecutionException e) {
				//-> Ignore
			}
		}
	}

	private void backup() {
        try {
            File backup = new File(destDir, BACKUP);
            FileUtils.forceMkdir(backup);

            File folder = new File(destDir, "lib");
			if (folder.exists()) {
				FileUtils.copyDirectoryToDirectory(folder, backup);
			}

			String[] executables = {String.format("%s.exe", application.name()), String.format("Deploy %s.exe", application.name())};
			for (String exe: executables) {
				File file = new File(destDir, exe);
				if (!file.exists()) {
					continue;
				}
				FileUtils.copyFileToDirectory(file, backup);
			}

			logArea.append(I18N.text("message.backup.success", backup.getAbsolutePath(), destDir) + NEW_LINE);
		} catch (IOException ex) {
			logArea.append(I18N.text("message.backup.fail", DeployUtils.getExceptionMsg(ex)) + NEW_LINE);
		}
	}

    private void initComponents() {
    	this.setResizable(false);
    	JScrollPane scrollPane = new JScrollPane();
        logArea = new JTextArea();
        logArea.setLineWrap(true);
        logArea.setEditable(false);
        installBtn = new JButton(I18N.text("label.deploy.complete"));
        installBtn.setToolTipText(I18N.text("tooltip.deploy.complete"));

        betaBtn = new JButton(I18N.text("label.deploy.beta"));
        betaBtn.setToolTipText(I18N.text("tooltip.deploy.beta"));

        deltaUpdateBtn = new JButton(I18N.text("label.deploy.delta"));
        deltaUpdateBtn.setToolTipText(I18N.text("tooltip.deploy.delta"));

        URL resource = com.amzass.dist.DeployMan.class.getResource("/icon/full.png");
        if (resource != null) {
        	installBtn.setIcon(new ImageIcon(resource));
        	betaBtn.setIcon(new ImageIcon(resource));
        }

        URL resourceDelta = com.amzass.dist.DeployMan.class.getResource("/icon/delta.png");
        if (resourceDelta != null) {
        	deltaUpdateBtn.setIcon(new ImageIcon(resourceDelta));
        }

        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
		progressBar.setMaximum(100);
        progressBar.setStringPainted(true);

        final boolean standAlone = deployMode == DeployMode.StandAlone || deployMode == DeployMode.SilentStandAlone;
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				LOGGER.info(I18N.text("message.close"));
				if (standAlone) {
					System.exit(0);
				} else {
					dispose();
					setVisible(false);
				}
			}
		});
        logArea.setColumns(20);
        logArea.setRows(5);
        scrollPane.setViewportView(logArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        installBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				startDeployIgnoringException(false, false);
            }
        });
        betaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				startDeployIgnoringException(false, true);
            }
        });
        deltaUpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startDeployIgnoringException(true, false);
			}
		});

        JLabel label = new JLabel(I18N.text("label.choose.dir"));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle(I18N.text("title.choose.dir"));

        instDirTxt = new JTextField(destDir);
        instDirTxt.setToolTipText(I18N.text("title.choose.dir"));
        instDirTxt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        instDirTxt.setColumns(10);

        Font font = label.getFont();
        logArea.setFont(new Font(font.getName(), font.getStyle(), 12));

        instDirTxt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int code = fileChooser.showOpenDialog(null);
				if (code == JFileChooser.APPROVE_OPTION) {
					File config = fileChooser.getSelectedFile();
					instDirTxt.setText(config.getAbsolutePath());
				}
			}
		});

        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        		.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(label)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(instDirTxt, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(installBtn)
        			.addComponent(deltaUpdateBtn)
        			.addComponent(betaBtn))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
    					.addComponent(deltaUpdateBtn).addComponent(installBtn)
        				.addComponent(betaBtn).addComponent(label)
        				.addComponent(instDirTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);
        getRootPane().setDefaultButton(installBtn);
        pack();
    }

    private static final String NEW_LINE = "\r\n";
    private AtomicInteger count = new AtomicInteger(0);
    private AtomicInteger processed = new AtomicInteger(0);
    private List<String> failedRecords = new CopyOnWriteArrayList<>();
    /**
     * Dropbox Worker that will be responsible for downloading files
     * @author <a href="mailto:nathanael4ever@gmail.com>Nathanael Yang</a> Nov 14, 2014 8:29:21 AM
     */
    private class DropboxWorker extends SwingWorker<Void, String> {
		DropboxWorker(int number, CountDownLatch latch, DropboxAssistant dropboxAssistant, List<DbxEntry.File> files) {
			super();
			this.number = number;
			this.latch = latch;
			this.dropboxAssistant = dropboxAssistant;
			this.files = files;
		}

		private final int number;
    	private final CountDownLatch latch;
		private final DropboxAssistant dropboxAssistant;
    	private final List<DbxEntry.File> files;

		@Override
		protected Void doInBackground() {
			Thread.currentThread().setName("Downloader" + number);
			LOGGER.info(String.format("Downloader%s will be responsible for downloading %s files", number, files.size()));
			for (DbxEntry.File dbxFile : files) {
				String result;
				try {
					result = dropboxAssistant.downloadFile(dbxFile, destDir);
				} catch (DownloadFailException e) {
					result = I18N.text("message.fail.dbx", dbxFile.path, DeployUtils.getExceptionMsg(e));
					failedRecords.add(result);
				}
				this.publish(result);
				processed.addAndGet(1);
			}
			return null;
		}

		@Override
		protected void done() {
			latch.countDown();
		}

		@Override
		protected void process(List<String> progress) {
			int percentage = processed.get() * 100 / count.get();
			progressBar.setValue(percentage);

			for (String s : progress) {
				logArea.append(s + NEW_LINE);
			}
		}
    }

    /**
	 * 配合应用程序，当程序启动、检测到需要升级时，启动自动静默升级模式，路径使用当前路径
	 * @param mode		部署模式
	 * @param initDir	初始路径
	 */
	public static void deploy(DeployMode mode, Application application, String initDir) {
		final com.kbers.dist.DeployMan deployMan = new com.kbers.dist.DeployMan(mode, application, initDir);
		if (mode == DeployMode.AfterStart) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					deployMan.startDeployIgnoringException(false, false);
				}
			});
		} else {
			deployMan.startDeployIgnoringException(false, false);
		}
	}

	private JButton installBtn;
	private JButton deltaUpdateBtn;
    private JButton betaBtn;
    private JTextArea logArea;
    private JProgressBar progressBar;
    private JTextField instDirTxt;
    private JFileChooser fileChooser;

    /** Indicator: Deploy immediately after start up? Reserved for deployment script */
    private static final String START_DEPLOY_AFTER_STARTUP = "S";

//    String [] args  = {"Application","MailMan"};

    public static void main(String args[]) {
//        System.out.println(args[0]+":"+args.length);
    	if (args == null || args.length == 0) {
    		LOGGER.error("Required parameter is not provided. You must define a certain application type.");
    		System.exit(4);
    	}

    	DeployUtils.setTheme();
    	I18N.getInstance().changeLocale(I18NHelper.getCurrentLocale());
    	Application application = Application.get(args[0].trim());
		if (application == null) {
			LOGGER.error("Illegal application " + args[0]);
    		System.exit(4);
		}

    	long start = System.currentTimeMillis();
		DeployMode mode = DeployUtils.containsAny(START_DEPLOY_AFTER_STARTUP, args) ? DeployMode.SilentStandAlone : DeployMode.StandAlone;
		String defaultInitDir = DeployUtils.getDefaultDirectory(application);
		com.kbers.dist.DeployMan deployMan = new com.kbers.dist.DeployMan(mode, application, defaultInitDir);
		if (mode == DeployMode.SilentStandAlone) {
			try {
				deployMan.startDeploy(false, false);
				LOGGER.info(mode.msg(start));
				System.exit(0);
			} catch (DbxException e) {
				LOGGER.error("Exception occurred in the process of deployment: " + DeployUtils.getExceptionMsg(e));
				System.exit(4);
			}
		}
    }
}
