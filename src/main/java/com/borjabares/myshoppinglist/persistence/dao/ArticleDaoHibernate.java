package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import com.borjabares.myshoppinglist.persistence.model.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleDaoHibernate extends GenericDaoHibernate<Article> implements ArticleDao {
    @Override
    public Article findByName(String name) {
        Article article = (Article) getEm()
                .createQuery("SELECT a FROM Article a " +
                        "WHERE LOWER(a.name) = LOWER(:name)")
                .setParameter("name", name)
                .getSingleResult();

        if (article == null) {
            throw new InstanceNotFoundException(name, Article.class.getName());
        }

        return article;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getFromCategoryName(String categoryName) {
        return getEm()
                .createQuery("SELECT DISTINCT a FROM Article a " +
                        "INNER JOIN a.categories c " +
                        "WHERE LOWER(c.name) = LOWER(:categoryName)")
                .setParameter("categoryName", categoryName)
                .getResultList();
    }
}
