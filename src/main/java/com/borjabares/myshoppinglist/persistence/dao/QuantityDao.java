package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDao;
import com.borjabares.myshoppinglist.persistence.model.Quantity;

public interface QuantityDao extends GenericDao<Quantity> {
    Quantity findByArticleAndCart(long idArticle, long idCart);
}
