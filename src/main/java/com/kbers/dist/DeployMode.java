package com.kbers.dist;

import com.amzass.dist.DeployUtils;
import com.amzass.dist.I18N;

/**
 * 部署模式枚举
 * @author <a href="mailto:nathanael4ever@gmail.com>Nathanael Yang</a> Nov 28, 2014 6:33:22 PM
 */
public enum DeployMode {
	/**
	 * 主程序启动之前
	 */
	BeforeStart(true),
	/**
	 * 主程序启动之后
	 */
	AfterStart(true),
	/**
	 * 后台任务执行
	 */
	BatchJob(false),
	/**
	 * 独立部署、安装
	 */
	StandAlone(true),
	/**
	 * 静默模式独立部署、安装
	 */
	SilentStandAlone(false);
	
	private boolean popup;
	public boolean popup() {
		return this.popup;
	}
	
	public String msg(long start) {
		String timeCost = DeployUtils.formatTime(System.currentTimeMillis() - start);
		return I18N.text("label.deploy.mode." + this.name().toLowerCase(), timeCost);
	}
	
	DeployMode(boolean popup) {
		this.popup = popup;
	}
}