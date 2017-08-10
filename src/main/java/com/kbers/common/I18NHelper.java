package com.kbers.common;

import com.amzass.common.Language;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

/**
 * 国际化辅助工具类
 * @author <a href="mailto:nathanael4ever@gmail.com">Nathanael Yang</a> Mar 14, 2015 3:19:54 PM
 */
public class I18NHelper {
	/**
	 * 获取当前系统的显示语言类型
	 */
	public static com.amzass.common.Language getCurrentLang() {
		File file = new File(System.getProperty("user.dir"), "customize" + File.separator + "lang.js");
		if (!file.exists()) {
			return determineDefaultLanguage();
		}

		try {
			List<String> contents = Files.readAllLines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset());
			for (String s : contents) {
				if (s != null && s.indexOf("=") != -1 && !s.startsWith("//")) {
					String[] arr = s.split("=");
					String code = arr[1];
					return com.amzass.common.Language.getLanguage(code);
				}
			}
		} catch (IOException e) {
			// -> Ignore
		}

		return determineDefaultLanguage();
	}
	/**
	 * 获取当前可用的国家化Locale: 先读取用户配置内容，如果没有，取默认值
	 */
	public static Locale getCurrentLocale() {
		return getCurrentLang().locale();
	}

	/**
	 * 确定默认使用的语种类型
	 */
	public static com.amzass.common.Language determineDefaultLanguage() {
		Locale locale = Locale.getDefault();
		if (Locale.CHINA.equals(locale)) {
			return com.amzass.common.Language.ZH_CN;
		} else if (Locale.TAIWAN.equals(locale)) {
			return com.amzass.common.Language.ZH_TW;
		} else {
			return Language.EN_US;
		}
	}
}
