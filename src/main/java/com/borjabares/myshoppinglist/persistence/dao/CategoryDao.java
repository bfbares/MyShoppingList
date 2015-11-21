package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDao;
import com.borjabares.myshoppinglist.persistence.model.Category;

public interface CategoryDao extends GenericDao<Category> {
    Category findByName(String name);
}
