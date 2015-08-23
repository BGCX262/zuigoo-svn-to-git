package com.jemmyee.framework.app.open.taobao;

import java.text.ParseException;

import com.jemmyee.framework.app.open.taobao.manager.CateManager;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ItemCat;

public class TaokeTest {
	
	public static void main(String[] args) throws ApiException, ParseException {
		TaobaoClient client=BaseUtils.getClient();
        for(ItemCat cat:CateManager.getItemCats())
        {
        	System.out.println(cat.getName());
        }
	}

}
