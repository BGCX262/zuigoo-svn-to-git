package com.jemmyee.framework.entity;

import java.util.Date;

import javax.persistence.Transient;

/**
 * @Description:主要提供搜索中的字段支持
 * @author:jemmyee@gmail.com
 * @date:2011-4-2
 * @version:v1.0
 */
public class BaseEntity {
    
	//查询条件字段
    private String bkw;//查询关键字
	private Date startTime; //开始时间1
	private Date endTime; //结束时间1

	private String hightLight;//查询结果高亮部分
	
	//日期临时转换
	private String startTimeString;//日期字符串，主要便于查询时候页面显示
	private String endTimeString;
	
	//备用字段。
	private String reserveField1;
	private String reserveField2;
	
	@Transient
	public String getBkw() {
		return bkw;
	}
	public void setBkw(String bkw) {
		this.bkw = bkw;
	}
	@Transient
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Transient
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Transient
	public String getHightLight() {
		return hightLight;
	}
	public void setHightLight(String hightLight) {
		this.hightLight = hightLight;
	}
	@Transient
	public String getStartTimeString() {
		return startTimeString;
	}
	public void setStartTimeString(String startTimeString) {
		this.startTimeString = startTimeString;
	}
	@Transient
	public String getEndTimeString() {
		return endTimeString;
	}
	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}
	@Transient
	public String getReserveField1() {
		return reserveField1;
	}
	public void setReserveField1(String reserveField1) {
		this.reserveField1 = reserveField1;
	}
	@Transient
	public String getReserveField2() {
		return reserveField2;
	}
	public void setReserveField2(String reserveField2) {
		this.reserveField2 = reserveField2;
	}
	
	
	
	
	
}
