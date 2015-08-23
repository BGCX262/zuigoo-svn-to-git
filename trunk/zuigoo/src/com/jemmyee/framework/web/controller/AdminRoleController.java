package com.jemmyee.framework.web.controller;

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
import com.jemmyee.framework.entity.AdminRole;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.controller.kernel.BaseFrameworkController;
import com.jemmyee.framework.web.utils.MathUtils;

@Controller
@RequestMapping(Constants.MV_URI_APP_CORE + Constants.MV_URI_MAIN_ADMIN
		+Constants.SYM_PATH_SEPARATOR+ Constants.ENTITY_ADMIN_ROLE)
public class AdminRoleController extends BaseFrameworkController {

	private static Log log = LogFactory.getLog(AdminRoleController.class);
	/* 分页查出保存的条件 */
	private static final String ENTITY_SESSION_BEAN = "admin_session_bean_"
			+ MathUtils.getRandomDouble();

	private static final String ROOT_VIEW = Constants.MV_URI_MAIN_ADMIN
			+ Constants.MV_URI_APP_CORE +Constants.SYM_PATH_SEPARATOR
			+ Constants.ENTITY_ADMIN_ROLE;
	private static final String ROOT_URI = Constants.MV_URI_APP_CORE
			+ Constants.MV_URI_MAIN_ADMIN +Constants.SYM_PATH_SEPARATOR
			+ Constants.ENTITY_ADMIN_ROLE;

	@RequestMapping(Constants.MV_URI_COMM_TOADD)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView();
		initToAdd(mv, ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_TOEDIT + "/{id}")
	public ModelAndView toEdit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		AdminRole item = adminRoleService.findById(id);
		initToEdit(mv, item, ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_ADD)
	@ResponseBody
	public Map<String, Object> add(@RequestBody AdminRole bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		AdminRole temp = null;
		try {
			if (adminRoleService.findByProperty("name", bean.getName()) != null
					&& adminRoleService.findByProperty("name", bean.getName())
							.size() > 0) {
				temp = adminRoleService.findByProperty("name", bean.getName())
						.get(0);
			}
			if (temp != null && temp.getName().equals(bean.getName().trim())) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_RENAME);
			} else {
				bean.setName(bean.getName().trim());
				bean.setAddTime(new Date());
				bean.setIsDelete((short) 0);
				bean.setRights("0");
				bean.setDeptId(getSessionValue(Constants.ONLINE_DEPT_ID,
						request) == null ? 1 : Integer
						.parseInt(getSessionValue(Constants.ONLINE_DEPT_ID,
								request).toString()));
				adminRoleService.save(bean);
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
			}
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}

		return model;
	}

	@RequestMapping(Constants.MV_URI_COMM_EDIT)
	@ResponseBody
	public Map<String, Object> edit(@RequestBody AdminRole bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		AdminRole temp = adminRoleService.findById(bean.getId());
		try {
			List<AdminRole> temps = adminRoleService.findByProperty("name",
					bean.getName().trim());
			if (temps.size() > 0
					&& !temps.get(0).getName().equals(temp.getName().trim())) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_RENAME);
			} else {
				temp.setName(bean.getName().trim());
				adminRoleService.update(temp);
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
			}
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
		}
		return model;
	}

	@RequestMapping(Constants.MV_URI_COMM_DELETE + "/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable String id,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			adminRoleService.deleteById(id);
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
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
					temps.add(id);
				}
			}
			try {
				adminRoleService.deleteBatch(temps);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}

	@RequestMapping(value = Constants.MV_URI_COMM_LIST
			+ "/{parentId}/{from}/{pageSize}/{startIndex}")
	public ModelAndView list(@PathVariable Integer parentId,@PathVariable String from,
			 @PathVariable Integer pageSize,
			@PathVariable Integer startIndex, @ModelAttribute AdminRole bean,
			HttpServletRequest request) {
		DetachedCriteria dc = DetachedCriteria.forClass(AdminRole.class);
		AdminRole temp = new AdminRole();
		if (from.equals(Constants.PAGE_ENTRY_TYPE_FORM)) {
			temp = bean;
			request.getSession().setAttribute(ENTITY_SESSION_BEAN, bean);
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_PAGE)) {
			if (request.getSession().getAttribute(ENTITY_SESSION_BEAN) != null) {
				temp = (AdminRole) request.getSession().getAttribute(
						ENTITY_SESSION_BEAN);
			}
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_MENU)) {

		}
		dc.addOrder(Order.desc("addTime"));
		if (parentId != 0) {
			dc.add(Restrictions.eq("deptId", parentId));
		}
		ModelAndView mv = new ModelAndView();
		if (pageSize == 0) {
			pageSize = Constants.DEFAULT_PAGESIZE;
		}
		Page page = adminRoleService.findPageByCriteria(dc, pageSize,
				startIndex);
		if (pageSize != 0) {
			page.setPageSize(pageSize);
		}
		request.getSession().setAttribute(Constants.ONLINE_DEPT_ID,
				parentId == 0 ?0 : parentId);
		String pageURI = request.getSession().getServletContext().getAttribute(Constants.APPLICATION_APPROOT) + ROOT_URI
				+ Constants.MV_URI_COMM_LIST + Constants.SYM_PATH_SEPARATOR
				+ parentId;
		initList(mv, page, Constants.MV_VIEW_LIST, pageURI, ROOT_URI, temp,
				ROOT_VIEW, -1, pageSize, startIndex);
		return mv;
	}
}
