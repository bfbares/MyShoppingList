package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.bean.Shop;
import com.borjabares.myshoppinglist.persistence.dao.util.GenericDao;

public interface ShopDao extends GenericDao<Shop> {
    Shop findWithArticles(long idShop);
}
