package com.kbers.dist;

import com.amzass.common.AbstractI18N;
import com.amzass.common.I18NHelper;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 部署人国际化
 * <a href="mailto:nathanael4ever@gmail.com>Nathanael Yang</a> Feb 9, 2015 12:24:29 PM
 */
public class I18N extends AbstractI18N {
	private I18N() {}
	
	private static final com.kbers.dist.I18N instance = new com.kbers.dist.I18N();
	
	public static com.kbers.dist.I18N getInstance() {
		return instance;
	}
	
	private final String resource = "i18n.Deploy";
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(resource, Locale.CHINA);
	
	/**
	 * @see com.amzass.dist.I18N#getText(String, Object...)
	 */
	public static String text(String i18nKey, Object... params) {
		return com.kbers.dist.I18N.getInstance().getText(i18nKey, params);
	}
	
	/**
	 * @see com.amzass.dist.I18N#changeLocale(Locale)
	 */
	public static void setLocale() {
		com.amzass.dist.I18N.getInstance().changeLocale(I18NHelper.getCurrentLocale());
	}
	
	@Override
	public ResourceBundle getBundle() {
		return resourceBundle;
	}
	
	@Override
	public void setBundle(Locale locale) {
		resourceBundle = ResourceBundle.getBundle(resource, locale);
	}
}
