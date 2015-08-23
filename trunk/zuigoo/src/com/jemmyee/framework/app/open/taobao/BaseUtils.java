package com.jemmyee.framework.app.open.taobao;

import com.jemmyee.framework.utils.ConfigXmlUtils;
import com.jemmyee.framework.web.Constants;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;

public class BaseUtils {
	
	public static String getSetting(String param)
	{
		return ConfigXmlUtils.getConfig(Constants.SETTING_PATH,"taoke",param);
	}
	
	/**
	* @Description:得到客户端链接
	* @author:jemmyee@gmail.com
	* @date:2010-11-17
	*/
	public static TaobaoClient getClient(){
		TaobaoClient client=new DefaultTaobaoClient(getSetting("appurl"),getSetting("appkey"),getSetting("appsecret"));
		return client;
	}

}
