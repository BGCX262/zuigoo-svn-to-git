package com.jemmyee.cms.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jemmyee.cms.dao.IArticleDao;
import com.jemmyee.cms.entity.Article;
import com.jemmyee.cms.service.IArticleService;
import com.jemmyee.framework.dao.impl.BaseDaoImpl;

@Service("articleService")
public class ArticleServiceImpl extends BaseDaoImpl<Article> implements IArticleService<Article>{
	
	private static final Log log = LogFactory.getLog(ArticleServiceImpl.class);
	@Autowired
	private IArticleDao articleDao;
}
