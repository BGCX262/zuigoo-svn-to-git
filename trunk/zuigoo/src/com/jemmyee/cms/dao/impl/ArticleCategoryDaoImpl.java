package com.jemmyee.cms.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.jemmyee.cms.dao.IArticleCategoryDao;
import com.jemmyee.cms.entity.ArticleCategory;
import com.jemmyee.framework.dao.impl.BaseDaoImpl;

@Repository("articleCategoryDao")
public class ArticleCategoryDaoImpl extends BaseDaoImpl<ArticleCategory> implements IArticleCategoryDao {
	
	private static final Log log = LogFactory.getLog(ArticleCategoryDaoImpl.class);

}
