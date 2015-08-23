package com.jemmyee.framework.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jemmyee.framework.entity.AdminUser;
import com.jemmyee.framework.utils.EncryptUtils;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.bean.ValidateCode;
import com.jemmyee.framework.web.controller.kernel.BaseFrameworkController;


/**
 * @Description:后台入口,不进行权限控制
 * @author:jemmyee@gmail.com
 * @date:2011-8-31
 * @version:v1.0
 */
@Controller
@RequestMapping("/entry")
public class EntryController extends BaseFrameworkController{
	
	private static Log log= LogFactory.getLog(EntryController.class);
	public static final String MV_VIEW_ADMIN="/admin";
	
	
//	/**
//	* @Description:系统分发,进入不同的应用
//	* @author:jemmyee@gmail.com
//	* @date:2011-8-4
//	*/
//	@RequestMapping("/d/{app}")
//	public String Dispatcher(@PathVariable String app,HttpServletRequest request,HttpServletResponse response){
//		String redirect="";
//		if(app.contains("console"))//后台登陆
//		{
//			request.getSession().setAttribute(Constants.ONLINE_APP,app);//后台显示哪个系统控制台
//			redirect="redirect:/admin/main/index";
//		}
//		if(app.equals("tao"))//淘宝导购
//		{
//			redirect="redirect:/tao/index/f/247/0";
//		}
//		if(app.equals("task"))//任务
//		{
//			redirect="redirect:/task/index";
//		}
//		if(app.equals("cms"))//任务
//		{
//			redirect="redirect:/cms/brand/index";
//		}
//		return redirect;
//	}
	
	/**
	* @Description:系统登录页
	* @author:jemmyee@gmail.com
	* @date:2010-10-20
	*/
	@RequestMapping("/index")
	public ModelAndView index(){
		   ModelAndView mv=new ModelAndView();
		   mv.setViewName(MV_VIEW_ADMIN+"/main/index");
		   return mv;
	}

	/**
	* @Description: TODO
	* @author:jemmyee@gmail.com
	* @param from  system 后台  front 前台
	* @date:2011-9-1
	*/
	@RequestMapping("/expired/{from}")
	public ModelAndView expired(@PathVariable String from){
		ModelAndView mv=new ModelAndView();
		mv.addObject("from",from);
		mv.setViewName("/common/expired");
		return mv;
	}
	
	/**
	* @Description:登录动作
	* @author:jemmyee@gmail.com
	* @date:2010-10-20
	*/
	@RequestMapping("/login")
	public ModelAndView login(@ModelAttribute("user")AdminUser user,@RequestParam String vcode,HttpServletRequest request)
	{   
		ModelAndView mv=new ModelAndView();
		//判断验证码是否正确
		String validateCode=request.getSession().getAttribute(Constants.SESSION_ADMIN_LOGIN_VALIDATE_CODE).toString();
		if(!vcode.equalsIgnoreCase(validateCode))
		{
			mv.addObject("loginError","验证码不正确");
			mv.addObject("name",user.getName());
			mv.addObject("password",user.getPassword());
			mv.setViewName(MV_VIEW_ADMIN+"/main/index");
			initLog(user.getName(),"[用户登录] name:"+user.getName()+"],status:vcode error", request);
			return mv;
		}
		
		
		List<AdminUser> users=adminUserService.findByProperty("name",user.getName());
		if(users!=null&&users.size()<1)//账户不存在
		{
			mv.addObject("loginError","登录失败,用户名不存在");
			initLog(user.getName(),"[用户登录] name:"+user.getName()+"],status:name not exsist", request);
			mv.setViewName(MV_VIEW_ADMIN+"/main/index");
		}else
		{
			AdminUser temp=users.get(0);
			if(temp.getPassword().equals(EncryptUtils.getMD5String(user.getPassword())))//验证通过
			{
				request.getSession().setAttribute(Constants.ONLINE_ADMIN_USER,temp);
				//进入一个中间301重定向页面，避免浏览器F5刷新登录
				mv.setViewName(MV_VIEW_ADMIN+"/main/redirect");
				//记录最后登录时间
				temp.setLastLoginTime(new Date());
				adminUserService.save(temp);
				initLog(user.getName(),"[用户登录] name:"+user.getName()+"],status:succ", request);
			}
			else//验证不通过,密码不正确
			{
				mv.addObject("loginError","密码不正确");
				mv.addObject("name",user.getName());
				mv.setViewName(MV_VIEW_ADMIN+"/main/index");
				initLog(user.getName(),"[用户登录] name:"+user.getName()+"],status:password error", request);
			}
		}
		try {
		} catch (Exception e) {
			log.error("errr:"+e.getMessage());
		}
		return mv;
	}

	/**
	* @Description:登录验证码
	* @author:jemmyee@gmail.com
	* @date:2011-5-3
	*/
	@RequestMapping("/validateCode")
	public String validateCode(HttpServletRequest request,HttpServletResponse response){
		    ValidateCode code = new ValidateCode();
	        // 禁止图像缓存
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentType("image/jpeg");
	        HttpSession session = request.getSession();

	        // 定义图像buffer
	        BufferedImage buffImg = new BufferedImage(code.getWidth(), code
	                .getHeight(), BufferedImage.TYPE_INT_RGB);
	        Graphics2D g = buffImg.createGraphics();

	        // 创建一个随机数生成器类
	        Random random = new Random();

	        // 将图像填充为白色
	        g.setColor(Color.WHITE);
	        g.fillRect(0, 0, code.getWidth(), code.getHeight() + 50);

	        // 创建字体，字体的大小应该根据图片的高度来定。
	        Font font = new Font("Fixedsys", Font.BOLD, code.getFontHeight());
	        // 设置字体
	        g.setFont(font);

	        // 画边框。
	        g.setColor(Color.BLACK);
	        g.drawRect(0, 0, 0, 0);

	        // 随机产生20条干扰线，使图象中的认证码不易被其它程序探测到。
	        g.setColor(Color.BLACK);
	        for (int i = 0; i < 20; i++) {
	            int x = random.nextInt(code.getWidth());
	            int y = random.nextInt(code.getHeight());
	            int xl = random.nextInt(1);
	            int yl = random.nextInt(1);
	            g.drawLine(x, y, x + xl, y + yl);
	        }

	        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
	        StringBuffer randomCode = new StringBuffer();
	        int red = 0, green = 0, blue = 0;

	        // 随机产生codeCount数字的验证码。
	        for (int i = 0; i < code.getCodeCount(); i++) {
	            // 得到随机产生的验证码数字。
	            String strRand = String.valueOf(ValidateCode.codeSequence[random
	                    .nextInt(ValidateCode.codeSequence.length)]);
	            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
	            red = random.nextInt(200);
	            green = random.nextInt(200);
	            blue = random.nextInt(200);

	            // 用随机产生的颜色将验证码绘制到图像中。
	            g.setColor(new Color(red, green, blue));
	            int point = 0;
	            if (i == 0)
	                point = 2;
	            else if (i == 1)
	                point = 18;
	            else if (i == 2)
	                point = 34;
	            else
	                point = 50;
	            g.drawString(strRand, point, code.getCodeY());

	            // 将产生的四个随机数组合在一起。
	            randomCode.append(strRand);
	        }
	        // 将四位数字的验证码保存到Session中。
	        session.setAttribute(Constants.SESSION_ADMIN_LOGIN_VALIDATE_CODE, randomCode.toString());

	        // 将图像输出到Servlet输出流中。
	        g.dispose();
	        ServletOutputStream sos;
			try {
				sos = response.getOutputStream();
				ImageIO.write(buffImg, "jpeg", sos);
			        sos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        return null;
	}
	

}
