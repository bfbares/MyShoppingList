package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.bean.Article;
import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDaoHibernate extends GenericDaoHibernate<Article> implements ArticleDao {
}
