package com.jemmyee.cms.entity;

// default package

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;

import com.jemmyee.framework.entity.BaseEntity;
import com.jemmyee.framework.web.bind.JsonDateSerializer;
import com.jemmyee.taoke.entity.TaokeCategory;

/**
 * @Description:文章
 * @author:jemmyee@gmail.com
 * @date:2011-4-1
 * @version:v1.0
 */
@JsonAutoDetect
@Entity(name="com.jemmyee.cms.entity.Article")
@Table(name = "cms_article")
public class Article extends BaseEntity implements java.io.Serializable{

	// Fields

	private String id;
	private Integer categoryId;//所属栏目
	private String title;
	private String content;
	private String author;
	private String keywords;//seo关键字
	private Short Type;
	private Short isOpen;
	private Date addTime;
	private String fileUrl;
	private Short openType;
	private String link;
	private String brief;//摘要
	private Integer sortOrder;
	private String befrom;//来源
	
	private TaokeCategory category;
	
	//关系映射
	@ManyToOne(cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	@JoinColumn(name="category_id",insertable=false,updatable=false)
	public TaokeCategory getCategory() {
		return category;
	}

	public void setCategory(TaokeCategory category) {
		this.category = category;
	}

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

	@Column(name = "category_id")
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "title", length = 150)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "author", length = 30)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "keywords")
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "type")
	public Short getType() {
		return this.Type;
	}

	public void setType(Short Type) {
		this.Type = Type;
	}

	@Column(name = "is_open")
	public Short getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(Short isOpen) {
		this.isOpen = isOpen;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	@Column(name = "add_time", length = 0)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "file_url")
	public String getFileUrl() {
		return this.fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Column(name = "open_type")
	public Short getOpenType() {
		return this.openType;
	}

	public void setOpenType(Short openType) {
		this.openType = openType;
	}

	@Column(name = "link")
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Column(name = "brief")
	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	@Column(name = "sort_order")
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	@Column(name = "befrom")
	public String getBefrom() {
		return befrom;
	}

	public void setBefrom(String befrom) {
		this.befrom = befrom;
	}
	
	

	


}