package com.borjabares.myshoppinglist.persistence.service;

import com.borjabares.myshoppinglist.persistence.bean.Article;
import com.borjabares.myshoppinglist.persistence.bean.Category;
import com.borjabares.myshoppinglist.persistence.dao.ArticleDao;
import com.borjabares.myshoppinglist.persistence.dao.CategoryDao;
import com.borjabares.myshoppinglist.persistence.service.util.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleServiceImpl extends GenericServiceImpl<Article> implements ArticleService {
    @Autowired private ArticleDao articleDao;
    @Autowired private CategoryDao categoryDao;

    @Override
    @Transactional
    public Article addCategory(long idArticle, Category category) {
        Article article = articleDao.find(idArticle);

        Category categoryDB = categoryDao.findByName(category.getName());
        if (categoryDB == null) {
            categoryDao.save(category);
            // TODO JOIN
            article.getCategories().add(category);
        } else {
            article.getCategories().add(categoryDB);
        }

        articleDao.save(article);

        return article;
    }
}
