package com.borjabares.myshoppinglist.persistence.service;

import com.borjabares.myshoppinglist.persistence.dao.ArticleDao;
import com.borjabares.myshoppinglist.persistence.dao.CategoryDao;
import com.borjabares.myshoppinglist.persistence.model.Article;
import com.borjabares.myshoppinglist.persistence.model.Category;
import com.borjabares.myshoppinglist.persistence.service.util.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category> implements CategoryService {
    private final CategoryDao categoryDao;
    @Autowired private ArticleDao articleDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        super(categoryDao);
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticlesFromName(String categoryName) {
        return articleDao.getFromCategoryName(categoryName);
    }
}
