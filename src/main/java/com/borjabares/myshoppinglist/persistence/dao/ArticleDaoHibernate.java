package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import com.borjabares.myshoppinglist.persistence.model.Article;
import com.borjabares.myshoppinglist.persistence.model.Article_;
import com.borjabares.myshoppinglist.persistence.model.Category;
import com.borjabares.myshoppinglist.persistence.model.Category_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import java.util.List;

@Repository
public class ArticleDaoHibernate extends GenericDaoHibernate<Article> implements ArticleDao {
    @Override
    public Article findByName(String name) {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<Article> query = criteriaBuilder.createQuery(Article.class);

        Root<Article> articleRoot = query.from(Article.class);

        query.where(criteriaBuilder.equal(criteriaBuilder.lower(articleRoot.get(Article_.name)), name.toLowerCase()));

        Article article = getEm().createQuery(query).getSingleResult();

        if (article == null) {
            throw new InstanceNotFoundException(name, Article.class.getName());
        }

        return article;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getFromCategoryName(String categoryName) {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<Article> query = criteriaBuilder.createQuery(Article.class);

        Root<Article> articleRoot = query.from(Article.class);
        SetJoin<Article, Category> categoryJoin = articleRoot.join(Article_.categories);

        query.where(criteriaBuilder.equal(criteriaBuilder.lower(categoryJoin.get(Category_.name)), categoryName.toLowerCase()));

        return getEm().createQuery(query.distinct(true)).getResultList();
    }
}
