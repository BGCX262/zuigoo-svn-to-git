package com.jemmyee.framework.web.controller;

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

import com.jemmyee.framework.dao.Page;
import com.jemmyee.framework.entity.AdminDept;
import com.jemmyee.framework.utils.FileUtils;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.controller.kernel.BaseFrameworkController;
import com.jemmyee.framework.web.utils.MathUtils;

@Controller
@RequestMapping(Constants.MV_URI_APP_CORE+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+Constants.ENTITY_ADMIN_DEPT)
public class AdminDeptController extends BaseFrameworkController {

	private static Log log = LogFactory.getLog(AdminDeptController.class);
	/* 分页查出保存的条件 */
	private static final String ENTITY_SESSION_BEAN = "admin_session_bean_"
			+ MathUtils.getRandomDouble();

	private static final String ROOT_VIEW =Constants.MV_URI_MAIN_ADMIN+Constants.MV_URI_APP_CORE+Constants.SYM_PATH_SEPARATOR+Constants.ENTITY_ADMIN_DEPT;
	private static final String ROOT_URI=Constants.MV_URI_APP_CORE+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+Constants.ENTITY_ADMIN_DEPT;

	@RequestMapping(Constants.MV_URI_COMM_TOADD)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		initToAdd(mv,ROOT_URI,ROOT_VIEW);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_TOEDIT + "/{id}")
	public ModelAndView toEdit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		AdminDept item = adminDeptService.findById(id);
		initToEdit(mv, item,ROOT_URI,ROOT_VIEW);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_ADD)
	@ResponseBody
	public Map<String, Object> add(@RequestBody AdminDept bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			AdminDept temp = null;
			if (adminDeptService.findByProperty("name", bean.getName()) != null
					&& adminDeptService.findByProperty("name", bean.getName())
							.size() > 0) {
				temp = adminDeptService.findByProperty("name", bean.getName())
						.get(0);
			}
			if (temp != null && temp.getName().equals(bean.getName().trim())) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_RENAME);
			} else {
				bean.setName(bean.getName().trim());
				bean.setAddTime(new Date());
				bean.setIsDelete((short) 0);
				Integer parentId = 1;
				if (request.getSession().getAttribute(Constants.ONLINE_DEPT_ID) != null) {
					parentId = Integer.parseInt(request.getSession()
							.getAttribute(Constants.ONLINE_DEPT_ID).toString());
				}
				bean.setParentId(parentId);
				adminDeptService.save(bean);
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
				//生成tree html文件
				String htmlFile=getAppRoot(request)+Constants.STATIC_PATH+Constants.TREE_HTML_DEPT;
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
	public Map<String, Object> edit(@RequestBody AdminDept bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			AdminDept temp = adminDeptService.findById(bean.getId());
			List<AdminDept> temps = adminDeptService.findByProperty("name",
					bean.getName().trim());
			if (temps.size() > 0
					&& !temps.get(0).getName().equals(temp.getName().trim())) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_RENAME);
			} else {
				temp.setName(bean.getName().trim());
				adminDeptService.update(temp);
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
				//生成tree html文件
				String htmlFile=getAppRoot(request)+Constants.STATIC_PATH+Constants.TREE_HTML_DEPT;
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
	public Map<String, Object> delete(@PathVariable Integer id,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
		try {
			adminDeptService.deleteById(id);
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
		List<Serializable> temps = new ArrayList<Serializable>();
		model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
		try {
			for (String id : ids.split("_")) {
				if (id != null && !id.equals("")) {
					temps.add(Integer.parseInt(id));
				}
			}
			adminDeptService.deleteBatch(temps);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = Constants.MV_URI_COMM_LIST
			+ "/{parentId}/{from}/{pageSize}/{startIndex}")
	public ModelAndView list(@PathVariable Integer parentId,@PathVariable String from,
			@PathVariable Integer pageSize,
			@PathVariable Integer startIndex, @ModelAttribute AdminDept bean,
			HttpServletRequest request) {
		DetachedCriteria dc = DetachedCriteria.forClass(AdminDept.class);
		AdminDept temp = new AdminDept();
		if (from.equals(Constants.PAGE_ENTRY_TYPE_FORM)) {
			temp = bean;
			request.getSession().setAttribute(ENTITY_SESSION_BEAN, bean);
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_PAGE)) {
			if (request.getSession().getAttribute(ENTITY_SESSION_BEAN) != null) {
				temp = (AdminDept) request.getSession().getAttribute(
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
		Page page = adminDeptService.findPageByCriteria(dc, pageSize,
				startIndex);
		if (pageSize != 0) {
			page.setPageSize(pageSize);
		}
		request.getSession().setAttribute(Constants.ONLINE_DEPT_ID,
				parentId == 0 ? 0 : parentId);
		mv.addObject("parentId", parentId);
		String pageURI=request.getSession().getServletContext().getAttribute(Constants.APPLICATION_APPROOT)+ROOT_URI+Constants.MV_URI_COMM_LIST+Constants.SYM_PATH_SEPARATOR+parentId;
		initList(mv,page,Constants.MV_VIEW_LIST,pageURI,ROOT_URI,temp,ROOT_VIEW,-1,pageSize,startIndex);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_FRAME + "/{from}")
	public ModelAndView frame(@PathVariable String from) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("from", from);
		mv.addObject(Constants.MV_OBJECT_APP,"core");
		mv.setViewName(ROOT_VIEW+ Constants.SYM_PATH_SEPARATOR+ Constants.MV_VIEW_FRAME);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_TREE + "/{from}")
	public ModelAndView tree(@PathVariable String from,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("from", from);
		mv.addObject(Constants.MV_OBJECT_APP, "core");
		mv.setViewName(ROOT_VIEW+Constants.SYM_PATH_SEPARATOR+ Constants.MV_VIEW_TREE);
		//判断html树文件是否存在 不存在就生成
		String htmlFile=getAppRoot(request)+Constants.STATIC_PATH+Constants.TREE_HTML_DEPT;
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
		List<AdminDept> entitys = adminDeptService
				.findByProperty("parentId", 0);
		String objName = "所有部门";
		StringBuffer sb = new StringBuffer();
		sb.append("<li id='10000'");
		sb.append("<a href='#' target='main'>" + objName + "</a><ul>");
		for (AdminDept entity : entitys) {
			List<AdminDept> entitysOne = adminDeptService.findByProperty(
					"parentId", entity.getId());
			if (entitysOne.size() > 0) {
				sb
						.append("<li href='#' id='" + entity.getId()
								+ "'><a target='main'>" + entity.getName()
								+ "</a><ul>");
				for (AdminDept entityOne : entitysOne) {
					// sb.append("<li href='#' id='" +
					// entityOne.getId()+"'><a target='main'>" +
					// entityOne.getName() + "</a></li>");
					List<AdminDept> entitysTwo = adminDeptService
							.findByProperty("parentId", entityOne.getId());
					if (entitysTwo.size() > 0) {
						sb.append("<li href='#' id='" + entityOne.getId()
								+ "'><a target='main'>" + entityOne.getName()
								+ "</a><ul>");
						for (AdminDept entityTwo : entitysTwo) {
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
	* @Description:生成tree html
	* @author:jemmyee@gmail.com
	* @date:2011-9-5
	*/
	public void createCategoryTreeHtml(HttpServletRequest request){
		List<AdminDept> entitys = adminDeptService.findByProperty("parentId",0);
		String objName="所有分类";
		StringBuffer sb = new StringBuffer();
		sb.append("<li href='#' id='0'");
		sb.append("<a target='main'>"+objName+"</a><ul>");
		for (AdminDept entity:entitys) {
			List<AdminDept> entitysOne=adminDeptService.findByProperty("parentId",entity.getId());
			if(entitysOne.size()>0)
			{   
				sb.append("<li href='#' id='" + entity.getId()+"'><a target='main'>" + entity.getName() + "</a><ul>");
				for(AdminDept entityOne:entitysOne)
				{
//					sb.append("<li href='#' id='" + entityOne.getId()+"'><a target='main'>" + entityOne.getName() + "</a></li>");
					List<AdminDept> entitysTwo=adminDeptService.findByProperty("parentId",entityOne.getId());
					if(entitysTwo.size()>0)
					{   
						sb.append("<li href='#' id='" + entityOne.getId()+"'><a target='main'>" + entityOne.getName() + "</a><ul>");
						for(AdminDept entityTwo:entitysTwo)
						{
							List<AdminDept> entitysThree=adminDeptService.findByProperty("parentId",entityTwo.getId());
							if(entitysThree.size()>0)
							{
								sb.append("<li href='#' id='" + entityTwo.getId()+"'><a target='main'>" + entityTwo.getName() + "</a><ul>");
								for(AdminDept entityThree:entitysThree)
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
		String htmlFile=getAppRoot(request)+Constants.STATIC_PATH+Constants.TREE_HTML_DEPT;
		FileUtils.writeFile(htmlFile,sb.toString());
	}

}
