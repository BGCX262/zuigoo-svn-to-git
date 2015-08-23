package com.jemmyee.taoke.controller.admin;

import java.io.Serializable;
import java.text.ParseException;
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

import com.jemmyee.framework.app.open.taobao.manager.CateManager;
import com.jemmyee.framework.app.open.taobao.manager.TaokeManager;
import com.jemmyee.framework.dao.Page;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.utils.MathUtils;
import com.jemmyee.taoke.TaokeConstants;
import com.jemmyee.taoke.controller.kernel.BaseTaokeFrontController;
import com.jemmyee.taoke.entity.TaokeCategory;
import com.jemmyee.taoke.entity.TaokeItem;
import com.taobao.api.ApiException;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.request.TaobaokeItemsGetRequest;

@Controller
@RequestMapping(Constants.MV_URI_APP_TAOKE+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+TaokeConstants.ENTITY_TAOKE_ITEM)
public class TaokeItemController extends BaseTaokeFrontController {

	private static Log log = LogFactory.getLog(TaokeItemController.class);
	/* 分页查出保存的条件 */
	private static final String ENTITY_SESSION_BEAN = "admin_session_bean_"
			+ MathUtils.getRandomDouble();
	
	private static final String ROOT_VIEW =Constants.MV_URI_MAIN_ADMIN+Constants.MV_URI_APP_TAOKE+Constants.SYM_PATH_SEPARATOR+TaokeConstants.ENTITY_TAOKE_ITEM;
	private static final String ROOT_URI=Constants.MV_URI_APP_TAOKE+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+TaokeConstants.ENTITY_TAOKE_ITEM;

	@RequestMapping(value = "/query/{from}/{pageSize}/{startIndex}")
	public ModelAndView query(@PathVariable String from,
			@PathVariable Integer pageSize, @PathVariable Integer startIndex,
			@ModelAttribute TaobaokeItemsGetRequest bean,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		TaobaokeItemsGetRequest temp = new TaobaokeItemsGetRequest();
		if (from.equals(Constants.PAGE_ENTRY_TYPE_FORM)) {
			temp = bean;
			request.getSession().setAttribute(ENTITY_SESSION_BEAN, bean);
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_PAGE)) {
			if (request.getSession().getAttribute(ENTITY_SESSION_BEAN) != null) {
				temp = (TaobaokeItemsGetRequest) request.getSession()
						.getAttribute(ENTITY_SESSION_BEAN);
			}
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_MENU)) {

		}

		if (pageSize == 0) {
			pageSize = Constants.DEFAULT_PAGESIZE;
		}
		// if(temp.getKeyword()!=null)
		// {
		// temp.setKeyword(Constants.HIGHTLIGHT_PREFIX+bean.getKeyword()+Constants.HIGHTLIGHT_SUFFIX);
		// }
		mv.addObject("pageSize", pageSize);
		mv.addObject("startIndex", startIndex);
		mv.addObject(Constants.MV_OBJECT_ENTITY,TaokeConstants.ENTITY_TAOKE_ITEM);
		mv.addObject(Constants.MV_OBJECT_BEAN, temp);
		try {
			mv.addObject("cates", CateManager.getItemCats());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (ApiException e) {
			e.printStackTrace();
		}
		try {
			mv.addObject("items", TaokeManager.getTaobaokeItems(bean));
		} catch (ApiException e) {
			e.printStackTrace();
		}
		mv.setViewName(ROOT_VIEW + "/query");
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_TOADD)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		initToAdd(mv,ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_TOEDIT + "/{id}")
	public ModelAndView toEdit(@PathVariable String id) {
		ModelAndView mv = new ModelAndView();
		TaokeItem item = taokeItemService.findById(id);
		initToEdit(mv, item, ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_ADD + "/{numiids}/{blank}")
	@ResponseBody
	public Map<String, Object> add(@PathVariable String numiids,
			@PathVariable String blank, HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
		try {
			List<TaobaokeItem> items = TaokeManager.getTaobaokeItems(numiids);
			for (TaobaokeItem item : items) {
				TaokeItem taoke = new TaokeItem();
				Integer parentId = 1;
				if (request.getSession().getAttribute(
						TaokeConstants.ONLINE_TAOKE_CATEGORY_ID) != null) {
					parentId = Integer
							.parseInt(request.getSession().getAttribute(
									TaokeConstants.ONLINE_TAOKE_CATEGORY_ID)
									.toString());
				}
				taoke.setAddTime(new Date());
				taoke.setCategoryId(parentId);
				taoke.setClickUrl(item.getClickUrl());
				taoke.setCommission(item.getCommission());
				taoke.setCommissionNum(item.getCommissionNum());
				taoke.setCommissionVolume(item.getCommissionVolume());
				taoke.setItemLocation(item.getItemLocation());
				taoke.setVolume(item.getVolume());
				taoke.setTitle(item.getTitle());
				taoke.setTaobaokeCatClickUrl(item.getTaobaokeCatClickUrl());
				taoke.setShopClickUrl(item.getShopClickUrl());
				taoke.setSellerCreditScore(item.getSellerCreditScore());
				taoke.setPrice(item.getPrice());
				taoke.setPicUrl(item.getPicUrl());
				taoke.setNumIid(item.getNumIid());
				taoke.setKeywordClickUrl(item.getKeywordClickUrl());
				taoke.setStatus(1);
				taoke.setIsTop(0);
				taoke.setIsVip(1);
				taoke.setNick(item.getNick());
				taokeItemService.save(taoke);
			}
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}

	@RequestMapping(Constants.MV_URI_COMM_EDIT)
	@ResponseBody
	public Map<String, Object> edit(@RequestBody TaokeItem bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			TaokeItem temp = taokeItemService.findById(bean.getId());
			List<TaokeItem> temps = taokeItemService.findByProperty("title",
					bean.getTitle().trim());
			if (temps.size() > 0
					&& !temps.get(0).getTitle().equals(temp.getTitle().trim())) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_RENAME);
			} else {
				temp.setTitle(bean.getTitle().trim());
				// bean.setIsDelete(temp.getIsDelete());
				taokeItemService.update(temp);
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
			taokeItemService.deleteById(id);
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
			taokeItemService.deleteBatch(temps);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}

	@RequestMapping(Constants.MV_URI_COMM_DETAIL + "/{id}")
	public ModelAndView detail(@PathVariable String id) {
		ModelAndView mv = new ModelAndView();
		TaokeItem item = taokeItemService.findById(id);
		initDetail(mv, item,ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(value = Constants.MV_URI_COMM_LIST
			+ "/{parentId}/{from}/{pageSize}/{startIndex}")
	public ModelAndView list(@PathVariable Integer parentId,@PathVariable String from,
			 @PathVariable Integer pageSize,
			@PathVariable Integer startIndex, @ModelAttribute TaokeItem bean,
			HttpServletRequest request) {
		DetachedCriteria dc = DetachedCriteria.forClass(TaokeItem.class);
		ModelAndView mv = new ModelAndView();
		TaokeItem temp = new TaokeItem();
		if (from.equals(Constants.PAGE_ENTRY_TYPE_FORM)) {
			temp = bean;
			request.getSession().setAttribute(ENTITY_SESSION_BEAN, bean);
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_PAGE)) {
			if (request.getSession().getAttribute(ENTITY_SESSION_BEAN) != null) {
				temp = (TaokeItem) request.getSession().getAttribute(
						ENTITY_SESSION_BEAN);
			}
		}
		if (from.equals(Constants.PAGE_ENTRY_TYPE_MENU)) {

		}
		if (parentId != 0) {
			dc.add(Restrictions.eq("categoryId", parentId));
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
		Page page = taokeItemService.findPageByCriteria(dc, pageSize,
				startIndex);
		if (pageSize != 0) {
			page.setPageSize(pageSize);
		}
		int isLast=0;
		List<TaokeCategory> cate=taokeCategoryService.findByProperty("id",parentId);
		if(cate!=null&&cate.size()>0){
			isLast=cate.get(0).getIsLast();
		}
		mv.addObject("isLast",isLast);
		request.getSession().setAttribute(
				TaokeConstants.ONLINE_TAOKE_CATEGORY_ID,
				parentId == 0 ? 0 : parentId);
		temp.setHightLight(Constants.HIGHTLIGHT_PREFIX + bean.getBkw()
				+ Constants.HIGHTLIGHT_SUFFIX);
		String pageURI=request.getContextPath()+Constants.SYM_PATH_SEPARATOR+ROOT_URI+Constants.MV_URI_COMM_LIST+Constants.SYM_PATH_SEPARATOR+parentId;
		initList(mv,page,Constants.MV_VIEW_LIST,pageURI,ROOT_URI,temp,ROOT_VIEW,-1,pageSize,startIndex);
		return mv;
	}

	@RequestMapping("/getItem/{numiid}")
	@ResponseBody
	public Map<String, Object> getItem(@PathVariable String numiid,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
		try {
			List<TaobaokeItem> items = TaokeManager.getTaobaokeItems(numiid);
			for (TaobaokeItem item : items) {
				TaokeItem taoke = new TaokeItem();
				Integer parentId = 1;
				if (request.getSession().getAttribute(
						TaokeConstants.ONLINE_TAOKE_CATEGORY_ID) != null) {
					parentId = Integer
							.parseInt(request.getSession().getAttribute(
									TaokeConstants.ONLINE_TAOKE_CATEGORY_ID)
									.toString());
				}
				taoke.setAddTime(new Date());
				taoke.setCategoryId(parentId);
				taoke.setClickUrl(item.getClickUrl());
				taoke.setCommission(item.getCommission());
				taoke.setCommissionNum(item.getCommissionNum());
				taoke.setCommissionVolume(item.getCommissionVolume());
				taoke.setItemLocation(item.getItemLocation());
				taoke.setVolume(item.getVolume());
				taoke.setTitle(item.getTitle());
				taoke.setTaobaokeCatClickUrl(item.getTaobaokeCatClickUrl());
				taoke.setShopClickUrl(item.getShopClickUrl());
				taoke.setSellerCreditScore(item.getSellerCreditScore());
				taoke.setPrice(item.getPrice());
				taoke.setPicUrl(item.getPicUrl());
				taoke.setNumIid(item.getNumIid());
				taoke.setKeywordClickUrl(item.getKeywordClickUrl());
				taoke.setStatus(1);
				taoke.setIsTop(0);
				taoke.setIsVip(1);
				taoke.setNick(item.getNick());
				taokeItemService.save(taoke);
			}
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
		}
		return model;
	}

	@RequestMapping("/getItemBatch/{ids}")
	@ResponseBody
	public Map<String, Object> getItemBatch(@PathVariable String ids,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
		try {
			List<TaobaokeItem> items = TaokeManager.getTaobaokeItems(ids
					.replace("_", ","));
			for (TaobaokeItem item : items) {
				TaokeItem taoke = new TaokeItem();
				Integer parentId = 1;
				if (request.getSession().getAttribute(
						TaokeConstants.ONLINE_TAOKE_CATEGORY_ID) != null) {
					parentId = Integer
							.parseInt(request.getSession().getAttribute(
									TaokeConstants.ONLINE_TAOKE_CATEGORY_ID)
									.toString());
				}
				taoke.setAddTime(new Date());
				taoke.setCategoryId(parentId);
				taoke.setClickUrl(item.getClickUrl());
				taoke.setCommission(item.getCommission());
				taoke.setCommissionNum(item.getCommissionNum());
				taoke.setCommissionVolume(item.getCommissionVolume());
				taoke.setItemLocation(item.getItemLocation());
				taoke.setVolume(item.getVolume());
				taoke.setTitle(item.getTitle());
				taoke.setTaobaokeCatClickUrl(item.getTaobaokeCatClickUrl());
				taoke.setShopClickUrl(item.getShopClickUrl());
				taoke.setSellerCreditScore(item.getSellerCreditScore());
				taoke.setPrice(item.getPrice());
				taoke.setPicUrl(item.getPicUrl());
				taoke.setNumIid(item.getNumIid());
				taoke.setKeywordClickUrl(item.getKeywordClickUrl());
				taoke.setStatus(1);
				taoke.setIsTop(0);
				taoke.setIsVip(1);
				taoke.setNick(item.getNick());
				taokeItemService.save(taoke);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
		}
		return model;
	}
	
	/**
	* @Description:更新单个值
	* @author:jemmyee@gmail.com
	* @param attr 属性名称
	* @param nval 新值
	* @param id ID
	* @date:2011-5-5
	*/
	@RequestMapping(Constants.MV_URI_COMM_MODIFY+"/{id}/{attr}/{nval}")
	@ResponseBody
	public Map<String, Object> modify(@PathVariable Integer id,@PathVariable String attr,@PathVariable Integer nval, HttpServletRequest request)
	{   
		Map<String, Object> model = new HashMap<String, Object>();
		String sql="update "+TaokeItem.class.getSimpleName()+" set "+attr+" ="+nval+" where id='"+id+"'";
//		System.out.println("sql:"+sql);
		try {
			taokeItemService.excuteSql(sql);
			model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_SUCC);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		
		return model;
	}

}
