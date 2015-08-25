package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.bean.Category;
import com.borjabares.myshoppinglist.persistence.dao.util.GenericDao;

public interface CategoryDao extends GenericDao<Category> {
    Category findByName(String name);
}
