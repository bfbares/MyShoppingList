package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDao;
import com.borjabares.myshoppinglist.persistence.model.Cart;

public interface CartDao extends GenericDao<Cart> {
    Cart findWithArticles(long idCart);
}
