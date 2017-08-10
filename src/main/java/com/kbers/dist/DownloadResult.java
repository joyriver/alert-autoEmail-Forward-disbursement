package com.kbers.dist;

/**
 * 下载结果
 * @author <a href="mailto:nathanael4ever@gmail.com>Nathanael Yang</a> Nov 28, 2015 2:26:47 PM
 */
public class DownloadResult {
	public enum Status {
		Success, Skip, Fail
	}
	
	public Status status;
	public String message;
	
	public DownloadResult(Status status, String message) {
		this.status = status;
		this.message = message;
	}

    @Override
    public String toString() {
        return status.name() + ":" + this.message;
    }
}