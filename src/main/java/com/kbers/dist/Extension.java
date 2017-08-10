package com.kbers.dist;

/**
 * File extensions
 * @author <a href="mailto:nathanael4ever@gmail.com>Nathanael Yang</a> Jan 4, 2015 3:42:08 PM
 */
public enum Extension {
	/** jar file */
	JAR("jar"),
	/** Windows executable file */
	EXE("exe"),
	/** JavaScript file */
	JAVASCRIPT("js"),
	/** json file */
	JSON("json"),
	/** xml file, usually logback config file */
	XML("xml"),
	/** html file, usually documentation file */
	HTML("html"),
	/** pdf file, usually documentation file */
	PDF("pdf"),
	/** version indicator file */
	VERSION("ver"),
	/** Freemarker template file */
	FREEMARKER_TEMPLATE("ftl"),
	/** Shell Script File */
	ShellScript("sh"),
	/** 文本文件 */
	Text("txt"),
	/** any kind of file */
	ALL("");

	private final String value;
	public String value() {
		return value;
	}
	public String append(String baseName) {
		return baseName + "." + value;
	}
	Extension(String value) {
		this.value = value;
	}
}