package com.kbers.dist;

import com.kbers.common.Application;
import com.kbers.dist.DeployUtils;
import com.kbers.dist.DownloadFailException;
import com.kbers.dist.DownloadResult;
import com.kbers.dist.DownloadResult.Status;
import com.amzass.dist.Extension;
import com.dropbox.core.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;



/**
 * Dropbox上传、下载助手
 * @author <a href="mailto:nathanael4ever@gmail.com>Nathanael Yang</a> Nov 20, 2014 4:36:24 PM
 */
public class DropboxAssistant {
	private final Logger LOGGER = LogManager.getLogger(com.kbers.dist.DropboxAssistant.class);
	/**
	 * Access Key: 默认为DeployMan
	 */
	private String accessKey = "TZmKyU0JLtAAAAAAAAAAJmIgYaTTnxNPWJ1HBVqV5ncv8lXBSoAYGHfSdPyQfNBP";
	private final int REPEAT_TIMES = 3;
	private final String appName;
	private String rootPath;
	private String[] overrideList;
	private final DbxClient client;
	/**
	 * 是否将Dropbox文件路径全部转为小写<br>
	 * Dropbox返回文件路径不区分大小写, 在Linux等大小写敏感的操作系统上会导致一些不可预测的问题, 一个简便的解决方案是全部转为小写
	 */
	private final boolean pathToLowercase;
	
	public boolean isPathToLowercase() {
		return pathToLowercase;
	}

	/**
	 * 根据应用名称和Access Key构建Dropbox上传下载助手实例，使用基于最后修改时间判定文件是否没有修改的策略
	 * @param appName		应用名称
	 * @param accessKey		Access Key
	 * @param pathToLowercase	是否将Dropbox文件路径全部转为小写, Dropbox返回文件路径不区分大小写, 在Linux等大小写敏感的操作系统上会导致一些不可预测的问题, 一个简便的解决方案是全部转为小写
	 */
	public DropboxAssistant(String appName, String accessKey, boolean pathToLowercase) {
		this.appName = appName;
		this.accessKey = accessKey;
		this.rootPath = DeployUtils.SLASH;
		client = new DbxClient(new DbxRequestConfig(appName, Locale.US.toString()), accessKey);
		this.pathToLowercase = pathToLowercase;
	}

	public DropboxAssistant(String appName, String accessKey) {
		this(appName, accessKey, false);
	}

	/**
	 * 构建服务于特定应用的Dropbox上传下载助手实例
	 * @param application	当前应用
	 */
	public DropboxAssistant(Application application) {
		this.appName = application.name();
		this.rootPath = DeployUtils.SLASH + appName;

		Application[] applications = Application.values();
		this.overrideList = new String[applications.length + 1];
		for (int i = 0; i < applications.length; i++) {
			overrideList[i] = applications[i].name();
		}
		overrideList[overrideList.length - 1] = "DeployMan";

		client = new DbxClient(new DbxRequestConfig(appName, Locale.US.toString()), accessKey);
		pathToLowercase = false;
	}

	/**
	 * 上传文件到当前应用根目录下面
	 */
	public DbxEntry.File uploadToRootPath(File file) throws DbxException, IOException {
		return upload(file, rootPath);
	}

	/**
	 * 删除指定路径下的文件
	 * @param dbxPath	Dropbox文件路径
	 */
	public void delete(String dbxPath) throws DbxException {
		this.client.delete(dbxPath);
	}

	/**
	 * 上传一个本地文件到Dropbox指定目录下面, 如果上传过程出现异常, 尝试重复3次以内, 每次重复间隔沉睡1秒
	 * @param file			本地文件
	 * @param dbxFolderPath	Dropbox上传目标文件夹路径
	 */
	public DbxEntry.File upload(File file, String dbxFolderPath) throws DbxException, IOException {
        Exception ex = null;
        for (int i = 0; i < REPEAT_TIMES; i++) {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
                String path = dbxFolderPath.endsWith(DeployUtils.SLASH) ? dbxFolderPath : (dbxFolderPath + DeployUtils.SLASH);
                return client.uploadFile(path + file.getName(), DbxWriteMode.add(), file.length(), inputStream);
            } catch (DbxException | IOException e) {
                ex = e;
                if (i < REPEAT_TIMES - 1) {
                    DeployUtils.sleep(REPEAT_TIMES, TimeUnit.SECONDS);
                }
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
        }

        if (ex instanceof DbxException) {
            throw (DbxException) ex;
        } else {
            throw (IOException) ex;
        }
	}

	/**
	 * 获取指定Dropbox路径下的文件
	 * @param path			dropbox文件夹路径
	 * @param extension		文件扩展名，用于过滤
	 * @param recursive		是否递归查找
	 */
	public List<DbxEntry.File> getFiles(String path, com.amzass.dist.Extension extension, boolean recursive) throws DbxException {
		List<DbxEntry.File> files = new ArrayList<>();

		DbxEntry.WithChildren childs = client.getMetadataWithChildren(path);
		if (childs == null || childs.children == null || childs.children.size() == 0) {
			return files;
		}

		for (DbxEntry child : childs.children) {
			if (child.isFolder() && recursive) {
				List<DbxEntry.File> subFiles = getAllFiles(child.path, extension);
				files.addAll(subFiles);
			} else if (child.isFile() && (extension == com.amzass.dist.Extension.ALL || FilenameUtils.getExtension(child.name).equalsIgnoreCase(extension.value()))) {
				files.add(child.asFile());
			}
		}
		return files;
	}

	/**
	 * 过滤掉无需下载的Dropbox文件
	 * @param files		Dropbox文件集合
	 * @param delta		是否启用delta模式，即增量式按需下载
	 */
	public void filter(List<DbxEntry.File> files, boolean delta) {
		for (Iterator<DbxEntry.File> iterator = files.iterator(); iterator.hasNext();) {
			DbxEntry.File file = iterator.next();
			String extension = FilenameUtils.getExtension(file.name);

			if (com.amzass.dist.Extension.VERSION.value().equalsIgnoreCase(extension) ||
				com.amzass.dist.Extension.ShellScript.value().equalsIgnoreCase(extension) ||
				(delta && !overwrite(file.path))) {
				iterator.remove();
			}
		}
	}

	/**
	 * 计算文件大小之和
	 * @param files		文件集合
	 */
	public long sumFileSize(List<DbxEntry.File> files) {
		long sum = 0L;
		for (DbxEntry.File file : files) {
			sum += file.numBytes;
		}
		return sum;
	}

	/**
	 * @see #getAllFiles(String, com.amzass.dist.Extension)
	 */
	public List<DbxEntry.File> getAllFilesUnderRootPath(com.amzass.dist.Extension extension) throws DbxException {
		return getAllFiles(this.rootPath, extension);
	}

	/**
	 * @see #getFiles(String, com.amzass.dist.Extension, boolean)
	 */
	public List<DbxEntry.File> getAllFiles(String path, com.amzass.dist.Extension extension) throws DbxException {
		return getFiles(path, extension, true);
	}

	/**
	 * 过滤掉稳定、很少改动的文件，比如依赖的第三方jar包等等
	 * @param files		dropbox文件集合
	 */
	public void filterStableFiles(List<DbxEntry.File> files) {
		for (Iterator<DbxEntry.File> iterator = files.iterator(); iterator.hasNext();) {
			DbxEntry.File file = iterator.next();
			if (!overwrite(file.path)) {
				iterator.remove();
			}
		}
	}

	/**
	 * <pre>
	 * 根据Dropbox路径判定对应文件是否一定需要覆盖:
	 * 1. 根目录下面文件一定覆盖;
	 * 2. FreeMarker模板文件一定覆盖;
	 * 3. 位于覆盖列表中的文件一定覆盖.
	 * </pre>
	 * @param path	Dropbox路径
	 */
	public boolean overwrite(String path) {
		if (!DeployUtils.startsWithAny(path, rootPath)) {
			return false;
		}

		String relativePath = this.getRelativePath(path);
  		String extension = FilenameUtils.getExtension(relativePath);
  		if (!relativePath.contains(DeployUtils.SLASH) || com.amzass.dist.Extension.FREEMARKER_TEMPLATE.value().equalsIgnoreCase(extension)) {
  			return true;
  		}

		return 	DeployUtils.containsAny(this.getRelativePath(path), overrideList) &&
				DeployUtils.containsAny(extension, com.amzass.dist.Extension.EXE.value(), com.amzass.dist.Extension.JAR.value());
	}

	/**
	 * 获取当前应用根路径的相对路径
	 * @param dbxPath	Dropbox文件路径
	 */
	public String getRelativePath(String dbxPath) {
		if (dbxPath.length() >= rootPath.length() + 1) {
			return dbxPath.substring(rootPath.length() + (rootPath.length() > 1 ? 1 : 0));
		}
		return dbxPath;
	}

	private boolean skip(File clientSide, DbxEntry.File serverSide) {
		return 	(clientSide.lastModified() == serverSide.lastModified.getTime() && clientSide.length() == serverSide.numBytes) ||
				(!this.overwrite(serverSide.path) && clientSide.length() == serverSide.numBytes);
	}

	private DownloadResult _download(DbxEntry.File dbxFile, String localRootDir) {
		long start = System.currentTimeMillis();
		FileOutputStream outputStream = null;
		String path = this.getRelativePath(dbxFile.path);
		if (this.isPathToLowercase()) {
			path = path.toLowerCase();
		}
		File target = new File(localRootDir, path);
		try {
			if (!target.exists()) {
				target.getParentFile().mkdirs();
			} else if (this.skip(target, dbxFile)) {
				String msg = I18N.text("message.skip", path);
				LOGGER.info(msg + String.format("(Last Modify Timestamp: %s -> %s, Length: %s -> %s)", target.lastModified(), dbxFile.lastModified.getTime(), target.length(), dbxFile.numBytes));
				return new DownloadResult(Status.Skip, msg);
			}

			outputStream = new FileOutputStream(target.getAbsoluteFile());
			client.getFile(dbxFile.path, null, outputStream);
			// 检查下载后文件大小是否一致，如不一致，重复下载
			if (target.length() != dbxFile.numBytes) {
				String errorMsg = I18N.text("message.download.fail", dbxFile.path, target.length(), dbxFile.numBytes);
				LOGGER.error(errorMsg);
				throw new DownloadFailException(errorMsg);
			}

			String result = I18N.text("message.success", path, dbxFile.humanSize, DeployUtils.formatTime(System.currentTimeMillis() - start));
			LOGGER.info(result + String.format("{%s(%s) => %s(%s)}", target.getAbsolutePath(), target.length(), dbxFile.path, dbxFile.numBytes));
			return new DownloadResult(Status.Success, result);
		} catch (DbxException e) {
            throw new DownloadFailException(e.getMessage());
        } catch (IOException e) {
			// 如果文件是因为进程被锁定而无法被更新，下载结果降级为跳过而非失败
			if (DeployUtils.containsAny(dbxFile.path, appName + ".exe", "Deploy " + appName + ".exe")) {
				return new DownloadResult(Status.Skip, I18N.text("message.fail", dbxFile.path, appName));
			}
			String result = I18N.text("message.fail.dbx", dbxFile.path, DeployUtils.getExceptionMsg(e));
			LOGGER.error(result);
			return new DownloadResult(Status.Fail, result);
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
	}

	/**
	 * @see #download(DbxEntry.File, String)
	 */
	public String downloadFile(DbxEntry.File dbxFile, String localRootDir) {
		return this.download(dbxFile, localRootDir).message;
	}

    /**
     * 将Dropbox指定文件夹中的所有文件下载到本地指定文件夹中
     * @param dbxDir        Dropbox文件夹路径
     * @param localDir      本地文件夹路径
     */
    public List<DownloadResult> syncDirectory(String dbxDir, String localDir) {
        File localFolder = new File(localDir);
        if (!localFolder.exists()) {
            localFolder.mkdirs();
        }

        List<DbxEntry.File> files = null;
        boolean success = false;
        String errorMsg = null;
        for (int i = 0; i < REPEAT_TIMES; i++) {
            try {
                files = this.getAllFiles(dbxDir, Extension.ALL);
                success = true;
            } catch (DbxException e) {
                errorMsg = e.getMessage();
                if (i < REPEAT_TIMES - 1) {
                    DeployUtils.sleep(REPEAT_TIMES, TimeUnit.SECONDS);
                }
            }
        }

        if (!success) {
            return Collections.singletonList(new DownloadResult(Status.Fail, DeployUtils.defaultString(errorMsg, String.format("Failed to retrieve file list of dropbox directory '%s' after %d attempts.", dbxDir, REPEAT_TIMES))));
        }

        List<DownloadResult> results = new ArrayList<>(files.size());
        for (DbxEntry.File file : files) {
            DownloadResult result = this.download(file, localDir);
            results.add(result);
        }

        return results;
    }

    public static final String TOO_MANY_REQUESTS = "Too Many Requests";
	/**
	 * 下载DropBox指定文件到本地，返回下载结果
	 * @param dbxFile		Dropbox指定文件
	 * @param localRootDir	本地存放根目录
	 */
	public DownloadResult download(DbxEntry.File dbxFile, String localRootDir) {
		String error = null;
		String path = this.getRelativePath(dbxFile.path);
		File local = new File(localRootDir, path);
		for (int i = 0; i < REPEAT_TIMES; i++) {
			try {
				DownloadResult result = this._download(dbxFile, localRootDir);
				if (result.status == Status.Success || result.status == Status.Skip) {
					local.setLastModified(dbxFile.lastModified.getTime());
				}
                return result;
            } catch (DownloadFailException e) {
				error = DeployUtils.getExceptionMsg(e);
				if (local.exists()) {
					FileUtils.deleteQuietly(local);
				}
                if (i < REPEAT_TIMES - 1) {
                    if (DeployUtils.containsAny(e.getMessage(), TOO_MANY_REQUESTS)) {
                        DeployUtils.sleep(60, TimeUnit.SECONDS);
                    } else {
                        DeployUtils.sleep(REPEAT_TIMES, TimeUnit.SECONDS);
                    }
                }
			}
		}
		return new DownloadResult(Status.Fail, error);
	}

    /**
     * 检查在Dropbox中是否存在给定路径的文件夹
     * @param dbxDir    文件夹路径
     * @throws DbxException
     */
    public boolean isDirectoryExist(String dbxDir) throws DbxException {
        DbxException exception = null;
        for (int i = 0; i < REPEAT_TIMES; i++) {
            try {
                DbxEntry entry = this.client.getMetadata(dbxDir);
                return entry != null && entry.isFolder();
            } catch (DbxException e) {
                exception = e;
                if (i < REPEAT_TIMES - 1) {
                    DeployUtils.sleep(REPEAT_TIMES, TimeUnit.SECONDS);
                }
            }
        }
        throw exception;
    }

    /**
     * 在Dropbox中创建文件夹, 如果其已经存在则直接略过
     * @param dbxDir    Dropbox文件夹路径
     */
    public void createFolder(String dbxDir) throws DbxException {
        DbxException exception = null;
        boolean success = false;
        for (int i = 0; i < REPEAT_TIMES; i++) {
            try {
                DbxEntry entry = this.client.getMetadata(dbxDir);
                if (entry != null && entry.isFolder()) {
                    return;
                }
                entry = this.client.createFolder(dbxDir);
                if (entry != null && entry.isFolder()) {
                    success = true;
                    break;
                }
            } catch (DbxException e) {
                exception = e;
                if (i < REPEAT_TIMES - 1) {
                    DeployUtils.sleep(REPEAT_TIMES, TimeUnit.SECONDS);
                }
            }
        }

        if (!success) {
            if (exception != null) {
                throw exception;
            } else {
                throw new DbxException("Failed to create folder '" + dbxDir + "' after " + REPEAT_TIMES + " attempts.");
            }
        }
    }

	public String getRootPath() {
		return rootPath;
	}
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
}