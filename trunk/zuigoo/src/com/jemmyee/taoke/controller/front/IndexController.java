package com.jemmyee.taoke.controller.front;

import java.util.ArrayList;
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

import com.jemmyee.framework.dao.Page;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.utils.MathUtils;
import com.jemmyee.taoke.TaokeConstants;
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
@RequestMapping("") 
public class IndexController extends BaseTaokeFrontController{
	
	private static Log log= LogFactory.getLog(IndexController.class);
	/*分页查出保存的条件*/
	private static final String ENTITY_SESSION_BEAN="admin_session_bean_"+MathUtils.getRandomDouble();
	private static final String ROOT_VIEW=Constants.MV_URI_MAIN_FRONT;
	
	private static String TITLE="最购网_淘宝热卖导购_zuigoo.com";
	private static String KEYWORDS="淘宝热卖服装,淘宝热卖数码,淘宝热卖护肤品,淘宝热卖化妆品,淘宝热卖内衣,淘宝热卖童装";
	private static String DESCRIPTION="最购淘宝导购网是一个汇集了数万家知名淘宝皇冠店铺和100%好评店,为您精选淘宝最热价格最实惠,最优质的商品,让您享受淘宝乐趣,淘宝商城特卖,淘宝网热卖导购,淘宝聚划算尽在zuigoo.com最购网！";
	
	/**
	* @Description:默认首页
	* @author:jemmyee@gmail.com
	* @date:2011-8-4
	* @param from f跟目录  l最后一层
	*/
	@RequestMapping(Constants.MV_URI_COMM_INDEX+"/{from}/{cateId}/{startIndex}")
	public ModelAndView index(@PathVariable String from,@PathVariable Integer cateId,@PathVariable Integer startIndex,HttpServletRequest request){
		
		//seo设置
		String title="";
		String keyword="";
		String desc="";
		
		//产品
		DetachedCriteria dcitem=DetachedCriteria.forClass(TaokeItem.class);
		DetachedCriteria dccate=DetachedCriteria.forClass(TaokeCategory.class);
		ModelAndView mv=new ModelAndView();
		//推荐分类
		List<TaokeCategory> hotCates=new ArrayList<TaokeCategory>();
		//关联分类
		List<TaokeCategory> relationCates=new ArrayList<TaokeCategory>();
		//一级类型
		TaokeCategory rootCate=new TaokeCategory();
		//三级类型
		TaokeCategory lastCate=new TaokeCategory();
		boolean isAll=false;//判断是否全部
		if(from.equals("f"))
		{
			rootCate=taokeCategoryService.findById(cateId);//当前cate
			if(cateId.intValue()==TaokeConstants.DEFAULT_INDEX_CATEGORY_ID)
			{
				title=TITLE;
				keyword=KEYWORDS;
				desc=DESCRIPTION;
			}else{
				title="淘宝热卖"+rootCate.getName()+"_最购网";
				StringBuffer stc=new StringBuffer("");
				List<TaokeCategory> tcs=taokeCategoryService.findByProperty("parentId",rootCate.getId());
				for(TaokeCategory tc:tcs)
				{
					stc.append(tc.getName()+",");
				}
				keyword=stc.toString();
				desc=DESCRIPTION;
			}
			isAll=true;
			List<Integer> ints=new ArrayList<Integer>();
			
			//得到该cate所在的推荐的分类
			String sqlCate="SELECT id FROM taoke_category WHERE is_recommend=1 and parent_id IN (SELECT id FROM taoke_category WHERE parent_id="+cateId+")";
			ints=taokeCategoryService.getIds(sqlCate);
			dccate.add(Restrictions.in("id",ints));
			hotCates=taokeCategoryService.findByCriteria(dccate);
			
			//得到产品所在的分类  也是last分类
			String sqlItem="SELECT id FROM taoke_category WHERE parent_id IN (SELECT id FROM taoke_category WHERE parent_id="+cateId+")";
			ints=taokeCategoryService.getIds(sqlItem);
			dcitem.add(Restrictions.in("categoryId",ints));
			
			relationCates=hotCates;
			
		}
//		if(from.equals("s"))
//		{
//			isAll=true;
//			List<Integer> ints=new ArrayList<Integer>();
//			TaokeCategory cate=taokeCategoryService.findById(cateId);//当前cate  女装
//			secondCates=taokeCategoryService.findByProperty("parentId",cate.getParentId());
//			//last分类ids  也是产品的ids
//			String sqlCategory="SELECT id FROM taoke_category WHERE parent_id="+cateId;
//			ints=taokeCategoryService.getIds(sqlCategory);
//			dcitem.add(Restrictions.in("categoryId",ints));
//			
//			DetachedCriteria dclast=DetachedCriteria.forClass(TaokeCategory.class);
//			dclast.add(Restrictions.in("id",ints));
//			lastCates=taokeCategoryService.findByCriteria(dclast);
//			
//			rootCate=taokeCategoryService.findById(cate.getParentId());
//			secondCate=cate;
//		}
		if(from.equals("l"))
		{   
			lastCate=taokeCategoryService.findById(cateId);//当前cate 
			title="2011淘宝热卖"+lastCate.getName()+"排行榜_"+"最购网";
			StringBuffer stc=new StringBuffer("");
			List<TaokeCategory> tcs=taokeCategoryService.findByProperty("parentId",lastCate.getParentId());
			for(TaokeCategory tc:tcs)
			{
				stc.append(tc.getName()+",");
			}
			keyword=stc.toString();
			desc=DESCRIPTION;
			if(cateId!=0)//0表示全部
			{
				dcitem.add(Restrictions.eq("categoryId",cateId));
			}else
			{
				isAll=true;
			}
			
			TaokeCategory centerCate=taokeCategoryService.findById(lastCate.getParentId());//
			rootCate=taokeCategoryService.findById(centerCate.getParentId());//
			
			List<Integer> ints=new ArrayList<Integer>();
			//得到该cate所在的推荐的分类
			String sqlCate="SELECT id FROM taoke_category WHERE is_recommend=1 and parent_id IN (SELECT id FROM taoke_category WHERE parent_id="+rootCate.getId()+")";
			ints=taokeCategoryService.getIds(sqlCate);
			dccate.add(Restrictions.in("id",ints));
			hotCates=taokeCategoryService.findByCriteria(dccate);
			
			relationCates=taokeCategoryService.findByProperty("parentId",lastCate.getParentId());
			
		}
//		dcitem.addOrder(Order.desc("addTime"));
//		dcitem.addOrder(Order.desc("commissionNum"));
	    Page page=taokeItemService.findPageByCriteria(dcitem,9,startIndex);
	    mv.addObject(Constants.MV_OBJECT_PAGE, page);
		mv.addObject("webroot", getWebRoot(request));
		mv.addObject("rootCate",rootCate);
		mv.addObject("lastCate",lastCate);
		mv.addObject("relationCates",relationCates);
		mv.addObject("hotCates",hotCates);
		mv.addObject("cateId",cateId);//分页需要
		mv.addObject("isAll",isAll);
		mv.addObject("startIndex",startIndex);
		mv.addObject("from",from);//来源
		mv.addObject("title",title);
		mv.addObject("keywords",keyword);
		mv.addObject("description",desc);
		mv.setViewName(ROOT_VIEW+Constants.MV_URI_COMM_INDEX); 
		return mv;
	}
	@RequestMapping("/r/{from}/{id}")
	public ModelAndView goitem(@PathVariable String from,@PathVariable int id,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		TaokeItem item=taokeItemService.findById(id);
		mv.addObject("item", item);
		mv.addObject("from", from);
		mv.setViewName(ROOT_VIEW+"/redirect"); 
		return mv;
	}
	@RequestMapping("/header")
	public ModelAndView member(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName(ROOT_VIEW+"/header"); 
		return mv;
	}
	@RequestMapping("/footer")
	public ModelAndView footer(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName(ROOT_VIEW+"/footer"); 
		return mv;
	}
	/**
	* @Description:浮动banner
	* @author:jemmyee@gmail.com
	* @date:2011-8-9
	*/
	@RequestMapping("/float")
	public ModelAndView floatBanner(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName(ROOT_VIEW+"/float"); 
		return mv;
	}
	/**
	 * @Description:浮动banner
	 * @author:jemmyee@gmail.com
	 * @date:2011-8-9
	 */
	@RequestMapping("/float2/{from}")
	public ModelAndView floatBanner2(@PathVariable String from,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		//一级目录级别
		DetachedCriteria dccate=DetachedCriteria.forClass(TaokeCategory.class);
		dccate.add(Restrictions.eq("parentId",0));
		dccate.addOrder(Order.asc("sortOrder"));
		mv.addObject("cates",taokeCategoryService.findByCriteria(dccate));
		mv.addObject("defaultId", TaokeConstants.DEFAULT_INDEX_CATEGORY_ID);
		mv.addObject("from",from);
		mv.setViewName(ROOT_VIEW+"/float2"); 
		return mv;
	}
	/**
	 * @Description:目录
	 * @author:jemmyee@gmail.com
	 * @date:2011-8-9
	 */
	@RequestMapping("/cate/{from}/{cateId}")
	public ModelAndView cate(@PathVariable String from,@PathVariable Integer cateId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		List<Integer> ints=new ArrayList<Integer>();
		DetachedCriteria dclast=DetachedCriteria.forClass(TaokeCategory.class);
		//得到产品所在的分类  也是last分类
		String sqlPrudoct="SELECT id FROM taoke_category WHERE parent_id="+cateId;
		ints=taokeCategoryService.getIds(sqlPrudoct);
		dclast.add(Restrictions.in("parentId",ints));
		mv.addObject("scates",taokeCategoryService.findByProperty("parentId", cateId));
		mv.addObject("lcates",taokeCategoryService.findByCriteria(dclast));
		mv.addObject("cate",taokeCategoryService.findById(cateId));
		mv.addObject("from",from);
		mv.setViewName(ROOT_VIEW+"/cate"); 
		return mv;
	}
	
	/**
	 * @Description:中间广告
	 * @author:jemmyee@gmail.com
	 * @date:2011-8-9
	 */
	@RequestMapping("/ad")
	public ModelAndView ad(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName(ROOT_VIEW+"/ad"); 
		return mv;
	}
	
	@RequestMapping("/menu")
	public ModelAndView menu(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName(ROOT_VIEW+"/menu"); 
		return mv;
	}
	
}
