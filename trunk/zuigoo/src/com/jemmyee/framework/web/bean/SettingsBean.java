package com.jemmyee.framework.web.bean;

/**
 * @Description:系统配置相关xml属性
 * @author:jemmyee@gmail.com
 * @date:2011-9-27
 * @version:v1.0
 */
public class SettingsBean {
	
	//user 会员相关
	private Integer regPoints;
	private Integer regMoney;
	private Double pointPrice;
	//淘客
	private String nick;
	private String appkey;
	private String appsecret;
	private String appurl;
	
	

	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public String getAppurl() {
		return appurl;
	}
	public void setAppurl(String appurl) {
		this.appurl = appurl;
	}
	public Double getPointPrice() {
		return pointPrice;
	}
	public void setPointPrice(Double pointPrice) {
		this.pointPrice = pointPrice;
	}
	public Integer getRegPoints() {
		return regPoints;
	}
	public void setRegPoints(Integer regPoints) {
		this.regPoints = regPoints;
	}
	public Integer getRegMoney() {
		return regMoney;
	}
	public void setRegMoney(Integer regMoney) {
		this.regMoney = regMoney;
	}
	
}
