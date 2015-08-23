package com.jemmyee.framework.web;

/**
 * @Description:常量定义
 * @author:jemmyee@gmail.com
 * @date:2010-10-19
 * @version:v1.0
 */
public class Constants {
	/*application 常量*/
	public static final String APPLICATION_APPROOT="approot";
	public static final String APPLICATION_CTXROOT="ctxroot";
	public static final String APPLICATION_VERSION="version";
	
	
	/*默认分页每页记录数*/
	public static final Integer DEFAULT_PAGESIZE=16;
	/*在线管理员*/
	public static final String ONLINE_ADMIN_USER="online_admin_user";
	/*在线用户*/
	public static final String ONLINE_USER="online_user";
	/*在线管理员*/
	public static final String ONLINE_DEPT_ID="online_dept_id";
	/*在线GoodsCategory*/
	public static final String ONLINE_GOODS_CATEGORY_ID="online_goods_category_id";
	/*在线Region*/
	public static final String ONLINE_REGION_ID="online_region_id";
	/*当前应用*/
	public static final String ONLINE_APP="online_app";
	
	
	/*后台登录验证码*/
	public static final String SESSION_ADMIN_LOGIN_VALIDATE_CODE="session_admin_login_validate_code";
	
	
	/*上传的文件根目录*/
	public static final String UPLOAD_IMAGES_PATH="/upload/images/";
	/*生成的内部静态文件根目录*/
	public static final String STATIC_PATH="/static/gen/";
	
	/*生成的admin dept html文件名*/
	public static final String TREE_HTML_DEPT="dept_tree.html";
	
	/*数据库表前缀*/
	public static final String DB_TABLE_PREFIX="so_";
	
	/*未查询到数据的提示*/
	public static final String NO_DATA_MSG="对不起，没有找到符合条件的数据！";
	
	/*生成的tree目录xml的编码设置*/
	public static final String TREE_XML_FILE_ENCODING="gbk";
	/*生成的tree目录xml的路径*/
	public static final String TREE_XML_FILE_PATH="/data/xml";
	/*生成的dept目录xml的文件名*/
	public static final String TREE_XML_FILE_NAME_DEPT="dept_tree.xml";
	/*生成的dept目录xml的文件名*/
	public static final String TREE_XML_FILE_NAME_GOODS_CATEGORY="goods_category_tree.xml";
	/*生成的region目录xml的文件名*/
	public static final String TREE_XML_FILE_NAME_REGION="region_tree.xml";
	/*生成的region目录xml的文件名*/
	public static final String TREE_XML_FILE_NAME_ARTICLE_CATEGORY="article_category_tree.xml";
	
	/*系统设置文件路径*/
	public static final String XML_FILE_SETTINGS="/WEB-INF/data/config/settings.xml";
	public static final String SETTING_PATH=Constants.class.getResource("settings.xml").getPath();
	
	
	/*ajax提交信息反馈*/
	public static final String AJAX_MSG_NAME="msg";
	public static final String AJAX_MSG_SUCC="succ";
	public static final String AJAX_MSG_ERROR="error";
	public static final String AJAX_MSG_RENAME="rename";
	public static final String AJAX_MSG_RETITLE="retitle";
	public static final String AJAX_MSG_NOUSER="nouser";
	public static final String AJAX_MSG_LOGIN_VCODE="login_vcode_error";
	public static final String AJAX_MSG_LOGIN_ERROR="login_error";
	public static final String AJAX_MSG_PASSWORD_ERROR="password_error";
	public static final String AJAX_MSG_MONEY_NOT_ENOUGH="money_not_enough";//余额不足
	public static final String AJAX_MSG_VIRTUAL_NOT_ENOUGH="virtual_not_enough";//虚拟币不足
	
	/*分页查询入口类型   来自form表单查询*/
	public static final String PAGE_ENTRY_TYPE_FORM="form";
	/*分页查询入口类型   来自page分页*/
	public static final String PAGE_ENTRY_TYPE_PAGE="page";
	/*分页查询入口类型   来自menu菜单*/
	public static final String PAGE_ENTRY_TYPE_MENU="menu";
	
	
	/*ModelAndView 返回的对象名称*/
	public static final String MV_OBJECT_PAGE="page";
	public static final String MV_OBJECT_ENTITY="entity";
	public static final String MV_OBJECT_BEAN="bean";
	public static final String MV_OBJECT_ITEM="item";
	/*所属应用,主要在后台路径中使用 eg:shop*/
	public static final String MV_OBJECT_APP="app";
	
	/*翻页传递的路径前缀*/
	public static final String MV_OBJECT_PAGE_URI="pageuri";
	/*常见方法的通用uri*/
	public static final String MV_OBJECT_ROOT_URI="rooturi";
	
	/*路径分隔符*/
	public static final String SYM_PATH_SEPARATOR="/";
	
	/*url路径约定    安全模块根路径*/
	public static final String MV_URI_MAIN_SSL="/ssl";
	/*url路径约定    根路径front*/
	public static final String MV_URI_MAIN_FRONT="/front";
	/*url路径约定    根路径admin*/
	public static final String MV_URI_MAIN_ADMIN="/admin";
	/*url路径约定    根路径core模块根路径*/
	public static final String MV_URI_APP_CORE="/core";
	/*url路径约定    根路径member模块根路径*/
	public static final String MV_URI_APP_MEMBER="/member";
	/*url路径约定    根路径shop模块根路径*/
	public static final String MV_URI_APP_SHOP="/shop";
	/*url路径约定    根路径cms模块根路径*/
	public static final String MV_URI_APP_CMS="/cms";
	/*url路径约定    根路径crm模块根路径*/
	public static final String MV_URI_APP_CRM="/crm";
	/*url路径约定    根路径task模块根路径*/
	public static final String MV_URI_APP_TASK="/task";
	/*url路径约定    根路径tao模块根路径*/
	public static final String MV_URI_APP_TAOKE="/taoke";
	
	/*前台首页*/
	public static final String MV_URI_COMM_INDEX="/index";
	
	public static final String MV_URI_COMM_TOADD="/toAdd";
	public static final String MV_URI_COMM_ADD="/add";
	public static final String MV_URI_COMM_TOEDIT="/toEdit";
	public static final String MV_URI_COMM_EDIT="/edit";
	public static final String MV_URI_COMM_DELETE="/delete";
	public static final String MV_URI_COMM_DELETBATCH="/deleteBatch";
	public static final String MV_URI_COMM_MODIFY="/modify";
	public static final String MV_URI_COMM_MODIFYBATCH="/modifyBatch";
	public static final String MV_URI_COMM_DETAIL="/detail";
	public static final String MV_URI_COMM_LIST="/list";
	public static final String MV_URI_COMM_FRAME="/frame";
	public static final String MV_URI_COMM_TREE="/tree";
	public static final String MV_URI_COMM_TOCONFIG="/toConfig";
	public static final String MV_URI_COMM_CONFIG="/config";
	
	
	public static final String MV_VIEW_ADD="add";
	public static final String MV_VIEW_EDIT="edit";
	public static final String MV_VIEW_DETAIL="detail";
	public static final String MV_VIEW_LIST="list";
	public static final String MV_VIEW_FRAME="frame";
	public static final String MV_VIEW_TREE="tree";
	public static final String MV_VIEW_CONFIG="config";
	public static final String MV_VIEW_UPLOAD="upload";
	
	/*实体路径名*/
	public static final String ENTITY_ADMIN_DEPT="admindept";
	public static final String ENTITY_ADMIN_LOG="adminlog";
	public static final String ENTITY_ADMIN_ROLE="adminrole";
	public static final String ENTITY_ADMIN_USER="adminuser";
	public static final String ENTITY_LINK="link";

	
	//cotroller中的常量
	public static final Short CON_QUERY_TYPE_ALL=-1;
	
	//高亮显示的前缀
	public static final String HIGHTLIGHT_PREFIX="<font color='red'>";
	public static final String HIGHTLIGHT_SUFFIX="</font>";
	
	public static final String HIBERNATE_SQL_ALIAS_COUNT="counts";
	
}
