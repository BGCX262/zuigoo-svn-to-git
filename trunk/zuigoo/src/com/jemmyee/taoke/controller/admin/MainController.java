package com.jemmyee.taoke.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jemmyee.framework.app.open.taobao.manager.TaokeManager;
import com.jemmyee.framework.utils.ConfigXmlUtils;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.bean.SettingsBean;
import com.jemmyee.taoke.controller.kernel.BaseTaokeFrontController;
import com.jemmyee.taoke.entity.TaokeCategory;
import com.jemmyee.taoke.entity.TaokeItem;
import com.taobao.api.ApiException;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.request.TaobaokeItemsGetRequest;

/**
 * @Description:系统设置等
 * @author:jemmyee@gmail.com
 * @date:2011-9-8
 * @version:v1.0
 */
@Controller("taskMainController")
@RequestMapping(Constants.MV_URI_APP_TAOKE+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+"main")
public class MainController extends BaseTaokeFrontController{
	
	private static Log log= LogFactory.getLog(MainController.class);

	private static final String ROOT_VIEW =Constants.MV_URI_MAIN_ADMIN+Constants.MV_URI_APP_TAOKE+Constants.SYM_PATH_SEPARATOR+"main";
	private static final String ROOT_URI=Constants.MV_URI_APP_TAOKE+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+"main";
	/**
	* @Description:主框架
	* @author:jemmyee@gmail.com
	* @date:2010-10-20
	*/
	@RequestMapping("/toSet/{tabID}")
	public ModelAndView toSet(@PathVariable Integer tabID,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.addObject(Constants.MV_OBJECT_ROOT_URI,ROOT_URI);
		mv.addObject("tabID",tabID);
//		if(tabID.intValue()==2)
//		{
			mv.addObject("regPoints",ConfigXmlUtils.getConfig(Constants.SETTING_PATH,"user","regPoints"));
			mv.addObject("regMoney",ConfigXmlUtils.getConfig(Constants.SETTING_PATH,"user","regMoney"));
			mv.addObject("pointPrice",ConfigXmlUtils.getConfig(Constants.SETTING_PATH,"user","pointPrice"));
			
			mv.addObject("nick",ConfigXmlUtils.getConfig(Constants.SETTING_PATH,"taoke","nick"));
			mv.addObject("appkey",ConfigXmlUtils.getConfig(Constants.SETTING_PATH,"taoke","appkey"));
			mv.addObject("appsecret",ConfigXmlUtils.getConfig(Constants.SETTING_PATH,"taoke","appsecret"));
			mv.addObject("appurl",ConfigXmlUtils.getConfig(Constants.SETTING_PATH,"taoke","appurl"));
//		}
		mv.setViewName(ROOT_VIEW+"/set");
		return mv;
	}
	
	
	/**
	 * @Description:系统设置
	 * @author:jemmyee@gmail.com
	 * @date:2011-9-24
	 */
	@RequestMapping("/set/{tabID}")
	@ResponseBody
	public Map<String, Object> set(@PathVariable Integer tabID,@RequestBody SettingsBean bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			if(tabID.intValue()==1)
			{
				ConfigXmlUtils.editConfig(Constants.SETTING_PATH,"taoke","nick",bean.getNick().toString());
				ConfigXmlUtils.editConfig(Constants.SETTING_PATH,"taoke","appkey",bean.getAppkey().toString());
				ConfigXmlUtils.editConfig(Constants.SETTING_PATH,"taoke","appsecret",bean.getAppsecret().toString());
				ConfigXmlUtils.editConfig(Constants.SETTING_PATH,"taoke","appurl",bean.getAppurl().toString());
			}
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}
	

	/**
	* @Description:产品采集
	* @author:jemmyee@gmail.com
	* @date:2011-10-12
	*/
	@RequestMapping("/gather")
	@ResponseBody
	public Map<String, Object> gather(HttpServletRequest request)
	{   
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_SUCC);
		int count=1;//生成的文件个数
		//删除之前的记录
		try{
		String sql="delete from taoke_taokeitem where is_vip=0";
		taokeItemService.batchUpdate(sql);
		for(TaokeCategory cate:taokeCategoryService.findByProperty("isLast",1)){
		    	 TaobaokeItemsGetRequest bean=new TaobaokeItemsGetRequest();
			      bean.setStartCommissionNum("100");
			      bean.setEndCommissionNum("6000");
//			      bean.setStartCommissionRate("1000.00");
			      bean.setStartCredit("10");
			      bean.setEndCredit("25");
			      bean.setKeyword(cate.getName());
			      bean.setSort("commissionNum_desc");
			      List<TaobaokeItem> items = TaokeManager.getTaobaokeItems(bean);
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
			    	  taoke.setIsVip(0);
			    	  taoke.setNick(item.getNick());
			    	  if(temp<=9)
			    	  {
			    	  taokeItemService.save(taoke);
			    	  count=count+1;
//			    	  System.out.println("标题："+item.getTitle()+"   价格:"+item.getPrice()+"  成交量:"+item.getCommissionNum()+"  累计佣金:"+item.getCommissionVolume()+"  信誉:"+item.getSellerCreditScore());
			    	  }
			    	  temp=temp+1;
			    	  
			      }
			      }
			      
		    }
		 }catch(Exception e) {
			log.error(e);
			e.printStackTrace();
			model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_ERROR);
			return model;
		 }
		model.put("count",count);
		return model;
	}
	
}
