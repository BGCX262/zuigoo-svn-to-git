package com.jemmyee.cms.controller.admin;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jemmyee.cms.CmsConstants;
import com.jemmyee.cms.controller.kernel.BaseCmsController;
import com.jemmyee.cms.entity.ArticleCategory;
import com.jemmyee.framework.dao.Page;
import com.jemmyee.framework.utils.FileUtils;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.utils.MathUtils;

@Controller("articleCategoryController")
@RequestMapping(Constants.MV_URI_APP_CMS+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+CmsConstants.ENTITY_ARTICLE_CATEGORY)
public class ArticleCategoryController extends BaseCmsController {

	private static Log log = LogFactory.getLog(ArticleCategoryController.class);
	/* 分页查出保存的条件 */
	private static final String ENTITY_SESSION_BEAN = "admin_session_bean_"
			+ MathUtils.getRandomDouble();
	
	private static final String ROOT_VIEW =Constants.MV_URI_MAIN_ADMIN+Constants.MV_URI_APP_CMS+Constants.SYM_PATH_SEPARATOR+CmsConstants.ENTITY_ARTICLE_CATEGORY;
	private static final String ROOT_URI=Constants.MV_URI_APP_CMS+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+CmsConstants.ENTITY_ARTICLE_CATEGORY;

	@RequestMapping(Constants.MV_URI_COMM_TOADD)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		initToAdd(mv,ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_TOEDIT + "/{id}")
	public ModelAndView toEdit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		ArticleCategory item = articleCategoryService.findById(id);
		initToEdit(mv, item,ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_ADD)
	@ResponseBody
	public Map<String, Object> add(@RequestBody ArticleCategory bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		ArticleCategory temp = null;
		try {
			if (articleCategoryService.findByProperty("name", bean.getName()) != null
					&& articleCategoryService
							.findByProperty("name", bean.getName()).size() > 0) {
				temp = articleCategoryService
						.findByProperty("name", bean.getName()).get(0);
			}
			if (temp != null && temp.getName().equals(bean.getName().trim())) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_RENAME);
			} else {
				bean.setName(bean.getName().trim());
				bean.setAddTime(new Date());
				bean.setIsDelete((short) 0);
				Integer parentId = 0;
				if (request.getSession().getAttribute(
						CmsConstants.ONLINE_ARTICLE_CATEGORY_ID) != null) {
					parentId = Integer.parseInt(request.getSession().getAttribute(
							CmsConstants.ONLINE_ARTICLE_CATEGORY_ID).toString());
				}
				bean.setParentId(parentId);
				articleCategoryService.save(bean);
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
				//生成tree html文件
				String htmlFile=getAppRoot(request)+Constants.STATIC_PATH+CmsConstants.TREE_HTML_ARTICLE_CATEGORY;
				createCategoryTreeHtml(request);
			}
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		
		return model;
	}

	@RequestMapping(Constants.MV_URI_COMM_EDIT)
	@ResponseBody
	public Map<String, Object> edit(@RequestBody ArticleCategory bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			ArticleCategory temp = articleCategoryService
					.findById(bean.getId());
			List<ArticleCategory> temps = articleCategoryService
					.findByProperty("name", bean.getName().trim());
			if (temps.size() > 0
					&& !temps.get(0).getName().equals(temp.getName().trim())) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_RENAME);
			} else {
				temp.setName(bean.getName().trim());
				temp.setIsLast(bean.getIsLast());
				temp.setSeoTitle(bean.getSeoTitle());
				temp.setSeoKeyword(bean.getSeoKeyword());
				temp.setSeoDesc(bean.getSeoDesc());
				temp.setIsShowInNav(bean.getIsShowInNav());
				articleCategoryService.update(temp);
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
				//生成tree html文件
				String htmlFile=getAppRoot(request)+Constants.STATIC_PATH+CmsConstants.TREE_HTML_ARTICLE_CATEGORY;
				createCategoryTreeHtml(request);
			}
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}

		return model;
	}

	@RequestMapping(Constants.MV_URI_COMM_DELETE + "/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable String id,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
		try {
			articleCategoryService.deleteById(id);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}

	@RequestMapping(Constants.MV_URI_COMM_DELETBATCH + "/{ids}")
	@ResponseBody
	public Map<String, Object> deleteBatch(@PathVariable String ids,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
		List<Serializable> temps = new ArrayList<Serializable>();
		try {
			for (String id : ids.split("_")) {
				if (id != null && !id.equals("")) {
					temps.add(Integer.parseInt(id));
				}
			}
			articleCategoryService.deleteBatch(temps);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}

	@RequestMapping(value = Constants.MV_URI_COMM_LIST
			+ "/{parentId}/{from}/{pageSize}/{startIndex}")
	public ModelAndView list(@PathVariable Integer parentId, @PathVariable String from,
			@PathVariable Integer pageSize,
			@PathVariable Integer startIndex,
			@ModelAttribute ArticleCategory bean, HttpServletRequest request) {
		DetachedCriteria dc = DetachedCriteria.forClass(ArticleCategory.class);
		ArticleCategory temp = new ArticleCategory();
		if (from.equals(Constants.PAGE_ENTRY_TYPE_FORM)) {
			temp = bean;
			request.getSession().setAttribute(ENTITY_SESSION_BEAN, bean);
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_PAGE)) {
			if (request.getSession().getAttribute(ENTITY_SESSION_BEAN) != null) {
				temp = (ArticleCategory) request.getSession().getAttribute(
						ENTITY_SESSION_BEAN);
			}
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_MENU)) {

		}
		if (parentId != 0) {
			dc.add(Restrictions.eq("parentId", parentId));
		}
		dc.addOrder(Order.desc("addTime"));
		ModelAndView mv = new ModelAndView();
		if (pageSize == 0) {
			pageSize = Constants.DEFAULT_PAGESIZE;
		}
		Page page = articleCategoryService.findPageByCriteria(dc, pageSize,
				startIndex);
		if (pageSize != 0) {
			page.setPageSize(pageSize);
		}
		request.getSession().setAttribute(
				CmsConstants.ONLINE_ARTICLE_CATEGORY_ID,
				parentId == 0 ? 0 : parentId);
		temp.setHightLight(Constants.HIGHTLIGHT_PREFIX + bean.getBkw()
				+ Constants.HIGHTLIGHT_SUFFIX);
		String pageURI=request.getSession().getServletContext().getAttribute(Constants.APPLICATION_APPROOT)+ROOT_URI+Constants.MV_URI_COMM_LIST+Constants.SYM_PATH_SEPARATOR+parentId;
		initList(mv,page,Constants.MV_VIEW_LIST,pageURI,ROOT_URI,temp,ROOT_VIEW,-1,pageSize,startIndex);
		return mv;
	}

	/**
	 * 生成目录树
	 * 
	 * @return
	 */
	@RequestMapping("/treexml")
	@ResponseBody
	public String treeXml(HttpServletRequest request) {
		List<ArticleCategory> cates = articleCategoryService.findAll();
		String filePath = getAppRoot(request) + Constants.TREE_XML_FILE_PATH
				+ File.separator + Constants.TREE_XML_FILE_NAME_DEPT;
		// TreeXmlUtils.createArticleCategoryTreeXml(filePath, cates);
		return "ok";
	}

	@RequestMapping(Constants.MV_URI_COMM_FRAME + "/{from}")
	public ModelAndView frame(@PathVariable String from) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("from", from);
		mv.addObject(Constants.MV_OBJECT_APP,"cms");
		mv.setViewName(ROOT_VIEW +Constants.SYM_PATH_SEPARATOR+ Constants.MV_VIEW_FRAME);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_TREE + "/{from}")
	public ModelAndView tree(@PathVariable String from,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("from", from);
		mv.addObject(Constants.MV_OBJECT_APP, "cms");
		mv.setViewName(ROOT_VIEW +Constants.SYM_PATH_SEPARATOR+ Constants.MV_VIEW_TREE);
		//判断html树文件是否存在 不存在就生成
		String htmlFile=getAppRoot(request)+Constants.STATIC_PATH+CmsConstants.TREE_HTML_ARTICLE_CATEGORY;
		if(!FileUtils.isFileExist(htmlFile))
		{
			createCategoryTreeHtml(request);
		}
		return mv;
	}

	@RequestMapping("/parse_tree")
	@ResponseBody
	public Map<String, Object> parseTree(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<ArticleCategory> entitys = articleCategoryService.findByProperty(
				"parentId", 0);
		String objName = "所有栏目";
		StringBuffer sb = new StringBuffer();
		sb.append("<li id='0'");
		sb.append("<a href='#' target='main'>" + objName + "</a><ul>");
		for (ArticleCategory entity : entitys) {
			List<ArticleCategory> entitysOne = articleCategoryService
					.findByProperty("parentId", entity.getId());
			if (entitysOne.size() > 0) {
				sb
						.append("<li href='#' id='" + entity.getId()
								+ "'><a target='main'>" + entity.getName()
								+ "</a><ul>");
				for (ArticleCategory entityOne : entitysOne) {
					// sb.append("<li href='#' id='" +
					// entityOne.getId()+"'><a target='main'>" +
					// entityOne.getName() + "</a></li>");
					List<ArticleCategory> entitysTwo = articleCategoryService
							.findByProperty("parentId", entityOne.getId());
					if (entitysTwo.size() > 0) {
						sb.append("<li href='#' id='" + entityOne.getId()
								+ "'><a target='main'>" + entityOne.getName()
								+ "</a><ul>");
						for (ArticleCategory entityTwo : entitysTwo) {
							sb.append("<li href='#' id='" + entityTwo.getId()
									+ "'><a target='main'>"
									+ entityTwo.getName() + "</a></li>");
						}
						sb.append("</ul></li>");
					} else {
						sb.append("<li href='#' id='" + entityOne.getId()
								+ "'><a target='main'>" + entityOne.getName()
								+ "</a></li>");
					}

				}
				sb.append("</ul></li>");
			} else {
				sb.append("<li href='#' id='" + entity.getId()
						+ "'><a target='main'>" + entity.getName()
						+ "</a></li>");
			}
		}
		sb.append("</ul></li>");
		model.put("tree", sb.toString());
		return model;
	}
	
	/**
	* @Description:生成html树
	* @author:jemmyee@gmail.com
	* @date:2011-9-5
	*/
	public void createCategoryTreeHtml(HttpServletRequest request){
		List<ArticleCategory> entitys = articleCategoryService.findByProperty("parentId",0);
		String objName="所有分类";
		StringBuffer sb = new StringBuffer();
		sb.append("<li href='#' id='0'");
		sb.append("<a target='main'>"+objName+"</a><ul>");
		for (ArticleCategory entity:entitys) {
			List<ArticleCategory> entitysOne=articleCategoryService.findByProperty("parentId",entity.getId());
			if(entitysOne.size()>0)
			{   
				sb.append("<li href='#' id='" + entity.getId()+"'><a target='main'>" + entity.getName() + "</a><ul>");
				for(ArticleCategory entityOne:entitysOne)
				{
//					sb.append("<li href='#' id='" + entityOne.getId()+"'><a target='main'>" + entityOne.getName() + "</a></li>");
					List<ArticleCategory> entitysTwo=articleCategoryService.findByProperty("parentId",entityOne.getId());
					if(entitysTwo.size()>0)
					{   
						sb.append("<li href='#' id='" + entityOne.getId()+"'><a target='main'>" + entityOne.getName() + "</a><ul>");
						for(ArticleCategory entityTwo:entitysTwo)
						{
							List<ArticleCategory> entitysThree=articleCategoryService.findByProperty("parentId",entityTwo.getId());
							if(entitysThree.size()>0)
							{
								sb.append("<li href='#' id='" + entityTwo.getId()+"'><a target='main'>" + entityTwo.getName() + "</a><ul>");
								for(ArticleCategory entityThree:entitysThree)
								{
									sb.append("<li href='#' id='" + entityThree.getId()+"'><a target='main'>" + entityThree.getName() + "</a></li>");	
								}
								sb.append("</ul></li>");
							}else{
								sb.append("<li href='#' id='" + entityTwo.getId()+"'><a target='main'>" + entityTwo.getName() + "</a></li>");
							}
							
						}
						sb.append("</ul></li>");
					}else{
						sb.append("<li href='#' id='" + entityOne.getId()+"'><a target='main'>" + entityOne.getName() + "</a></li>");
					}
					
				}
				sb.append("</ul></li>");
			}else{
				sb.append("<li href='#' id='" + entity.getId()+"'><a target='main'>" + entity.getName() + "</a></li>");
			}
		}
		sb.append("</ul></li>");
		String htmlFile=getAppRoot(request)+Constants.STATIC_PATH+CmsConstants.TREE_HTML_ARTICLE_CATEGORY;
		FileUtils.writeFile(htmlFile,sb.toString());
	}


}
