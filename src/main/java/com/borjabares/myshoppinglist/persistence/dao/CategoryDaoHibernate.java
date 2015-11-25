package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import com.borjabares.myshoppinglist.persistence.model.Category;
import com.borjabares.myshoppinglist.persistence.model.Category_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class CategoryDaoHibernate extends GenericDaoHibernate<Category> implements CategoryDao {
    @Override
    public Category findByName(String name) {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<Category> query = criteriaBuilder.createQuery(Category.class);

        Root<Category> categoryRoot = query.from(Category.class);

        query.where(criteriaBuilder.equal(criteriaBuilder.lower(categoryRoot.get(Category_.name)), name.toLowerCase()));

        Category category = getEm().createQuery(query).getSingleResult();

        if (category == null) {
            throw new InstanceNotFoundException(name, Category.class.getName());
        }
        return category;
    }
}
