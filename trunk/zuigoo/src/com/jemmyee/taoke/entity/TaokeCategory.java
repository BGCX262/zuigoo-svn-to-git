package com.jemmyee.taoke.entity;

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
 * @Description:分类
 * @author:jemmyee@gmail.com
 * @date:2011-4-1
 * @version:v1.0
 */
@Entity
@Table(name = "taoke_category")
public class TaokeCategory extends BaseEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String code;
	private Short type;//是否品牌 0否  1是
	private String seoTitle;
	private String seoKeyword;
	private String seoDesc;
	private Integer sortOrder;
	private Short isDelete;//是否删除   无效不显示     1是 0否
	private Short isRecommend;//是否推荐   1是  0否
	private Short isLast;//是否最后一层目录   1是  0否
	private Integer parentId;
	private Date addTime;


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
	
	@Column(name = "sort_order")
	public Integer getSortOrder() {
		return this.sortOrder;
	}


	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "is_delete")
	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
	@Column(name = "is_recommend")
	public Short getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Short isRecommend) {
		this.isRecommend = isRecommend;
	}
	
	@Column(name = "is_last")
	public Short getIsLast() {
		return isLast;
	}

	public void setIsLast(Short isLast) {
		this.isLast = isLast;
	}
	

}