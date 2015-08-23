package com.jemmyee.taoke.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jemmyee.cms.entity.Article;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.utils.MathUtils;
import com.jemmyee.taoke.controller.kernel.BaseTaokeFrontController;
import com.jemmyee.taoke.entity.TaokeCategory;
import com.jemmyee.taoke.entity.TaokeItem;


/**
 * @Description:前台Controller
 * @author:jemmyee@gmail.com
 * @date:2011-8-4
 * @version:v1.0
 */
@Controller
@RequestMapping("/single") 
public class SingleController extends BaseTaokeFrontController{
	
	private static Log log= LogFactory.getLog(SingleController.class);
	/*分页查出保存的条件*/
	private static final String ENTITY_SESSION_BEAN="admin_session_bean_"+MathUtils.getRandomDouble();
	private static final String ROOT_VIEW=Constants.MV_URI_MAIN_FRONT+"/single";
	private static final String ROOT_URI=Constants.SYM_PATH_SEPARATOR+"single";
	
	private static String TITLE="最购网_淘宝热卖导购_zuigoo.com";
	private static String KEYWORDS="淘宝热卖服装,淘宝热卖数码,淘宝热卖护肤品,淘宝热卖化妆品,淘宝热卖内衣,淘宝热卖童装";
	private static String DESCRIPTION="最购淘宝导购网是一个汇集了数万家知名淘宝皇冠店铺和100%好评店,为您精选淘宝最热价格最实惠,最优质的商品,让您享受淘宝乐趣,淘宝商城特卖,淘宝网热卖导购,淘宝聚划算尽在zuigoo.com最购网！";
	
	
	/**
	* @Description:单站推广首页
	* @author:jemmyee@gmail.com
	* @date:2011-10-10
	* @param from f跟目录  l最后一层
	*/
	@RequestMapping("/index/{from}/{cid}")
	public ModelAndView index(@PathVariable String from,@PathVariable Integer cid,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		if(from.equals("f"))//根目录
		{
			List<TaokeCategory> cates=taokeCategoryService.findByProperty("parentId", cid);
			TaokeCategory cate=cates.get(0);
			cid=taokeCategoryService.findByProperty("parentId",cate.getId()).get(0).getId();
		}
		//seo设置
		String title="";
		String keyword="";
		String desc=DESCRIPTION;
		TaokeCategory nowcate=taokeCategoryService.findById(cid);
		if(nowcate.getSeoTitle()!=null&&!nowcate.getSeoTitle().equals(""))
		{
			title=nowcate.getSeoTitle();
		}else{
			title="淘宝最热销的"+nowcate.getName()+"-最流行效果最好的"+nowcate.getName()+"-最热门的"+nowcate.getName()+"品牌-性价比最高的"+nowcate.getName();
		}
		if(nowcate.getSeoKeyword()!=null&&!nowcate.getSeoKeyword().equals(""))
		{
			keyword=nowcate.getSeoKeyword();
		}else{
			keyword="淘宝最热销的"+nowcate.getName()+"-最流行效果最好的"+nowcate.getName()+"-最热门的"+nowcate.getName()+"品牌-性价比最高的"+nowcate.getName();
		}
		if(nowcate.getSeoDesc()!=null&&!nowcate.getSeoDesc().equals(""))
		{
			desc=nowcate.getSeoDesc();
		}else{
			desc="淘宝最畅销,性价比最高,最流行的"+nowcate.getName()+",本站为您精选淘宝最好的,100%好评的"+nowcate.getName()+"产品";
		}
		List<Article> articles=articleService.findByProperty("categoryId",cid);
		mv.addObject("cid",cid);
		mv.addObject("nowcate",nowcate);
		mv.addObject("title",title);
		mv.addObject("keywords",keyword);
		mv.addObject("description",desc);
		mv.addObject("articles",articles);
		//需要得到顶级cid
		TaokeCategory father=taokeCategoryService.findById(nowcate.getParentId());
		mv.addObject("rid",father.getParentId());
		
		initView(mv, ROOT_URI,ROOT_VIEW+"/index");
		return mv;
	}
	
	
	@RequestMapping("/hots/{cid}")
	public ModelAndView member(@PathVariable Integer cid,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		//当前栏目
		TaokeCategory nowCate=taokeCategoryService.findById(cid);
		//关联栏目   同一个父类
		List<TaokeCategory> reCates=taokeCategoryService.findByProperty("parentId",nowCate.getParentId());
		//本栏目的推广
		DetachedCriteria dc = DetachedCriteria.forClass(TaokeItem.class);
		dc.add(Restrictions.eq("categoryId", nowCate.getId()));
		dc.addOrder(Order.desc("volume"));
		mv.addObject("nowcate",nowCate);
		mv.addObject("recates",reCates);
		mv.addObject("items",taokeItemService.findByCriteria(dc));
		mv.setViewName(ROOT_VIEW+"/hots"); 
		return mv;
	}
}
