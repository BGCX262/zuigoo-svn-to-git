package com.jemmyee.cms.controller.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jemmyee.cms.CmsConstants;
import com.jemmyee.cms.controller.kernel.BaseCmsController;
import com.jemmyee.cms.entity.Article;
import com.jemmyee.framework.dao.Page;
import com.jemmyee.framework.utils.DateUtils;
import com.jemmyee.framework.utils.HTMLGeneratorUtils;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.utils.MathUtils;
import com.jemmyee.taoke.TaokeConstants;
import com.jemmyee.taoke.entity.TaokeCategory;

/**
 * @Description: 文章
 * @author:jemmyee@gmail.com
 * @date:2011-4-29
 * @version:v1.0
 */
@Controller("articleController")
@RequestMapping(Constants.MV_URI_APP_CMS + Constants.MV_URI_MAIN_ADMIN
		+ Constants.SYM_PATH_SEPARATOR + CmsConstants.ENTITY_ARTICLE)
public class ArticleController extends BaseCmsController {

	private static Log log = LogFactory.getLog(ArticleController.class);
	/* 分页查出保存的条件 */
	private static final String ENTITY_SESSION_BEAN = "admin_session_bean_"
			+ MathUtils.getRandomDouble();
	private static final String ROOT_VIEW = Constants.MV_URI_MAIN_ADMIN
			+ Constants.MV_URI_APP_CMS + Constants.SYM_PATH_SEPARATOR
			+ CmsConstants.ENTITY_ARTICLE;
	private static final String ROOT_URI = Constants.MV_URI_APP_CMS
			+ Constants.MV_URI_MAIN_ADMIN + Constants.SYM_PATH_SEPARATOR
			+ CmsConstants.ENTITY_ARTICLE;

	@RequestMapping(Constants.MV_URI_COMM_TOADD)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		initToAdd(mv, ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_TOEDIT + "/{id}")
	public ModelAndView toEdit(@PathVariable String id) {
		ModelAndView mv = new ModelAndView();
		Article item = articleService.findById(id);
		initToEdit(mv, item, ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(Constants.MV_URI_COMM_ADD)
	@ResponseBody
	public Map<String, Object> add(@RequestBody Article bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		Article temp = null;
		try {
			if (articleService.findByProperty("title", bean.getTitle()) != null
					&& articleService.findByProperty("title", bean.getTitle())
							.size() > 0) {
				temp = articleService.findByProperty("title", bean.getTitle())
						.get(0);
			}
			if (temp != null && temp.getTitle().equals(bean.getTitle().trim())) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_RENAME);
			} else {
				bean.setTitle(bean.getTitle().trim());
				bean.setContent(bean.getContent());
				bean.setAddTime(new Date());
				bean.setSortOrder(100);
				// bean.setIsDelete((short)0);
				String id = articleService.save(bean).toString();
				// 生成html文件
				String url=createSingleHtml(id,"NEW",request);
				Article art=articleService.findById(id);
				art.setFileUrl(url);
				articleService.update(art);
				
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
	public Map<String, Object> edit(@RequestBody Article bean,
			HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			Article temp = articleService.findById(bean.getId());
			List<Article> temps = articleService.findByProperty("title", bean
					.getTitle().trim());
			if (temps.size() > 0
					&& !temps.get(0).getTitle().equals(temp.getTitle().trim())) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_RENAME);
			} else {
				temp.setTitle(bean.getTitle().trim());
				temp.setContent(bean.getContent());
				temp.setBefrom(bean.getBefrom());
				temp.setKeywords(bean.getKeywords());
				temp.setAuthor(bean.getAuthor());
				if(bean.getBrief()!=null)
				{
					temp.setBrief(bean.getBrief());
				}
				// bean.setIsDelete(temp.getIsDelete());
				articleService.update(temp);
				createSingleHtml(bean.getId(),temp.getFileUrl(),request);
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
			articleService.deleteById(id);
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
			articleService.deleteBatch(temps);
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
		} catch (Exception e) {
			model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
			log.error(e);
		}

		return model;
	}

	@RequestMapping(Constants.MV_URI_COMM_DETAIL + "/{id}")
	public ModelAndView detail(@PathVariable String id) {
		ModelAndView mv = new ModelAndView();
		Article item = articleService.findById(id);
		initDetail(mv, item, ROOT_URI, ROOT_VIEW);
		return mv;
	}

	@RequestMapping(value = Constants.MV_URI_COMM_LIST
			+ "/{parentId}/{from}/{pageSize}/{startIndex}")
	public ModelAndView list(@PathVariable Integer parentId,
			@PathVariable String from, @PathVariable Integer pageSize,
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
		Page page = articleService.findPageByCriteria(dc, pageSize, startIndex);
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
				parentId == 0 ? 0 : parentId);// 默认发到通知公告
		temp.setHightLight(Constants.HIGHTLIGHT_PREFIX + bean.getBkw()
				+ Constants.HIGHTLIGHT_SUFFIX);
		//不同的部署环境，getContextPath()为空的情况 
		String pageURI =request.getSession().getServletContext().getAttribute(Constants.APPLICATION_APPROOT)+ ROOT_URI
				+ Constants.MV_URI_COMM_LIST + Constants.SYM_PATH_SEPARATOR
				+ parentId;
		initList(mv, page, Constants.MV_VIEW_LIST, pageURI, ROOT_URI, temp,
				ROOT_VIEW, -1, pageSize, startIndex);
		return mv;
	}

	/**
	 * @Description: 发布文章的图片上传 kindeditor专用
	 * @author:jemmyee@gmail.com
	 * @date:2011-7-29
	 */
	@RequestMapping("/upload")
	public void upload(@RequestParam MultipartFile imgFile,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		// 定义允许上传的文件扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
		// 最大文件大小
		long maxSize = 1000000;
		String savePath = Constants.UPLOAD_IMAGES_PATH
				+ DateUtils.getNowYear() + "/" + DateUtils.getNowMonth() + "/"
				+ DateUtils.getNowDay() + "/";// 文件目录
		String fileName = imgFile.getOriginalFilename();// 文件名
		String path = getAppRoot(request) + savePath + fileName;// 上传文件的绝对路径
		String url = getWebRoot(request) + savePath + fileName;// 前台浏览图片路径

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// 检查目录
		File dirFile = new File(getAppRoot(request)+savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		// 检查目录写权限
		if (!dirFile.canWrite()) {
			out.println(getUploadError("上传目录没有写权限。"));
			return;
		}
		// 检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
				.toLowerCase();
		if (!Arrays.<String> asList(fileTypes).contains(fileExt)) {
			out.println(getUploadError("该图片格式不支持上传!"));
			return;
		}
		if (imgFile.getSize() > maxSize) {
			out.println(getUploadError("图片大小超出限制!"));
			return;
		}
		FileOutputStream fos;
		try {
			byte[] bytes = imgFile.getBytes();
			fos = new FileOutputStream(path);
			fos.write(bytes);
		} catch (FileNotFoundException e) {
			out.println(getUploadError("上传文件失败"));
			e.printStackTrace();
		} catch (IOException e) {
			out.println(getUploadError("上传文件失败"));
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("error", 0);// 0表示上传成功
		obj.put("url", url);
		out.println(obj.toJSONString());
	}

	/**
	 * @Description:图片上传错误处理
	 * @author:jemmyee@gmail.com
	 * @date:2011-7-29
	 */
	private String getUploadError(String msg) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", msg);
		return obj.toJSONString();
	}

	/**
	 * @Description:single静态页面生成
	 * @author:jemmyee@gmail.com
	 * @date:2011-9-19
	 */
	public String createSingleHtml(String id,String path,HttpServletRequest request)
			throws Exception {
		Article article = articleService.findById(id);
		String url = getWebRoot(request) + "article/detail/" + id;
		String filePath;
		if(!path.equals("NEW"))
		{
			filePath=path;
		}else{
			String htmlPath = "/" + CmsConstants.HTML_ROOT_PATH + "/article/"
					+ DateUtils.getNowYear() + "/" + DateUtils.getNowMonth() + "/"
					+ DateUtils.getNowDay() + "/";
			String fileName = article.getId() + CmsConstants.HTML_SUFFIX;
			filePath=htmlPath+fileName;
		}
		String ctxPath = getAppRoot(request);
		HTMLGeneratorUtils.generateHtml(request, url, ctxPath,filePath,"UTF-8");
		return filePath;
	}

	/**
	 * @Description:静态页面生成
	 * @param:type all cate single
	 * @author:jemmyee@gmail.com
	 * @date:2011-9-19
	 */
	@RequestMapping("/createHtml/{type}/{param}")
	@ResponseBody
	public Map<String, Object> createHtml(@PathVariable String type,
			@PathVariable String param, HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_SUCC);
		int count = 1;// 生成的文件个数
		if (type.equals("single"))// 单个文章页面
		{
			Article article = articleService.findById(param);
			String url = getWebRoot(request) + "article/detail/"
					+ param;
//			String htmlPath = "/" + CmsConstants.HTML_ROOT_PATH + "/"
//					+ DateUtils.getNowYear() + "/" + DateUtils.getNowMonth()
//					+ "/" + DateUtils.getNowDay() + "/";
//			String fileName = article.getId() + CmsConstants.HTML_SUFFIX;
			String ctxPath = getAppRoot(request);
			try {
				HTMLGeneratorUtils.generateHtml(request, url, ctxPath,
						article.getFileUrl(), "UTF-8");
			} catch (IOException e) {
				model.put(Constants.AJAX_MSG_NAME, Constants.AJAX_MSG_ERROR);
				e.printStackTrace();
				return model;
			}
		} else if (type.equals("cate")) {//
			List<Article> articles = articleService.findByProperty(
					"categoryId", Integer.parseInt(param));
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
		} else if (type.equals("all")) {//
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
		model.put("count", count);
		return model;
	}

}
