package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.bean.Article;
import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleDaoHibernate extends GenericDaoHibernate<Article> implements ArticleDao {
    @Override
    public Article findByName(String name) {
        return (Article) getSession()
                .createQuery("SELECT a FROM Article a " +
                        "WHERE LOWER(a.name) = LOWER(:name)")
                .setParameter("name", name)
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getFromCategoryName(String categoryName) {
        return getSession()
                .createQuery("SELECT DISTINCT a FROM Article a " +
                        "INNER JOIN a.categories c " +
                        "WHERE LOWER(c.name) = LOWER(:categoryName)")
                .setParameter("categoryName", categoryName)
                .list();
    }
}
