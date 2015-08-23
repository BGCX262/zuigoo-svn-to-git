package com.jemmyee.framework.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.controller.kernel.BaseFrameworkController;


/**
 * @Description:后台主框架的控制器,有是否登陆控制
 * @author:jemmyee@gmail.com
 * @date:2010-10-20
 * @version:v1.0
 */
@Controller
@RequestMapping(Constants.MV_URI_MAIN_ADMIN+"/main")
public class MainController extends BaseFrameworkController{
	
	private static Log log= LogFactory.getLog(MainController.class);
	public static final String MV_VIEW_ADMIN="/admin";
	
	/**
	* @Description:退出
	* @author:jemmyee@gmail.com
	* @date:2010-10-20
	*/
	@RequestMapping("/exit")
	public String exit(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute(Constants.ONLINE_ADMIN_USER);
		//回到根目录，也就是首页
//		return "redirect:/";
		return "redirect:/entry/index";
	}
	
	/**
	* @Description:主框架
	* @author:jemmyee@gmail.com
	* @date:2010-10-20
	*/
	@RequestMapping("/frame")
	public ModelAndView frame(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName(MV_VIEW_ADMIN+"/main/frame");
		return mv;
	}
	
	/**
	* @Description:框架头部
	* @author:jemmyee@gmail.com
	* @date:2010-10-20
	*/
	@RequestMapping("/header")
	public ModelAndView top(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName(MV_VIEW_ADMIN+"/main/header");
		return mv;
	}
	/**
	* @Description:框架底部
	* @author:jemmyee@gmail.com
	* @date:2010-10-20
	*/
	@RequestMapping("/footer")
	public ModelAndView footer(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName(MV_VIEW_ADMIN+"/main/footer");
		return mv;
	}
	
	/**
	 * @Description:左侧菜单整体页面
	 * @author:jemmyee@gmail.com
	 * @date:2010-10-20
	 */
	@RequestMapping("/left/{app}")
	public ModelAndView left(@PathVariable String app,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.addObject("webroot", getWebRoot(request));
		mv.addObject("app",app);
		mv.setViewName(MV_VIEW_ADMIN+"/main/left");
		return mv;
	}		
	/**
	 * @Description:左侧菜单每个app的菜单
	 * @author:jemmyee@gmail.com
	 * @date:2010-10-20
	 */
	@RequestMapping("/left_sub/{app}")
	public ModelAndView leftSub(@PathVariable String app,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.addObject("webroot", getWebRoot(request));
		mv.addObject("app",app);
		mv.setViewName(MV_VIEW_ADMIN+"/main/left_"+app);
		return mv;
	}		
	
	/**
	* @Description:右侧欢迎页面
	* @author:jemmyee@gmail.com
	* @date:2010-10-20
	*/
	@RequestMapping("/right")
	public ModelAndView right(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName(MV_VIEW_ADMIN+"/main/right");
		return mv;
	}
	/**
	 * @Description:中间伸缩页面
	 * @author:jemmyee@gmail.com
	 * @date:2010-10-20
	 */
	@RequestMapping("/split")
	public ModelAndView split(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName(MV_VIEW_ADMIN+"/main/split");
		return mv;
	}

}
