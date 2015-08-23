package com.jemmyee.framework.app.open.taobao.manager;

import java.util.List;

import com.jemmyee.framework.app.open.taobao.BaseUtils;
import com.jemmyee.framework.app.open.taobao.TaokeConstants;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.request.TaobaokeItemsConvertRequest;
import com.taobao.api.request.TaobaokeItemsGetRequest;
import com.taobao.api.response.TaobaokeItemsConvertResponse;
import com.taobao.api.response.TaobaokeItemsGetResponse;

/**
 * @Description:淘客API
 * @author:jemmyee@gmail.com
 * @date:2011-4-26
 * @version:v1.0
 */
public class TaokeManager extends BaseManager{

	public static void main(String[] args) throws ApiException {
            getTaobaokeItems("2286377531");
//		      TaobaokeItemsGetRequest bean=new TaobaokeItemsGetRequest();
//		      bean.setStartCommissionNum("100");
//		      bean.setEndCommissionNum("6000");
////		      bean.setStartCommissionRate("1000.00");
//		      bean.setStartCredit("10");
//		      bean.setEndCredit("25");
//		      bean.setKeyword("美白");
//		      bean.setSort("commissionNum_desc");
////		      bean.setPageSize(Long.valueOf("9"));
//		      List<TaobaokeItem> items=getTaobaokeItems(bean);
//		      System.out.println("总计:"+items.size());
//		      for(TaobaokeItem item:items)
//		      {
//		    	  System.out.println("标题："+item.getTitle()+"   价格:"+item.getPrice()+"  成交量:"+item.getCommissionNum()+"  累计佣金:"+item.getCommissionVolume()+"  信誉:"+item.getSellerCreditScore());
//		      }
		      
		      
	}
	
	/**
	* @Description:淘宝客商品查询
	* @author:jemmyee@gmail.com
	* @date:2011-8-9
	*/
	public static List<TaobaokeItem> getTaobaokeItems(TaobaokeItemsGetRequest bean) throws ApiException{
		TaobaoClient client = BaseUtils.getClient();
		TaobaokeItemsGetRequest req=new TaobaokeItemsGetRequest();
		req.setFields("num_iid,title,nick,pic_url,price,click_url,commission,commission_rate,commission_num,commission_volume,shop_click_url,seller_credit_score,item_location,volume");
		req.setNick(BaseUtils.getSetting("nick"));
//		req.setPid("mm_123456_0_0");
		req.setKeyword(bean.getKeyword());
		req.setCid(bean.getCid());
		if(bean.getStartPrice()!=null)
		{
			req.setStartPrice(bean.getStartPrice());
		}
		if(bean.getEndPrice()!=null)
		{
			req.setEndPrice(bean.getEndPrice());
		}
		if(bean.getStartCommissionRate()!=null)
		{
			req.setStartCommissionRate(bean.getStartCommissionRate());
		}
		if(bean.getEndCommissionRate()!=null)
		{
			req.setEndCommissionRate(bean.getEndCommissionRate());
		}
		if(bean.getStartCommissionNum()!=null)
		{
			req.setStartCommissionNum(bean.getStartCommissionNum());
		}
		if(bean.getEndCommissionNum()!=null)
		{
			req.setEndCommissionNum(bean.getEndCommissionNum());
		}
//		req.setAutoSend("true");
//		req.setArea("杭州");
//		req.setStartCredit("1heart");
//		req.setEndCredit("1heart");
//		req.setSort("price_desc");
//		req.setGuarantee("true");
//		req.setStartCommissionRate("1234");
//		req.setEndCommissionRate("2345");
//		req.setStartCommissionNum("1000");
//		req.setEndCommissionNum("10000");
//		req.setStartTotalnum("1");
//		req.setEndTotalnum("10");
//		req.setCashCoupon("true");
//		req.setVipCard("true");
//		req.setOverseasItem("true");
//		req.setSevendaysReturn("true");
//		req.setRealDescribe("true");
//		req.setOnemonthRepair("true");
//		req.setCashOndelivery("true");
//		req.setMallItem("true");
		req.setPageNo(1L);
		req.setPageSize(40L);
//		req.setOuterCode("abc");
//		req.setIsMobile(true);
		TaobaokeItemsGetResponse response = client.execute(req);
		debug(response);
		return response.getTaobaokeItems();
	}
	/**
	* @Description:根据商品ID,得到淘宝客商品
	* @author:jemmyee@gmail.com
	* @date:2011-8-9
	*/
	public static List<TaobaokeItem> getTaobaokeItems(String numIids) throws ApiException{
		TaobaoClient client = BaseUtils.getClient();
		TaobaokeItemsConvertRequest req=new TaobaokeItemsConvertRequest();
		req.setFields("num_iid,title,nick,pic_url,price,click_url,commission,commission_rate,commission_num,commission_volume,shop_click_url,seller_credit_score,item_location,volume");
		req.setNick(BaseUtils.getSetting("nick"));
        req.setNumIids(numIids);
        TaobaokeItemsConvertResponse response = client.execute(req);
		debug(response);
		return response.getTaobaokeItems();
	}
}
