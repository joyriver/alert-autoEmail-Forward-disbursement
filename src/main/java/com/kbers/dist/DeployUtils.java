package com.kbers.dist;

import com.kbers.common.Application;
import com.amzass.dist.Extension;
import com.kbers.dist.I18N;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 * 部署工具类
 * @author <a href="mailto:nathanael4ever@gmail.com>Nathanael Yang</a> Jan 12, 2015 1:29:47 PM
 */
public class DeployUtils {
	static final String SLASH = "/";
	
	private static String[] TIME_UNITS = {"h", "m", "s", "ms"};
	private static long[] TIME_RANGES = {1000 * 60 * 60, 1000 * 60, 1000};
	private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("0.00");

    static String defaultString(String src, String defaultValue) {
        return src == null || src.length() == 0 || src.trim().length() == 0 ? defaultValue : src;
    }

	public static void sleep(long time, TimeUnit timeunit) {
		try {
			timeunit.sleep(time);
		} catch (InterruptedException e) {
			// -> Ignore
		}
	}

    /**
     * Determine whether a program under a certain path is running or not
     * @param fullPath      Complete file path, since the program may open multiple instances
     */
	public static boolean isProcessRunning(String fullPath) {
		BufferedReader input = null;
		boolean running = false;
		try {
		    String processName = FilenameUtils.getName(fullPath);
			Process process = Runtime.getRuntime().exec("wmic process where \"name='" + processName + "'\" get ExecutablePath");
			input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
            while ((line = input.readLine()) != null) {
                if (startsWithAny(line.trim(), fullPath)) {
                    running = true;
                    break;
                }
            }
        } catch (IOException e) {
            // -> Ignore
        } finally {
			IOUtils.closeQuietly(input);
		}
		return running;
	}

    /**
     * <pre>
     * Get default application directory, typically will be ${root}/${applicationName}
     * However, if there is programming files found in current directory, then the default directory
     * will be set to current directory, this can be useful when there are multiple instances deployed.
     * </pre>
     */
	static String getDefaultDirectory(Application application) {
	    // Assumption: if main program, driver folder and document folder exist, it has been deployed before
	    String[] keyPaths = {Extension.EXE.append(application.name()), "Drivers", "document"};
	    boolean existed = true;
	    for (String path : keyPaths) {
            if (!new File(path).exists()) {
                existed = false;
                break;
            }
        }
	    if (existed) {
	        return new File("").getAbsolutePath();
        }

		String dirWin = "C:" + File.separator + application.name();
		String dirLinux = System.getProperty("user.home") + File.separator + application.name();
		return File.separator.equals("/") ? dirLinux : dirWin;
	}
	
	public static boolean containsAny(String src, String... targets) {
		if (src == null || targets == null || targets.length == 0) {
			return false;
		}
		for (String target : targets) {
			if (src.toUpperCase().contains(target.toUpperCase())) {
				return true;
			}
		}
		return false;
	}
	
	static boolean startsWithAny(String src, String...targets) {
		if (src == null || targets == null || targets.length == 0) {
			return false;
		}
		for (String target : targets) {
			if (src.toUpperCase().startsWith(target.toUpperCase())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 格式化消耗时间
	 * @param cost	消耗时间
	 * @return		格式化好的消耗时间
	 */
	public static String formatTime(long cost) {
		double result = cost;
		for (int i = 0; i < TIME_RANGES.length; i++) {
			if (cost >= TIME_RANGES[i]) {
				result /= TIME_RANGES[i];
				return I18N.text("label.timeunit." + TIME_UNITS[i], DOUBLE_FORMAT.format(result));
			}
		}

		return I18N.text("label.timeunit.ms", cost);
	}
	
	/**
	 * 获取异常消息，如果没有有效消息，则直接返回异常类名
	 * @param e		异常
	 */
	static String getExceptionMsg(Throwable e) {
		String msg = e.getMessage();
		return msg != null && msg.length() > 0 ? msg : e.getClass().getCanonicalName();
	}

    /**
     * 设置UI显示风格为Windows
     */
	static void setTheme() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// -> Ignore
		}
	}
	
	/**
	 * 设置图标和居中定位
	 */
	static void centered(Window window) {
		int locationY = (GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height - window.getHeight()) / 2;
		if (locationY < 0) {
			locationY = 0;
		}
		window.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - window.getWidth()) / 2, locationY);
	}
	
	public static void error(String msg) {
		message(msg, I18N.text("title.error.program"), JOptionPane.ERROR_MESSAGE);
	}
	
	static void info(String msg) {
		message(msg, I18N.text("title.result"), JOptionPane.INFORMATION_MESSAGE);
	}
	
	private static void message(String msg, String title, int type) {
		JOptionPane.showMessageDialog(null, msg, title, type);
	}
}
