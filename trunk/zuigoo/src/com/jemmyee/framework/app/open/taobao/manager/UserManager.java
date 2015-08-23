package com.jemmyee.framework.app.open.taobao.manager;

import java.util.List;

import com.jemmyee.framework.app.open.taobao.BaseUtils;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.User;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.request.UsersGetRequest;
import com.taobao.api.response.UserGetResponse;
import com.taobao.api.response.UsersGetResponse;

/**
 * @Description:用户API
 * @author:jemmyee@gmail.com
 * @date:2011-4-26
 * @version:v1.0
 */
public class UserManager extends BaseManager{

	public static void main(String[] args) throws ApiException {
		getUserByNick(BaseUtils.getSetting("nick"));
	}

	/**
	* @Description:通过nick得到用户
	* @author:jemmyee@gmail.com
	* @date:2011-4-26
	*/
	public static User getUserByNick(String nick) throws ApiException {
		TaobaoClient client = BaseUtils.getClient();
		UserGetRequest req = new UserGetRequest();
		req.setFields("user_id,nick,seller_credit,location,created,last_visit,email");
		req.setNick(BaseUtils.getSetting("nick"));
		UserGetResponse res = client.execute(req, "");
		debug(res);
		return res.getUser();
	}
	/**
	 * @Description:通过session得到用户
	 * @author:jemmyee@gmail.com
	 * @date:2011-4-26
	 */
	public static User getUserBySession(String session) throws ApiException {
		TaobaoClient client = BaseUtils.getClient();
		UserGetRequest req = new UserGetRequest();
		req.setFields("user_id,nick,seller_credit,location,created,last_visit,email");
		UserGetResponse res = client.execute(req,session);
		debug(res);
		return res.getUser();
	}
	
	/**
	* @Description:通过用户nicks批量得到用户
	* @param nicks eg: "nick1,nick2"
	* @author:jemmyee@gmail.com
	* @date:2011-4-26
	*/
	public static List<User> getUsers(String nicks) throws ApiException {
		TaobaoClient client = BaseUtils.getClient();
		UsersGetRequest req = new UsersGetRequest();
		req.setFields("user_id,nick,seller_credit,location,created,last_visit,email");
		req.setNicks(""+nicks+"");
		UsersGetResponse res = client.execute(req);
		debug(res);
		return res.getUsers();
	}
}
