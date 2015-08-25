package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.bean.Category;
import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoHibernate extends GenericDaoHibernate<Category> implements CategoryDao {
    @Override
    public Category findByName(String name) {
        return (Category) getSession().createQuery("SELECT c FROM Category c " +
                "WHERE c.name = :name")
                .setParameter("name", name)
                .uniqueResult();
    }
}
