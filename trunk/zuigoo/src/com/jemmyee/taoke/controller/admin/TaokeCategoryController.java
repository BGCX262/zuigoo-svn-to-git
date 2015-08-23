package com.jemmyee.taoke.controller.admin;

import java.io.IOException;
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

import com.jemmyee.cms.entity.Article;
import com.jemmyee.framework.dao.Page;
import com.jemmyee.framework.utils.FileUtils;
import com.jemmyee.framework.utils.HTMLGeneratorUtils;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.utils.MathUtils;
import com.jemmyee.taoke.TaokeConstants;
import com.jemmyee.taoke.controller.kernel.BaseTaokeFrontController;
import com.jemmyee.taoke.entity.TaokeCategory;


@Controller("taokeCategoryController")
@RequestMapping(Constants.MV_URI_APP_TAOKE+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+TaokeConstants.ENTITY_TAOKE_CATEGORY)
public class TaokeCategoryController extends BaseTaokeFrontController{
	
	private static Log log= LogFactory.getLog(TaokeCategoryController.class);
	/*HQL 查询中的实体名*/
	private static final String ENTITY_HQL_NAME=TaokeCategory.class.getSimpleName();
	/*分页查出保存的条件*/
	private static final String ENTITY_SESSION_BEAN="admin_session_bean_"+MathUtils.getRandomDouble();
	
	private static final String ROOT_VIEW =Constants.MV_URI_MAIN_ADMIN+Constants.MV_URI_APP_TAOKE+Constants.SYM_PATH_SEPARATOR+TaokeConstants.ENTITY_TAOKE_CATEGORY;
	private static final String ROOT_URI=Constants.MV_URI_APP_TAOKE+Constants.MV_URI_MAIN_ADMIN+Constants.SYM_PATH_SEPARATOR+TaokeConstants.ENTITY_TAOKE_CATEGORY;

	@RequestMapping(Constants.MV_URI_COMM_TOADD)
	public ModelAndView toAdd(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
        initToAdd(mv,ROOT_URI,ROOT_VIEW);
		return mv;
	}
	@RequestMapping(Constants.MV_URI_COMM_TOEDIT+"/{id}")
	public ModelAndView toEdit(@PathVariable Integer id){
		   ModelAndView mv=new ModelAndView();
		   TaokeCategory item=taokeCategoryService.findById(id);
           initToEdit(mv, item,ROOT_URI,ROOT_VIEW);
		   return mv;
	}
	@RequestMapping(Constants.MV_URI_COMM_ADD)
	@ResponseBody
	public Map<String, Object> add(@RequestBody TaokeCategory bean,HttpServletRequest request)
	{   
		Integer parentId=0;
		if(request.getSession().getAttribute(TaokeConstants.ONLINE_TAOKE_CATEGORY_ID)!=null)
		{
			parentId=Integer.parseInt(request.getSession().getAttribute(TaokeConstants.ONLINE_TAOKE_CATEGORY_ID).toString());
		}
		Map<String, Object> model = new HashMap<String, Object>();
		TaokeCategory temp=null;
		try {
			 DetachedCriteria dc= DetachedCriteria.forClass(TaokeCategory.class);
			 dc.add(Restrictions.eq("name",bean.getName().trim()));
			 dc.add(Restrictions.eq("parentId",parentId));
			 List<TaokeCategory> cates=taokeCategoryService.findByCriteria(dc);
			if(cates!=null&&cates.size()>0){
				temp=cates.get(0);
			}
			if(temp!=null&&temp.getName().equals(bean.getName().trim()))//当前同一目录下不能重名
			{
				model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_RENAME);
			}else
			{   
				bean.setName(bean.getName().trim());
				bean.setAddTime(new Date());
				bean.setIsDelete((short)0);
				bean.setParentId(parentId);
				bean.setIsRecommend((short)0);
				taokeCategoryService.save(bean);
				//更新html文件
//				createCategoryTreeHtml(request);//太慢了
				model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_SUCC);
			}

		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}
	
	@RequestMapping(Constants.MV_URI_COMM_EDIT)
	@ResponseBody
	public Map<String, Object> edit(@RequestBody TaokeCategory bean,HttpServletRequest request)
	{   
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			Integer parentId=0;
			if(request.getSession().getAttribute(TaokeConstants.ONLINE_TAOKE_CATEGORY_ID)!=null)
			{
				parentId=Integer.parseInt(request.getSession().getAttribute(TaokeConstants.ONLINE_TAOKE_CATEGORY_ID).toString());
			}
			TaokeCategory temp=taokeCategoryService.findById(bean.getId());
			 DetachedCriteria dc= DetachedCriteria.forClass(TaokeCategory.class);
			 dc.add(Restrictions.eq("name",bean.getName().trim()));
			 dc.add(Restrictions.eq("parentId",parentId));
			 List<TaokeCategory> cates=taokeCategoryService.findByCriteria(dc);
			if(cates.size()>0&&!cates.get(0).getName().equals(temp.getName().trim()))
			{
				model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_RENAME);//当前目录不重名
			}
			else
			{
			    temp.setName(bean.getName().trim());
			    temp.setType(bean.getType());
			    temp.setSeoTitle(bean.getSeoTitle());
			    temp.setSeoDesc(bean.getSeoDesc());
			    temp.setSeoKeyword(bean.getSeoKeyword());
			    temp.setCode(bean.getCode());
			    temp.setIsLast(bean.getIsLast());
//			    bean.setIsDelete(temp.getIsDelete());
			    taokeCategoryService.update(temp);
				//更新html文件
//				createCategoryTreeHtml(request);//太慢了
			    model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_SUCC);
			}
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}
	@RequestMapping(Constants.MV_URI_COMM_DELETE+"/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable String id,HttpServletRequest request)
	{   
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			taokeCategoryService.deleteById(id);
			model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_SUCC);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}
	
	@RequestMapping(Constants.MV_URI_COMM_DELETBATCH+"/{ids}")
	@ResponseBody
	public Map<String, Object> deleteBatch(@PathVariable String ids,HttpServletRequest request)
	{   
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_SUCC);
		List<Serializable> temps=new ArrayList<Serializable>();
		for(String id:ids.split("_")){
			if(id!=null&&!id.equals("")){
				temps.add(Integer.parseInt(id));
			}
		}
		try {
			taokeCategoryService.deleteBatch(temps);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		return model;
	}
	
	@RequestMapping(value=Constants.MV_URI_COMM_LIST+"/{parentId}/{from}/{pageSize}/{startIndex}")
	public ModelAndView list(@PathVariable Integer parentId,@PathVariable String from,@PathVariable Integer pageSize,@PathVariable Integer startIndex,@ModelAttribute TaokeCategory bean,HttpServletRequest request){
		  DetachedCriteria dc= DetachedCriteria.forClass(TaokeCategory.class);
		  TaokeCategory temp=new TaokeCategory();
		  if(from.equals(Constants.PAGE_ENTRY_TYPE_FORM))
		   {
			   temp=bean;
			   request.getSession().setAttribute(ENTITY_SESSION_BEAN, bean);
		   }
		   if(from.equals(Constants.PAGE_ENTRY_TYPE_PAGE))
		   {
			   if(request.getSession().getAttribute(ENTITY_SESSION_BEAN)!=null)
			   {
				   temp=(TaokeCategory)request.getSession().getAttribute(ENTITY_SESSION_BEAN);
			   }
		   }
		   if(from.equals(Constants.PAGE_ENTRY_TYPE_MENU))
		   {
			   
		   }
		   //0显示一级目录
//		   if(parentId!=0){
			   dc.add(Restrictions.eq("parentId",parentId));
//		   }
		   dc.addOrder(Order.desc("addTime"));
		   ModelAndView mv=new ModelAndView();
		   if(pageSize==0)
		   {
			   pageSize=Constants.DEFAULT_PAGESIZE;
		   }
		   Page page=taokeCategoryService.findPageByCriteria(dc,pageSize, startIndex);
		   if(pageSize!=0)
		   {
			   page.setPageSize(pageSize);
		   }
		   
		   request.getSession().setAttribute(TaokeConstants.ONLINE_TAOKE_CATEGORY_ID,parentId==0?0:parentId);
		   temp.setHightLight(Constants.HIGHTLIGHT_PREFIX+bean.getBkw()+Constants.HIGHTLIGHT_SUFFIX);
			String pageURI=request.getContextPath()+Constants.SYM_PATH_SEPARATOR+ROOT_URI+Constants.MV_URI_COMM_LIST+Constants.SYM_PATH_SEPARATOR+parentId;
			initList(mv,page,Constants.MV_VIEW_LIST,pageURI,ROOT_URI,temp,ROOT_VIEW,-1,pageSize,startIndex);
		   return mv;
	}
	
	/**
	 * 生成目录树
	 * @return
	 */
//	@RequestMapping("/treexml")
//	@ResponseBody
//	public String treeXml(HttpServletRequest request){
//		List<TaokeCategory> cates=taokeCategoryService.findAll();
//		String filePath=getAppRoot(request)+Constants.TREE_XML_FILE_PATH+File.separator+Constants.TREE_XML_FILE_NAME_DEPT;
//	    TreeXmlUtils.createArticleCategoryTreeXml(filePath, cates);
//		return "ok";
//	}
	
	@RequestMapping(Constants.MV_URI_COMM_FRAME+"/{from}")
	public ModelAndView frame(@PathVariable String from) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("from",from);
		mv.setViewName(ROOT_VIEW+Constants.SYM_PATH_SEPARATOR+Constants.MV_VIEW_FRAME);
		return mv;
	}
	@RequestMapping(Constants.MV_URI_COMM_TREE+"/{from}")
	public ModelAndView tree(@PathVariable String from,HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("from",from);
		//判断html树文件是否存在 不存在就生成
		String htmlFile=getAppRoot(request)+Constants.STATIC_PATH+TaokeConstants.TREE_HTML_TAOKE_CATEGORY;
		if(!FileUtils.isFileExist(htmlFile))
		{
			createCategoryTreeHtml(request);
		}
		mv.setViewName(ROOT_VIEW+Constants.SYM_PATH_SEPARATOR+Constants.MV_VIEW_TREE);
		return mv;
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
		String sql="update "+ENTITY_HQL_NAME+" set "+attr+" ="+nval+" where id="+id;
//		System.out.println("sql:"+sql);
		try {
			taokeCategoryService.excuteSql(sql);
			model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_SUCC);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_ERROR);
			log.error(e);
		}
		
		return model;
	}
	
	@RequestMapping("/parse_tree")
	@ResponseBody
	public Map<String, Object> parseTree(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<TaokeCategory> entitys = taokeCategoryService.findByProperty("parentId",0);
		String objName="所有分类";
		StringBuffer sb = new StringBuffer();
		sb.append("<li href='#' id='0'");
		sb.append("<a target='main'>"+objName+"</a><ul>");
		for (TaokeCategory entity:entitys) {
			List<TaokeCategory> entitysOne=taokeCategoryService.findByProperty("parentId",entity.getId());
			if(entitysOne.size()>0)
			{   
				sb.append("<li href='#' id='" + entity.getId()+"'><a target='main'>" + entity.getName() + "</a><ul>");
				for(TaokeCategory entityOne:entitysOne)
				{
//					sb.append("<li href='#' id='" + entityOne.getId()+"'><a target='main'>" + entityOne.getName() + "</a></li>");
					List<TaokeCategory> entitysTwo=taokeCategoryService.findByProperty("parentId",entityOne.getId());
					if(entitysTwo.size()>0)
					{   
						sb.append("<li href='#' id='" + entityOne.getId()+"'><a target='main'>" + entityOne.getName() + "</a><ul>");
						for(TaokeCategory entityTwo:entitysTwo)
						{
							List<TaokeCategory> entitysThree=taokeCategoryService.findByProperty("parentId",entityTwo.getId());
							if(entitysThree.size()>0)
							{
								sb.append("<li href='#' id='" + entityTwo.getId()+"'><a target='main'>" + entityTwo.getName() + "</a><ul>");
								for(TaokeCategory entityThree:entitysThree)
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
		model.put("tree", sb.toString());
		return model;
	}
	
	@RequestMapping("/toSet")
	public ModelAndView toSet() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName(ROOT_VIEW+"/set");
		return mv;
	}
	
	
	/**
	* @Description:静态页面生成
	* @author:jemmyee@gmail.com
	* @date:2011-8-15
	*/
	@RequestMapping("/createHtml/{type}")
	@ResponseBody
	public Map<String, Object> createHtml(@PathVariable String type,HttpServletRequest request)
	{   
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_SUCC);
		int count=1;//生成的文件个数
		if(type.equals("tree"))//目录树
		{
			createCategoryTreeHtml(request);
		}else if(type.equals("one")){//一级目录
			List<TaokeCategory> cates=taokeCategoryService.findByProperty("parentId","0");
			String ctxPath=getAppRoot(request);
			for(TaokeCategory cate:cates)
			{
				
				String path="";  
				if(cate.getId().intValue()==TaokeConstants.DEFAULT_INDEX_CATEGORY_ID.intValue())
				{
					 path="index.html"; 
				}else{
					 path="/html/index/"+cate.getId()+".html"; 
				}
				String url=getWebRoot(request)+"index/f/"+cate.getId()+"/0";
				try {
					HTMLGeneratorUtils.generateItemHtml(request, url, ctxPath, path,true, "UTF-8");
				} catch (IOException e) {
					model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_ERROR);
					e.printStackTrace();
					return model;
				}
				count=count+1;
			}
		}
        else if(type.equals("three")){//三级目录
        	List<TaokeCategory> cates=taokeCategoryService.findByProperty("isLast","1");
			String ctxPath=getAppRoot(request);
			for(TaokeCategory cate:cates)
			{
//				TaokeCategory rootCate=taokeCategoryService.findByProperty("parentId",taokeCategoryService.findById(cate.getParentId())).get(0);
				String path="/html/index/"+cate.getParentId()+"/"+cate.getId()+".html";  
				String url=getWebRoot(request)+"index/l/"+cate.getId()+"/0";
				try {
					HTMLGeneratorUtils.generateItemHtml(request, url, ctxPath, path,true,"UTF-8");
				} catch (IOException e) {
					model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_ERROR);
					e.printStackTrace();
					return model;
				}
				count=count+1;
			}
	    }
        else if(type.equals("four")){//单站一级主页
        	List<TaokeCategory> cates=taokeCategoryService.findByProperty("parentId","0");
        	String ctxPath=getAppRoot(request);
        	for(TaokeCategory cate:cates)
        	{
//				TaokeCategory rootCate=taokeCategoryService.findByProperty("parentId",taokeCategoryService.findById(cate.getParentId())).get(0);
        		String path="/html/single/"+cate.getId()+".html";  
        		String url=getWebRoot(request)+"single/index/f/"+cate.getId();
        		try {
        			HTMLGeneratorUtils.generateItemHtml(request, url, ctxPath, path,true,"UTF-8");
        		} catch (IOException e) {
        			model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_ERROR);
        			e.printStackTrace();
        			return model;
        		}
        		count=count+1;
        	}
        }
        else if(type.equals("two")){//单站三级主页
        	List<TaokeCategory> cates=taokeCategoryService.findByProperty("isLast","1");
        	String ctxPath=getAppRoot(request);
        	for(TaokeCategory cate:cates)
        	{
//				TaokeCategory rootCate=taokeCategoryService.findByProperty("parentId",taokeCategoryService.findById(cate.getParentId())).get(0);
        		String path="/html/single/"+cate.getParentId()+"/"+cate.getId()+".html";  
        		String url=getWebRoot(request)+"single/index/l/"+cate.getId();
        		try {
        			HTMLGeneratorUtils.generateItemHtml(request, url, ctxPath, path,true,"UTF-8");
        		} catch (IOException e) {
        			model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_ERROR);
        			e.printStackTrace();
        			return model;
        		}
        		count=count+1;
        	}
        }
        else if(type.equals("five")){//文章详情
    		List<Article> articles = articleService.findAll();
			for (Article article : articles) {
				String url = getWebRoot(request) + "article/detail/"
						+ article.getId();
//				String htmlPath = "/" + CmsConstants.HTML_ROOT_PATH + "/"
//						+ DateUtils.getNowYear() + "/"
//						+ DateUtils.getNowMonth() + "/" + DateUtils.getNowDay()
//						+ "/";
//				String fileName = article.getId() + CmsConstants.HTML_SUFFIX;
				String ctxPath = getAppRoot(request);
				try {
					HTMLGeneratorUtils.generateHtml(request, url, ctxPath,
							article.getFileUrl(), "UTF-8");
				} catch (IOException e) {
					model
							.put(Constants.AJAX_MSG_NAME,
									Constants.AJAX_MSG_ERROR);
					e.printStackTrace();
					return model;
				}
				count = count + 1;
			}
        }
		model.put("count",count);
		return model;
	}
	

	/**
	* @Description:单个栏目静态生成
	* @author:jemmyee@gmail.com
	* @date:2011-10-13
	*/
	@RequestMapping("/dostatic/{type}")
	@ResponseBody
	public Map<String, Object> doStatic(@PathVariable String type,HttpServletRequest request)
	{   
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_SUCC);
		Integer parentId=0;
		if(request.getSession().getAttribute(TaokeConstants.ONLINE_TAOKE_CATEGORY_ID)!=null)
		{
			parentId=Integer.parseInt(request.getSession().getAttribute(TaokeConstants.ONLINE_TAOKE_CATEGORY_ID).toString());
		}
		int count=0;//生成的文件个数
		if(type.equals("single")){//单站三级主页
			List<TaokeCategory> cates=taokeCategoryService.findByProperty("id",parentId);
			String ctxPath=getAppRoot(request);
			for(TaokeCategory cate:cates)
			{
//				TaokeCategory rootCate=taokeCategoryService.findByProperty("parentId",taokeCategoryService.findById(cate.getParentId())).get(0);
				String path="/html/single/"+cate.getParentId()+"/"+cate.getId()+".html";  
				String url=getWebRoot(request)+"single/index/l/"+cate.getId();
				try {
					HTMLGeneratorUtils.generateItemHtml(request, url, ctxPath, path,true,"UTF-8");
				} catch (IOException e) {
					model.put(Constants.AJAX_MSG_NAME,Constants.AJAX_MSG_ERROR);
					e.printStackTrace();
					return model;
				}
				count=count+1;
			}
		}
		else if(type.equals("detail")){//文章详情
			List<Article> articles = articleService.findByProperty("categoryId",parentId);
			for (Article article : articles) {
				String url = getWebRoot(request) + "article/detail/"
				+ article.getId();
//				String htmlPath = "/" + CmsConstants.HTML_ROOT_PATH + "/"
//						+ DateUtils.getNowYear() + "/"
//						+ DateUtils.getNowMonth() + "/" + DateUtils.getNowDay()
//						+ "/";
//				String fileName = article.getId() + CmsConstants.HTML_SUFFIX;
				String ctxPath = getAppRoot(request);
				try {
					HTMLGeneratorUtils.generateHtml(request, url, ctxPath,
							article.getFileUrl(), "UTF-8");
				} catch (IOException e) {
					model
					.put(Constants.AJAX_MSG_NAME,
							Constants.AJAX_MSG_ERROR);
					e.printStackTrace();
					return model;
				}
				count = count + 1;
			}
		}
		model.put("count",count);
		return model;
	}
	
	public void createCategoryTreeHtml(HttpServletRequest request){
		List<TaokeCategory> entitys = taokeCategoryService.findByProperty("parentId",0);
		String objName="所有分类";
		StringBuffer sb = new StringBuffer();
		sb.append("<li href='#' id='0'");
		sb.append("<a target='main'>"+objName+"</a><ul>");
		for (TaokeCategory entity:entitys) {
			List<TaokeCategory> entitysOne=taokeCategoryService.findByProperty("parentId",entity.getId());
			if(entitysOne.size()>0)
			{   
				sb.append("<li href='#' id='" + entity.getId()+"'><a target='main'>" + entity.getName() + "</a><ul>");
				for(TaokeCategory entityOne:entitysOne)
				{
//					sb.append("<li href='#' id='" + entityOne.getId()+"'><a target='main'>" + entityOne.getName() + "</a></li>");
					List<TaokeCategory> entitysTwo=taokeCategoryService.findByProperty("parentId",entityOne.getId());
					if(entitysTwo.size()>0)
					{   
						sb.append("<li href='#' id='" + entityOne.getId()+"'><a target='main'>" + entityOne.getName() + "</a><ul>");
						for(TaokeCategory entityTwo:entitysTwo)
						{
							List<TaokeCategory> entitysThree=taokeCategoryService.findByProperty("parentId",entityTwo.getId());
							if(entitysThree.size()>0)
							{
								sb.append("<li href='#' id='" + entityTwo.getId()+"'><a target='main'>" + entityTwo.getName() + "</a><ul>");
								for(TaokeCategory entityThree:entitysThree)
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
		String htmlFile=getAppRoot(request)+Constants.STATIC_PATH+TaokeConstants.TREE_HTML_TAOKE_CATEGORY;
		FileUtils.writeFile(htmlFile,sb.toString());
	}

}
