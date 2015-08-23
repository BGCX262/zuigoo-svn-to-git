package com.jemmyee.taoke.controller.kernel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.jemmyee.cms.entity.Article;
import com.jemmyee.cms.service.IArticleService;
import com.jemmyee.framework.web.Constants;
import com.jemmyee.framework.web.controller.kernel.BaseAdminController;
import com.jemmyee.taoke.entity.TaokeCategory;
import com.jemmyee.taoke.entity.TaokeItem;
import com.jemmyee.taoke.service.ITaokeCategoryService;
import com.jemmyee.taoke.service.ITaokeItemService;

public class BaseTaokeFrontController extends BaseAdminController {
	// service 注入
	//tao
	@Autowired
	public ITaokeCategoryService<TaokeCategory> taokeCategoryService;
	@Autowired
	public ITaokeItemService<TaokeItem> taokeItemService;
	@Autowired
	public IArticleService<Article> articleService;
	
	
	public void initView(ModelAndView mv,String ROOTURI,String view)
	{
		mv.addObject(Constants.MV_OBJECT_ROOT_URI, ROOTURI);
		mv.setViewName(view);
	}
}
