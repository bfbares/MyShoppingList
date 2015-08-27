package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.bean.Shop;
import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDaoHibernate extends GenericDaoHibernate<Shop> implements ShopDao {
    @Override
    public Shop findWithArticles(long idShop) {
        Shop shop = (Shop) getSession()
                .createQuery("SELECT s FROM Shop s " +
                        "LEFT JOIN FETCH s.prices p " +
                        "LEFT JOIN FETCH p.article a " +
                        "WHERE s.id = :id")
                .setParameter("id", idShop)
                .uniqueResult();

        if (shop == null) {
            throw new InstanceNotFoundException(idShop, Shop.class.getName());
        }

        return shop;
    }
}
