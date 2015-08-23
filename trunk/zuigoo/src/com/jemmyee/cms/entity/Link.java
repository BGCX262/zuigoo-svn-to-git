package com.jemmyee.cms.entity;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.jemmyee.framework.entity.BaseEntity;

/**
 * @Description:友情链接
 * @author:jemmyee@gmail.com
 * @date:2011-4-1
 * @version:v1.0
 */
@Entity
@Table(name = "core_link")
public class Link extends BaseEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String url;
	private String logo;
	private Integer showOrder;
	private Short isDelete;//是否删除,禁用  1是  0否
	private Date addTime;
	private Integer type;//1 文字链接  2图片链接
	private String contact;//联系方式
	private Short isVip;//首页推荐  1是 0否

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "logo")
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "show_order")
	public Integer getShowOrder() {
		return this.showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	@Column(name = "is_delete")
	public Short getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "add_time", length = 0)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name = "contact")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	@Column(name = "is_vip")
	public Short getIsVip() {
		return isVip;
	}

	public void setIsVip(Short isVip) {
		this.isVip = isVip;
	}
	
	

}