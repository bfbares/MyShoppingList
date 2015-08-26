package com.borjabares.myshoppinglist.persistence.service;

import com.borjabares.myshoppinglist.persistence.bean.Article;
import com.borjabares.myshoppinglist.persistence.service.util.GenericService;

public interface ArticleService extends GenericService<Article> {
    Article addCategory(long idArticle, String categoryName);
}
