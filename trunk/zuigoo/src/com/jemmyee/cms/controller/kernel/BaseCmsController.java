package com.jemmyee.cms.controller.kernel;

import org.springframework.beans.factory.annotation.Autowired;

import com.jemmyee.cms.entity.Article;
import com.jemmyee.cms.entity.ArticleCategory;
import com.jemmyee.cms.entity.Link;
import com.jemmyee.cms.service.IArticleCategoryService;
import com.jemmyee.cms.service.IArticleService;
import com.jemmyee.cms.service.ILinkService;
import com.jemmyee.framework.web.controller.kernel.BaseAdminController;
import com.jemmyee.taoke.entity.TaokeCategory;
import com.jemmyee.taoke.service.ITaokeCategoryService;

public class BaseCmsController extends BaseAdminController {
	// service 注入
	//cms
	@Autowired
	public IArticleCategoryService<ArticleCategory> articleCategoryService;
	@Autowired
	public IArticleService<Article> articleService;
	@Autowired
	public ILinkService<Link> linkService;
	@Autowired
	public ITaokeCategoryService<TaokeCategory> taokeCategoryService;
	
	
}
