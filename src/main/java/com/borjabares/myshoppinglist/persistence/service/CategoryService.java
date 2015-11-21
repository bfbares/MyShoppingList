package com.borjabares.myshoppinglist.persistence.service;

import com.borjabares.myshoppinglist.persistence.model.Article;
import com.borjabares.myshoppinglist.persistence.model.Category;
import com.borjabares.myshoppinglist.persistence.service.util.GenericService;

import java.util.List;

public interface CategoryService extends GenericService<Category> {
    List<Article> getArticlesFromName(String categoryName);
}
