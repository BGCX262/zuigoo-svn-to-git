package com.jemmyee.taoke.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.jemmyee.framework.entity.BaseEntity;
@Entity
@Table(name = "taoke_taokeitem")
public class TaokeItem extends BaseEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private Long numIid;
	private String title;
	private String nick;//卖家昵称
	private String picUrl;
	private String price;//商品价格
	private String clickUrl;
	private String commission;//淘宝客佣金
	private String commissionNum;//累计成交量.注：返回的数据是30天内累计推广量
	private String commissionVolume;//累计总支出佣金量
	private String shopClickUrl;//商品所在店铺的推广点击url
	private Long sellerCreditScore;//卖家信用等级
	private String itemLocation;//商品所在地
	private Long volume;//30天内交易量
	private String taobaokeCatClickUrl;//淘宝客类目推广URL
	private String keywordClickUrl;//淘宝客关键词搜索URL
	private Date addTime;
	private Integer status;
	private Integer isTop;
	private Integer isVip;//是否vip 重新批量获取时不删除   手动添加的vip为1  批量获取时为1
	
	private Integer categoryId;

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

	@Column(name = "num_iid")
	public Long getNumIid() {
		return this.numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	@Column(name = "title", length = 400)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "nick", length = 50)
	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Column(name = "pic_url")
	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Column(name = "price", precision = 22, scale = 0)
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "click_url")
	public String getClickUrl() {
		return this.clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	@Column(name = "commission")
	public String getCommission() {
		return this.commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	@Column(name = "commission_num")
	public String getCommissionNum() {
		return this.commissionNum;
	}

	public void setCommissionNum(String commissionNum) {
		this.commissionNum = commissionNum;
	}

	@Column(name = "commission_volume")
	public String getCommissionVolume() {
		return this.commissionVolume;
	}

	public void setCommissionVolume(String commissionVolume) {
		this.commissionVolume = commissionVolume;
	}

	@Column(name = "shop_click_url")
	public String getShopClickUrl() {
		return this.shopClickUrl;
	}

	public void setShopClickUrl(String shopClickUrl) {
		this.shopClickUrl = shopClickUrl;
	}

	@Column(name = "seller_credit_score")
	public Long getSellerCreditScore() {
		return this.sellerCreditScore;
	}

	public void setSellerCreditScore(Long sellerCreditScore) {
		this.sellerCreditScore = sellerCreditScore;
	}

	@Column(name = "item_location", length = 20)
	public String getItemLocation() {
		return this.itemLocation;
	}

	public void setItemLocation(String itemLocation) {
		this.itemLocation = itemLocation;
	}

	@Column(name = "volume")
	public Long getVolume() {
		return this.volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	@Column(name = "taobaoke_cat_click_url")
	public String getTaobaokeCatClickUrl() {
		return this.taobaokeCatClickUrl;
	}

	public void setTaobaokeCatClickUrl(String taobaokeCatClickUrl) {
		this.taobaokeCatClickUrl = taobaokeCatClickUrl;
	}

	@Column(name = "keyword_click_url")
	public String getKeywordClickUrl() {
		return this.keywordClickUrl;
	}

	public void setKeywordClickUrl(String keywordClickUrl) {
		this.keywordClickUrl = keywordClickUrl;
	}

	@Column(name = "add_time", length = 0)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "is_top")
	public Integer getIsTop() {
		return this.isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	@Column(name = "category_id")
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	@Column(name = "is_vip")
	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}
	
	


}