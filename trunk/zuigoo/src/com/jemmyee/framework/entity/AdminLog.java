package com.jemmyee.framework.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @Description:管理员日志
 * @author:jemmyee@gmail.com
 * @date:2011-4-1
 * @version:v1.0
 */
@Entity
@Table(name = "core_log")
public class AdminLog extends BaseEntity implements java.io.Serializable {

	// Fields
	private String id;
	private Date addTime;
	private String userName;
	private String content;
	private String ip;
	private Short isDelete;
	private Short type; //1 管理员日志  2 会员日志

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "add_time", length = 0)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "ip", length = 15)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column(name = "is_delete")
	public Short getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
	
	@Column(name = "type")
	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

}