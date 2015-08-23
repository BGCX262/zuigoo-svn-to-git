package com.jemmyee.taoke.controller.front;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jemmyee.cms.CmsConstants;
import com.jemmyee.cms.entity.Article;
import com.jemmyee.framework.dao.Page;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.utils.MathUtils;
import com.jemmyee.taoke.controller.kernel.BaseTaokeFrontController;
import com.jemmyee.taoke.entity.TaokeCategory;
import com.jemmyee.taoke.entity.TaokeItem;

/**
 * @Description:article前台Controller
 * @author:jemmyee@gmail.com
 * @date:2011-8-4
 * @version:v1.0
 */
@Controller("articleFrontController")
@RequestMapping(Constants.SYM_PATH_SEPARATOR+CmsConstants.ENTITY_ARTICLE) 
public class ArticleController extends BaseTaokeFrontController{
	
	private static Log log= LogFactory.getLog(ArticleController.class);
	/*分页查出保存的条件*/
	private static final String ENTITY_SESSION_BEAN="admin_session_bean_"+MathUtils.getRandomDouble();
	private static final String ROOT_VIEW=Constants.MV_URI_MAIN_FRONT+Constants.SYM_PATH_SEPARATOR+CmsConstants.ENTITY_ARTICLE;
	private static final String ROOT_URI=Constants.SYM_PATH_SEPARATOR+CmsConstants.ENTITY_ARTICLE;
	
	@RequestMapping("/detail/{id}")
	public ModelAndView info(@PathVariable String id,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		Article article=articleService.findById(id);
		//当前栏目
		TaokeCategory nowCate=taokeCategoryService.findById(article.getCategoryId());
		mv.addObject("nowCategory",nowCate);
		mv.addObject("item",article);
		//本栏目的推广
		DetachedCriteria dc = DetachedCriteria.forClass(TaokeItem.class);
		dc.add(Restrictions.eq("categoryId", nowCate.getId()));
		dc.addOrder(Order.desc("volume"));
		mv.addObject("items",taokeItemService.findByCriteria(dc));
		
		initView(mv, ROOT_URI,ROOT_VIEW+"/detail");
		return mv;
	}
	/**
	* @Description:右边公共部分
	* @author:jemmyee@gmail.com
	* @date:2011-9-20
	*/
	@RequestMapping("/right")
	public ModelAndView right(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		//我是买家
		mv.addObject("buyers",articleService.findByProperty("categoryId",3)); 
		//我是卖家
		mv.addObject("sales",articleService.findByProperty("categoryId",4)); 
		initView(mv, ROOT_URI,ROOT_VIEW+"/right");
		return mv;
	}
	
	@RequestMapping(value = Constants.MV_URI_COMM_LIST+ "/{categoryId}/{from}/{pageSize}/{startIndex}")
	public ModelAndView list(@PathVariable Integer categoryId, @PathVariable String from,
			@PathVariable Integer pageSize,
			@PathVariable Integer startIndex, @ModelAttribute Article bean,
			HttpServletRequest request) {
		DetachedCriteria dc = DetachedCriteria.forClass(Article.class);
		ModelAndView mv = new ModelAndView();
		Article temp = new Article();
		if (from.equals(Constants.PAGE_ENTRY_TYPE_FORM)) {
			temp = bean;
			request.getSession().setAttribute(ENTITY_SESSION_BEAN, bean);
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_PAGE)) {
			if (request.getSession().getAttribute(ENTITY_SESSION_BEAN) != null) {
				temp = (Article) request.getSession().getAttribute(
						ENTITY_SESSION_BEAN);
			}
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_MENU)) {

		}
		if (categoryId != 0) {
			dc.add(Restrictions.eq("categoryId", categoryId));
		}
		if (pageSize == 0) {
			pageSize = Constants.DEFAULT_PAGESIZE;
		}
		if (bean.getBkw() != null && !bean.getBkw().equals("")) {
			dc.add(Restrictions.like("title", "%" + bean.getBkw() + "%"));
		}
		if (bean.getStartTime() != null && bean.getEndTime() != null) {
			dc.add(Restrictions.ge("addTime", bean.getStartTime()));
			dc.add(Restrictions.le("addTime", bean.getEndTime()));
		}
		if (bean.getStartTime() != null && bean.getEndTime() == null) {
			dc.add(Restrictions.ge("addTime", bean.getStartTime()));
		}
		if (bean.getStartTime() == null && bean.getEndTime() != null) {
			dc.add(Restrictions.le("addTime", bean.getEndTime()));
		}
		dc.addOrder(Order.desc("addTime"));
		Page page = articleService.findPageByCriteria(dc, pageSize, startIndex);
		if (pageSize != 0) {
			page.setPageSize(pageSize);
		}
		mv.addObject("nowCategory",taokeCategoryService.findById(categoryId));
		temp.setHightLight(Constants.HIGHTLIGHT_PREFIX + bean.getBkw()
				+ Constants.HIGHTLIGHT_SUFFIX);
		String pageURI=request.getSession().getServletContext().getAttribute(Constants.APPLICATION_APPROOT)+ROOT_URI+Constants.MV_URI_COMM_LIST+Constants.SYM_PATH_SEPARATOR+categoryId;
		initList(mv,page,Constants.MV_VIEW_LIST,pageURI,ROOT_URI,temp,ROOT_VIEW,-1,pageSize,startIndex);
		return mv;
	}

}
