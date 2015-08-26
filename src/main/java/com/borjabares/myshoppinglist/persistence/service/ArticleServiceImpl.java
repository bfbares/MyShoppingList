package com.borjabares.myshoppinglist.persistence.service;

import com.borjabares.myshoppinglist.persistence.bean.Article;
import com.borjabares.myshoppinglist.persistence.bean.Category;
import com.borjabares.myshoppinglist.persistence.dao.ArticleDao;
import com.borjabares.myshoppinglist.persistence.dao.CategoryDao;
import com.borjabares.myshoppinglist.persistence.dao.util.GenericDao;
import com.borjabares.myshoppinglist.persistence.service.util.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleServiceImpl extends GenericServiceImpl<Article> implements ArticleService {
    private final ArticleDao articleDao;
    @Autowired private CategoryDao categoryDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao) {
        super(articleDao);
        this.articleDao = articleDao;
    }

    @Override
    @Transactional
    public Article addCategory(long idArticle, String categoryName) {
        Article article = articleDao.find(idArticle);

        Category category = categoryDao.findByName(categoryName);
        if (category == null) {
            category = new Category(categoryName);
            categoryDao.save(category);
            article.getCategories().add(category);
        }

        article.getCategories().add(category);

        articleDao.save(article);

        return article;
    }
}
