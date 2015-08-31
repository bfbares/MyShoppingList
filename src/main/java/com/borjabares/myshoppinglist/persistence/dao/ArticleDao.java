package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.bean.Article;
import com.borjabares.myshoppinglist.persistence.dao.util.GenericDao;

import java.util.List;

public interface ArticleDao extends GenericDao<Article> {
    Article findByName(String name);

    List getFromCategoryName(String categoryName);
}
