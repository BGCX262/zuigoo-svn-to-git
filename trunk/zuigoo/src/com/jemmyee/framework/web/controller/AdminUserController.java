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
import com.jemmyee.framework.entity.AdminUser;
import com.jemmyee.framework.utils.EncryptUtils;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.controller.kernel.BaseFrameworkController;
import com.jemmyee.framework.web.utils.MathUtils;

/**
 * @Description:用户管理
 * @author:jemmyee@gmail.com
 * @date:2010-10-28
 * @version:v1.0
 */
@Controller
@RequestMapping(Constants.MV_URI_APP_CORE+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+Constants.ENTITY_ADMIN_USER)
public class AdminUserController extends BaseFrameworkController {

	private static Log log = LogFactory.getLog(AdminUserController.class);
	/* 分页查出保存的条件 */
	private static final String ENTITY_SESSION_BEAN = "admin_session_bean_"
			+ MathUtils.getRandomDouble();

	private static final String ROOT_VIEW =Constants.MV_URI_MAIN_ADMIN+Constants.MV_URI_APP_CORE+Constants.SYM_PATH_SEPARATOR+Constants.ENTITY_ADMIN_USER;
	private static final String ROOT_URI=Constants.MV_URI_APP_CORE+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+Constants.ENTITY_ADMIN_USER;

	@RequestMapping(Constants.MV_URI_COMM_TOADD)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView();
		initToAdd(mv,ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_TOEDIT + "/{id}")
	public ModelAndView toEdit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		AdminUser item = adminUserService.findById(id);
		initToEdit(mv, item,ROOT_URI, ROOT_VIEW);
		return mv;
	}

	/**
	 * @Description:个人信息修改
	 * @author:jemmyee@gmail.com
	 * @date:2011-7-24
	 */
	@RequestMapping("/toPersonal")
	public ModelAndView toPersonal(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		AdminUser item = getOnlineUser(request);
		mv.addObject(Constants.MV_OBJECT_ITEM, item);
		mv.addObject(Constants.MV_OBJECT_ROOT_URI,ROOT_URI);
		mv.setViewName(ROOT_VIEW + "/personal");
		return mv;
	}

	@RequestMapping("/personal")
	@ResponseBody
	public Map<String, Object> personal(@RequestBody AdminUser bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			AdminUser temp = adminUserService.findById(bean.getId());
			temp.setSex(bean.getSex());
			temp.setRealName(bean.getRealName());
			if (bean.getPassword() != null && !"".equals(bean.getPassword())) {
				temp.setPassword(EncryptUtils.getMD5String(bean.getPassword()
						.trim()));
			}
			adminUserService.update(temp);
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}

	@RequestMapping(Constants.MV_URI_COMM_ADD)
	@ResponseBody
	public Map<String, Object> add(@RequestBody AdminUser bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		AdminUser temp = null;
		try {
			if (adminUserService.findByProperty("name", bean.getName()) != null
					&& adminUserService.findByProperty("name", bean.getName())
							.size() > 0) {
				temp = adminUserService.findByProperty("name", bean.getName()).get(
						0);
			}
			if (temp != null && temp.getName().equals(bean.getName().trim())) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_RENAME);
			} else {
				bean.setName(bean.getName().trim());
				bean.setPassword(EncryptUtils.getMD5String(bean.getPassword()
						.trim()));
				bean.setAddTime(new Date());
				bean.setIsDelete((short) 0);
				bean
						.setDeptId(getSessionValue(Constants.ONLINE_DEPT_ID,
								request) == null ? 1 : Integer
								.parseInt(getSessionValue(Constants.ONLINE_DEPT_ID,
										request).toString()));
				adminUserService.save(bean);
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
	public Map<String, Object> edit(@RequestBody AdminUser bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			AdminUser temp = adminUserService.findById(bean.getId());
			List<AdminUser> temps = adminUserService.findByProperty("name",bean.getName()!=null?bean
					.getName().trim():"unknow");
			if (temps.size() > 0
					&& !temps.get(0).getName().equals(temp.getName().trim())) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_RENAME);
			} else {
				if(bean.getName()!=null&&!bean.getName().trim().equals(""))
				{
					temp.setName(bean.getName().trim());
				}
				temp.setSex(bean.getSex());
				temp.setRealName(bean.getRealName());
				if (bean.getPassword() != null && !"".equals(bean.getPassword())) {
					temp.setPassword(EncryptUtils.getMD5String(bean.getPassword()
							.trim()));
				}
				adminUserService.update(temp);
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
			}
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}

	@RequestMapping(Constants.MV_URI_COMM_MODIFY + "/{id}/{attr}/{val}")
	@ResponseBody
	public Map<String, Object> modify(@PathVariable Integer id,@PathVariable String attr,@PathVariable Integer val,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		String sql="update AdminUser set "+attr+"="+val+" where id="+id;
		try {
			adminUserService.excuteSql(sql);
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
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
		try {
			adminUserService.deleteById(id);
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
		List<Serializable> temps = new ArrayList<Serializable>();
		try {
			for (String id : ids.split("_")) {
				if (id != null && !id.equals("")) {
					temps.add(Integer.parseInt(id));
				}
			}
			adminUserService.deleteBatch(temps);
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}

	@RequestMapping(value = Constants.MV_URI_COMM_LIST
			+ "/{parentId}/{from}/{pageSize}/{startIndex}")
	public ModelAndView list( @PathVariable Integer parentId,@PathVariable String from,
			@PathVariable Integer pageSize,
			@PathVariable Integer startIndex, @ModelAttribute AdminUser bean,
			HttpServletRequest request) {
		DetachedCriteria dc = DetachedCriteria.forClass(AdminUser.class);
		AdminUser temp = new AdminUser();
		if (from.equals(Constants.PAGE_ENTRY_TYPE_FORM)) {
			temp = bean;
			request.getSession().setAttribute(ENTITY_SESSION_BEAN, bean);
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_PAGE)) {
			if (request.getSession().getAttribute(ENTITY_SESSION_BEAN) != null) {
				temp = (AdminUser) request.getSession().getAttribute(
						ENTITY_SESSION_BEAN);
			}
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_MENU)) {

		}

		if (bean.getDeptId() != null && bean.getDeptId() != 0) {
			dc.add(Restrictions.eq("deptId", bean.getDeptId()));
		}
		if (bean.getName() != null && !bean.getName().equals("")) {
			dc.add(Restrictions.like("name", "%" + bean.getName() + "%"));
		}
		if (bean.getRealName() != null && !bean.getRealName().equals("")) {
			dc.add(Restrictions
					.like("realName", "%" + bean.getRealName() + "%"));
		}
		// 时间
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
		if (parentId != 0) {
			dc.add(Restrictions.eq("deptId", parentId));
		}
		ModelAndView mv = new ModelAndView();
		if (pageSize == 0) {
			pageSize = Constants.DEFAULT_PAGESIZE;
		}
		Page page = adminUserService.findPageByCriteria(dc, pageSize,
				startIndex);
		if (pageSize != 0) {
			page.setPageSize(pageSize);
		}
		mv.addObject("depts", adminDeptService.findAll());
		request.getSession().setAttribute(Constants.ONLINE_DEPT_ID,
				parentId == 0 ? 0 : parentId);
		  String pageURI=request.getSession().getServletContext().getAttribute(Constants.APPLICATION_APPROOT)+ROOT_URI+Constants.MV_URI_COMM_LIST+Constants.SYM_PATH_SEPARATOR+parentId;
		   initList(mv,page,Constants.MV_VIEW_LIST,pageURI,ROOT_URI,temp,ROOT_VIEW,-1,pageSize,startIndex);
		return mv;
	}
}
