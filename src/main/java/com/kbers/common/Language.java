package com.kbers.common;

import java.util.Locale;

/**
 * 语言显示
 * @author <a href="mailto:nathanael4ever@gmail.com">Nathanael Yang</a> Mar 14, 2015 3:03:06 PM
 */
public enum Language {
	/**
	 * 英语(美国)
	 */
	EN_US(Locale.US),
	/**
	 * 简体中文
	 */
	ZH_CN(Locale.CHINA),
	/**
	 * 繁体中文
	 */
	ZH_TW(Locale.TAIWAN);
	
	private Locale locale;
	
	public Locale locale() {
		return this.locale;
	}
	
	Language(Locale locale) {
		this.locale = locale;
	}
	
	public static com.kbers.common.Language getLanguage(String code) {
		for (com.kbers.common.Language lang : com.kbers.common.Language.values()) {
			if (lang.name().equalsIgnoreCase(code)) {
				return lang;
			}
		}
		
		// 兼容老版本配置记录
		if ("SimplifiedChinese".equalsIgnoreCase(code)) {
			return ZH_CN;
		}
		
		if ("TraditionalChinese".equalsIgnoreCase(code)) {
			return ZH_TW;
		}
		
		return EN_US;
	}
}