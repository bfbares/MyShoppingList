package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDao;
import com.borjabares.myshoppinglist.persistence.model.Article;

import java.util.List;

public interface ArticleDao extends GenericDao<Article> {
    Article findByName(String name);

    List getFromCategoryName(String categoryName);
}
