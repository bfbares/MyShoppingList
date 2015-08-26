package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.bean.Cart;
import com.borjabares.myshoppinglist.persistence.dao.util.GenericDao;

public interface CartDao extends GenericDao<Cart> {
    Cart findWithArticles(long idCart);
}
