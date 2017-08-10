package com.kbers.common;


/**
 * 系统中的应用程序枚举
 * @author <a href="mailto:nathanael4ever@gmail.com">Nathanael Yang</a> Mar 14, 2015 3:23:57 PM
 */
public enum Application {
	/**
	 * 找做单流程实现
	 */
	OrderMan,
	/**
	 * 客服助手
	 */
	MailMan,
	/**
	 * 守望者: 侵权、违禁等账户安全助手
	 */
	WatchMan,
	/**
	 * 掌门狗: 卖家健康指标监控
	 */
	WatchDog,

    JoyMan;
	
	public static com.kbers.common.Application get(String str) {
		for (com.kbers.common.Application app : com.kbers.common.Application.values()) {
			if (app.name().equalsIgnoreCase(str)) {
				return app;
			}
		}
		return null;
	}
}