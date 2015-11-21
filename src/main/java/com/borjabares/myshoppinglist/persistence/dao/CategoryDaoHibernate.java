package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import com.borjabares.myshoppinglist.persistence.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoHibernate extends GenericDaoHibernate<Category> implements CategoryDao {
    @Override
    public Category findByName(String name) {
        Category category = (Category) getEm().createQuery("SELECT c FROM Category c " +
                "WHERE LOWER(c.name) = LOWER(:name)")
                .setParameter("name", name)
                .getSingleResult();
        if (category == null) {
            throw new InstanceNotFoundException(name, Category.class.getName());
        }
        return category;
    }
}
