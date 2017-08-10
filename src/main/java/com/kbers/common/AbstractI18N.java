package com.kbers.common;

import javax.swing.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * I18N抽象基类
 * @author <a href="mailto:nathanael4ever@gmail.com">Nathanael Yang</a> Mar 14, 2015 3:44:19 PM
 */
public abstract class AbstractI18N {
	/**
	 * 获取当前的{@link ResourceBundle}
	 */
	public abstract ResourceBundle getBundle();
	
	/**
	 * 设置当前{@link ResourceBundle}的{@link Locale}
	 * @param locale	待设置的语种所对应Locale
	 */
	public abstract void setBundle(Locale locale);
	
	/**
	 * 变化当前国际化显示的{@link Locale}
	 * @param locale	待设置的语种所对应Locale
	 */
	public void changeLocale(Locale locale) {
		this.setBundle(locale);
		ResourceBundle.clearCache();
		
		Locale.setDefault(locale);
		JComponent.setDefaultLocale(locale);
	}
	
	/**
	 * 获取国际化文本内容
	 * @param i18nKey	国际化文本Key
	 * @param params	变量，填充{0}、{1}等占位符
	 */
	protected String getText(String i18nKey, Object... params) {
		String keyContent = this.getKeyContent(i18nKey);
		return MessageFormat.format(keyContent, params);
	}
	
	/**
	 * 获取资源文件中i18 Key对应的文本模板内容
	 * @param key	i18n Key，形如title.deployman等等
	 */
	private String getKeyContent(String key) {
		try {
			ResourceBundle bundle = this.getBundle();
			String result = bundle.getString(key);
			if (result.trim().length() == 0) {
				return key;
			}
			return result;
		} catch (MissingResourceException e) {
			return key;
		}
	}
}