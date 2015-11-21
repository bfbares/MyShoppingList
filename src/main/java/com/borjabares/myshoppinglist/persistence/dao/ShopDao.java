package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDao;
import com.borjabares.myshoppinglist.persistence.model.Shop;

public interface ShopDao extends GenericDao<Shop> {
    Shop findWithArticles(long idShop);
}
