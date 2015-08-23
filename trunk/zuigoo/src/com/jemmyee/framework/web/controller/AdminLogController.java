package com.jemmyee.framework.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jemmyee.framework.dao.Page;
import com.jemmyee.framework.entity.AdminLog;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.controller.kernel.BaseFrameworkController;
import com.jemmyee.framework.web.utils.MathUtils;


@Controller
@RequestMapping(Constants.MV_URI_APP_CORE+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+Constants.ENTITY_ADMIN_LOG)
public class AdminLogController extends BaseFrameworkController{
	
	private static Log log= LogFactory.getLog(AdminLogController.class);
	/*分页查出保存的条件*/
	private static final String ENTITY_SESSION_BEAN="admin_session_bean_"+MathUtils.getRandomDouble();
	
	private static final String ROOT_VIEW=Constants.MV_URI_MAIN_ADMIN+Constants.MV_URI_APP_CORE+Constants.SYM_PATH_SEPARATOR+Constants.ENTITY_ADMIN_LOG;
	private static final String ROOT_URI=Constants.MV_URI_APP_CORE+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+Constants.ENTITY_ADMIN_LOG;
	
	
	@RequestMapping(Constants.MV_URI_COMM_DELETE + "/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable String id,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
		try {
			adminLogService.deleteById(id);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.equals(e);
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

			adminLogService.deleteBatch(temps);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}

	@RequestMapping(value=Constants.MV_URI_COMM_LIST+"/{type}/{from}/{pageSize}/{startIndex}")
	public ModelAndView list(@PathVariable Integer type,@PathVariable String from,@PathVariable Integer pageSize,@PathVariable Integer startIndex,@ModelAttribute AdminLog bean,HttpServletRequest request){
		  DetachedCriteria dc= DetachedCriteria.forClass(AdminLog.class);
		  AdminLog temp=new AdminLog();
		  if(from.equals(Constants.PAGE_ENTRY_TYPE_FORM))
		   {
			   temp=bean;
			   request.getSession().setAttribute(ENTITY_SESSION_BEAN, bean);
		   }
		   if(from.equals(Constants.PAGE_ENTRY_TYPE_PAGE))
		   {
			   if(request.getSession().getAttribute(ENTITY_SESSION_BEAN)!=null)
			   {
				   temp=(AdminLog)request.getSession().getAttribute(ENTITY_SESSION_BEAN);
			   }
		   }
		   if(from.equals(Constants.PAGE_ENTRY_TYPE_MENU))
		   {
			   
		   }
		   dc.addOrder(Order.desc("addTime"));
		   ModelAndView mv=new ModelAndView();
		   if(pageSize==0)
		   {
			   pageSize=Constants.DEFAULT_PAGESIZE;
		   }
		   if (bean.getBkw()!= null && !bean.getBkw().equals("")) {
				dc.add(Restrictions.like("content","%"+bean.getBkw()+"%"));
			}
			if (bean.getStartTime()!=null&&bean.getEndTime()!= null) {
				dc.add(Restrictions.ge("addTime",bean.getStartTime()));
				dc.add(Restrictions.le("addTime",bean.getEndTime()));
			}
			if (bean.getStartTime()!=null&&bean.getEndTime()== null) {
				dc.add(Restrictions.ge("addTime",bean.getStartTime()));
			}
			if (bean.getStartTime()==null&&bean.getEndTime()!= null) {
				dc.add(Restrictions.le("addTime",bean.getEndTime()));
			}   
		   dc.add(Restrictions.eq("type",type.shortValue()));
		   Page page=adminLogService.findPageByCriteria(dc,pageSize, startIndex);
		   if(pageSize!=0)
		   {
			   page.setPageSize(pageSize);
		   }
		   temp.setHightLight("<font color='red'>"+bean.getBkw()+"</font>");
		   String pageURI=request.getSession().getServletContext().getAttribute(Constants.APPLICATION_APPROOT)+ROOT_URI+Constants.MV_URI_COMM_LIST+Constants.SYM_PATH_SEPARATOR+type;
		   initList(mv,page,Constants.MV_VIEW_LIST,pageURI,ROOT_URI,temp,ROOT_VIEW,-1,pageSize,startIndex);
		   return mv;
	}
}
