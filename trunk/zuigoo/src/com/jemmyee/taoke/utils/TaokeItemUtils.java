package com.jemmyee.taoke.utils;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jemmyee.framework.app.open.taobao.manager.TaokeManager;
import com.jemmyee.taoke.entity.TaokeCategory;
import com.jemmyee.taoke.entity.TaokeItem;
import com.jemmyee.taoke.service.ITaokeCategoryService;
import com.jemmyee.taoke.service.ITaokeItemService;
import com.taobao.api.ApiException;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.request.TaobaokeItemsGetRequest;

/**
 * @Description:自动抓取淘宝客商品
 * @author:jemmyee@gmail.com
 * @date:2011-8-14
 * @version:v1.0
 */
public class TaokeItemUtils{
	
	public static void main(String[] args) throws ApiException{
	  execute();
	}
	
	public static void execute() throws ApiException{
		  String[] configs = new  String[] {
					"/com/jemmyee/framework/config/spring/spring-dao.xml",
					"/com/jemmyee/framework/config/spring/spring-service.xml",
					"/com/jemmyee/framework/config/spring/spring-datasource.xml",
					"/com/jemmyee/framework/config/spring/spring-hibernate.xml"
					};
			ApplicationContext act = new ClassPathXmlApplicationContext(configs);
	        ITaokeCategoryService<TaokeCategory> taokeCategoryService=(ITaokeCategoryService<TaokeCategory>)act.getBean("taokeCategoryService");
	        ITaokeItemService<TaokeItem> taokeItemService=(ITaokeItemService<TaokeItem>)act.getBean("taokeItemService");
		    for(TaokeCategory cate:taokeCategoryService.findByProperty("isLast",1)){
		    	 TaobaokeItemsGetRequest bean=new TaobaokeItemsGetRequest();
			      bean.setStartCommissionNum("100");
			      bean.setEndCommissionNum("6000");
//			      bean.setStartCommissionRate("1000.00");
			      bean.setStartCredit("10");
			      bean.setEndCredit("25");
			      bean.setKeyword(cate.getName());
			      bean.setSort("commissionNum_desc");
			      List<TaobaokeItem> items=TaokeManager.getTaobaokeItems(bean);
//			      System.out.println("总计:"+items.size());
			      int temp=1;
			      if(items!=null)
			      {

			      for(TaobaokeItem item:items)
			      {
			    	  TaokeItem taoke=new TaokeItem();
			    	  taoke.setAddTime(new Date());
			    	  taoke.setCategoryId(cate.getId());
			    	  taoke.setClickUrl(item.getClickUrl());
			    	  taoke.setCommission(item.getCommission());
			    	  taoke.setCommissionNum(item.getCommissionNum());
			    	  taoke.setCommissionVolume(item.getCommissionVolume());
			    	  taoke.setItemLocation(item.getItemLocation());
			    	  taoke.setVolume(item.getVolume());
			    	  taoke.setTitle(item.getTitle());
			    	  taoke.setTaobaokeCatClickUrl(item.getTaobaokeCatClickUrl());
			    	  taoke.setShopClickUrl(item.getShopClickUrl());
			    	  taoke.setSellerCreditScore(item.getSellerCreditScore());
			    	  taoke.setPrice(item.getPrice());
			    	  taoke.setPicUrl(item.getPicUrl());
			    	  taoke.setNumIid(item.getNumIid());
			    	  taoke.setKeywordClickUrl(item.getKeywordClickUrl());
			    	  taoke.setStatus(1);
			    	  taoke.setIsTop(0);
			    	  taoke.setNick(item.getNick());
			    	  if(temp<=9)
			    	  {
			    		  
			    	  taokeItemService.save(taoke);
			    	  System.out.println("标题："+item.getTitle()+"   价格:"+item.getPrice()+"  成交量:"+item.getCommissionNum()+"  累计佣金:"+item.getCommissionVolume()+"  信誉:"+item.getSellerCreditScore());
			    	  }
			    	  temp=temp+1;
			      }
		    	  
			      }
			      
		    }
	 }
}
