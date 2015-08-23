package com.jemmyee.cms.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jemmyee.cms.dao.IArticleCategoryDao;
import com.jemmyee.cms.entity.ArticleCategory;
import com.jemmyee.cms.service.IArticleCategoryService;
import com.jemmyee.framework.dao.impl.BaseDaoImpl;

@Service("articleCategoryService")
public class ArticleCategoryServiceImpl extends BaseDaoImpl<ArticleCategory> implements IArticleCategoryService<ArticleCategory> {
	
	private static final Log log = LogFactory.getLog(ArticleCategoryServiceImpl.class);

	@Autowired
	private IArticleCategoryDao articleCategoryDao;
	
}
