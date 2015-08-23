package com.jemmyee.framework.app.open.taobao.manager;

import com.jemmyee.framework.app.open.taobao.TaokeConstants;
import com.taobao.api.TaobaoResponse;

public class BaseManager {
	
	public static void debug(TaobaoResponse res){
		if (TaokeConstants.IS_DEBUG) {
			System.out.println("body:" + res.getBody());
			System.out.println("msg:" + res.getMsg());
			System.out.println("params:" + res.getParams());
			System.out.println("errorcode:" + res.getErrorCode());
		}
	}

}
