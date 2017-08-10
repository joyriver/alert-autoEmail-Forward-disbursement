package com.kbers.dist;

/**
 * Download failed exception definition
 * @author <a href="mailto:nathanael4ever@gmail.com">Nathanael Yang</a> Apr 28, 2015
 */
public class DownloadFailException extends RuntimeException {

	private static final long serialVersionUID = -8393512058440649286L;

	public DownloadFailException(String message) {
		super(message);
	}

}