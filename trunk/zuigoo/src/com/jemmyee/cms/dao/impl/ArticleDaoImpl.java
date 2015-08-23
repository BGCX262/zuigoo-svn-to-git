package com.jemmyee.cms.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.jemmyee.cms.dao.IArticleDao;
import com.jemmyee.cms.entity.Article;
import com.jemmyee.framework.dao.impl.BaseDaoImpl;

@Repository("articleDao")
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements IArticleDao {
	
	private static final Log log = LogFactory.getLog(ArticleDaoImpl.class);

}
