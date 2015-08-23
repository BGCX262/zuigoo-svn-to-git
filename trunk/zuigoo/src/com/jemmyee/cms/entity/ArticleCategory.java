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
 * @Description:文章分类
 * @author:jemmyee@gmail.com
 * @date:2011-4-1
 * @version:v1.0
 */
@Entity(name="com.jemmyee.cms.entity.ArticleCategory")
@Table(name = "cms_article_category")
public class ArticleCategory extends BaseEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Short type;
	private String seoTitle;
	private String seoKeyword;
	private String seoDesc;
	private Short isDelete;//是否删除   无效不显示     1是 0否
	private String brief;
	private Short sortOrder;
	private Short isShowInNav;//是否显示首页主菜单 1是 0否
	private Integer parentId;
	private Date addTime;
	private Short isLast;//是否底层目录  0 否 1是

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

	@Column(name = "type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "seo_title")
	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}
	@Column(name = "seo_keyword")
	public String getSeoKeyword() {
		return seoKeyword;
	}

	public void setSeoKeyword(String seoKeyword) {
		this.seoKeyword = seoKeyword;
	}
	@Column(name = "seo_desc")
	public String getSeoDesc() {
		return seoDesc;
	}

	public void setSeoDesc(String seoDesc) {
		this.seoDesc = seoDesc;
	}

	@Column(name = "brief")
	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	@Column(name = "sort_order")
	public Short getSortOrder() {
		return this.sortOrder;
	}


	public void setSortOrder(Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Column(name = "is_show_in_nav")
	public Short getIsShowInNav() {
		return this.isShowInNav;
	}

	public void setIsShowInNav(Short isShowInNav) {
		this.isShowInNav = isShowInNav;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "add_time", length = 0)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	@Column(name = "is_last")
	public Short getIsLast() {
		return isLast;
	}

	public void setIsLast(Short isLast) {
		this.isLast = isLast;
	}
	@Column(name = "is_delete")
	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
	

	
}