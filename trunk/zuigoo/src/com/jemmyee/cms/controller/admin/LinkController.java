package com.jemmyee.cms.controller.admin;

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

import com.jemmyee.cms.controller.kernel.BaseCmsController;
import com.jemmyee.cms.entity.Link;
import com.jemmyee.framework.dao.Page;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.utils.MathUtils;

@Controller
@RequestMapping(Constants.MV_URI_APP_CMS+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+Constants.ENTITY_LINK)
public class LinkController extends BaseCmsController {

	private static Log log = LogFactory.getLog(LinkController.class);
	/* 分页查出保存的条件 */
	private static final String ENTITY_SESSION_BEAN = "admin_session_bean_"
			+ MathUtils.getRandomDouble();

	private static final String ROOT_VIEW =Constants.MV_URI_MAIN_ADMIN+Constants.MV_URI_APP_CMS+Constants.SYM_PATH_SEPARATOR+Constants.ENTITY_LINK;
	private static final String ROOT_URI=Constants.MV_URI_APP_CMS+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+Constants.ENTITY_LINK;

	@RequestMapping(Constants.MV_URI_COMM_TOADD)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		initToAdd(mv, ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_TOEDIT + "/{id}")
	public ModelAndView toEdit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		Link item = linkService.findById(id);
		initToEdit(mv, item,ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_ADD)
	@ResponseBody
	public Map<String, Object> add(@RequestBody Link bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		Link temp = null;
		try {
			if (linkService.findByProperty("name", bean.getName()) != null
					&& linkService.findByProperty("name", bean.getName())
							.size() > 0) {
				temp = linkService.findByProperty("name", bean.getName())
						.get(0);
			}
			if (temp != null && temp.getName().equals(bean.getName().trim())) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_RENAME);
			} else {
				bean.setName(bean.getName().trim());
				bean.setAddTime(new Date());
				bean.setIsDelete((short) 0);
				bean.setShowOrder(500);
				linkService.save(bean);
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
	public Map<String, Object> edit(@RequestBody Link bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			Link temp = linkService.findById(bean.getId());
			List<Link> temps = linkService.findByProperty("name", bean
					.getName().trim());
			if (temps.size() > 0
					&& !temps.get(0).getName().equals(temp.getName().trim())) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_RENAME);
			} else {
				temp.setName(bean.getName().trim());
				temp.setIsVip(bean.getIsVip());
				temp.setContact(bean.getContact());
				temp.setType(bean.getType());
				temp.setUrl(bean.getUrl());
				temp.setLogo(bean.getLogo());
				linkService.update(temp);
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
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
		try {
			linkService.deleteById(id);
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
					temps.add(Integer.parseInt(id));
				}
			}
			linkService.deleteBatch(temps);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}

		return model;
	}

	@RequestMapping(Constants.MV_URI_COMM_DETAIL + "/{id}")
	public ModelAndView detail(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		Link item = linkService.findById(id);
		initDetail(mv, item,ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(value = Constants.MV_URI_COMM_LIST
			+ "/{from}/{pageSize}/{startIndex}")
	public ModelAndView list(@PathVariable String from,@PathVariable Integer pageSize,
			@PathVariable Integer startIndex, @ModelAttribute Link bean,
			HttpServletRequest request) {
		DetachedCriteria dc = DetachedCriteria.forClass(Link.class);
		ModelAndView mv = new ModelAndView();
		Link temp = new Link();
		if (from.equals(Constants.PAGE_ENTRY_TYPE_FORM)) {
			temp = bean;
			request.getSession().setAttribute(ENTITY_SESSION_BEAN, bean);
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_PAGE)) {
			if (request.getSession().getAttribute(ENTITY_SESSION_BEAN) != null) {
				temp = (Link) request.getSession().getAttribute(
						ENTITY_SESSION_BEAN);
			}
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_MENU)) {

		}

		if (pageSize == 0) {
			pageSize = Constants.DEFAULT_PAGESIZE;
		}
		if (bean.getBkw() != null && !bean.getBkw().equals("")) {
			dc.add(Restrictions.like("name", "%" + bean.getBkw() + "%"));
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

		Page page = linkService.findPageByCriteria(dc, pageSize, startIndex);
		if (pageSize != 0) {
			page.setPageSize(pageSize);
		}
		temp.setHightLight(Constants.HIGHTLIGHT_PREFIX + bean.getBkw()
				+ Constants.HIGHTLIGHT_SUFFIX);
		  String pageURI=request.getSession().getServletContext().getAttribute(Constants.APPLICATION_APPROOT)+ROOT_URI+Constants.MV_URI_COMM_LIST;
		   initList(mv,page,Constants.MV_VIEW_LIST,pageURI,ROOT_URI,temp,ROOT_VIEW,-1,pageSize,startIndex);
		return mv;
	}

}
