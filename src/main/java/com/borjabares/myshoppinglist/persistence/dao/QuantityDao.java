package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.bean.Quantity;
import com.borjabares.myshoppinglist.persistence.dao.util.GenericDao;

public interface QuantityDao extends GenericDao<Quantity> {
    Quantity findByArticleAndCart(long idArticle, long idCart);
}
