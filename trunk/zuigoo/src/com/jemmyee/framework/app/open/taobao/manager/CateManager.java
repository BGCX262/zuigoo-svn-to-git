package com.jemmyee.framework.app.open.taobao.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jemmyee.framework.app.open.taobao.BaseUtils;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ItemCat;
import com.taobao.api.domain.ItemProp;
import com.taobao.api.domain.PropValue;
import com.taobao.api.request.ItemcatsGetRequest;
import com.taobao.api.request.ItempropsGetRequest;
import com.taobao.api.request.ItempropvaluesGetRequest;
import com.taobao.api.response.ItemcatsGetResponse;
import com.taobao.api.response.ItempropsGetResponse;
import com.taobao.api.response.ItempropvaluesGetResponse;

/**
 * @Description:产品品API
 * @author:jemmyee@gmail.com
 * @date:2011-4-26
 * @version:v1.0
 */
public class CateManager extends BaseManager{

	public static void main(String[] args) throws ApiException,ParseException {
        getItemCats();
	}
	

	public static List<ItemProp> getItemProps() throws ApiException, ParseException{
		TaobaoClient client = BaseUtils.getClient();
		ItempropsGetRequest req=new ItempropsGetRequest();
		req.setFields("pid,name,must,multi,prop_values");
		req.setCid(44343L);
		req.setPid(3232L);
		req.setParentPid(4834L);
		req.setIsKeyProp(true);
		req.setIsSaleProp(true);
		req.setIsColorProp(true);
		req.setIsEnumProp(true);
		req.setIsInputProp(true);
		req.setIsItemProp(true);
		Date dateTime = SimpleDateFormat.getDateTimeInstance().parse("2010-01-01 00:00:00");
		req.setDatetime(dateTime);
		req.setChildPath("3932:13221;2113:2345");
		ItempropsGetResponse response = client.execute(req);
		debug(response);
		return response.getItemProps();
	}
	
	public static List<PropValue> getPropValues() throws  ParseException,ApiException{
		TaobaoClient client = BaseUtils.getClient();
		ItempropvaluesGetRequest req=new ItempropvaluesGetRequest();
		req.setFields("cid,pid,prop_name,vid,name,name_alias,status,sort_order");
		req.setCid(50010538L);
		req.setPvs("20561:1234");
		Date dateTime = SimpleDateFormat.getDateTimeInstance().parse("2000-01-01 00:00:00");
		req.setDatetime(dateTime);
		ItempropvaluesGetResponse res = client.execute(req);
		debug(res);
		return res.getPropValues();
	}
	public static List<ItemCat> getItemCats() throws  ParseException,ApiException{
		TaobaoClient client = BaseUtils.getClient();
		ItemcatsGetRequest req=new ItemcatsGetRequest();
		req.setFields("cid,parent_cid,name,is_parent");
		req.setParentCid(0L);
//		req.setCids("18957,19562,");
		Date dateTime = SimpleDateFormat.getDateTimeInstance().parse("2000-01-01 00:00:00");
		req.setDatetime(dateTime);
		ItemcatsGetResponse response = client.execute(req);
		debug(response);
		return response.getItemCats();
	}
}
