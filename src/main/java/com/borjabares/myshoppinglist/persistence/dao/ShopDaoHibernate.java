package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import com.borjabares.myshoppinglist.persistence.model.Price_;
import com.borjabares.myshoppinglist.persistence.model.Shop;
import com.borjabares.myshoppinglist.persistence.model.Shop_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class ShopDaoHibernate extends GenericDaoHibernate<Shop> implements ShopDao {
    @Override
    public Shop findWithArticles(long idShop) {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<Shop> query = criteriaBuilder.createQuery(Shop.class);

        Root<Shop> shopRoot = query.from(Shop.class);

        shopRoot.fetch(Shop_.prices).fetch(Price_.article);

        query.where(criteriaBuilder.equal(shopRoot.get(Shop_.id), idShop));

        Shop shop = getEm().createQuery(query).getSingleResult();

        if (shop == null) {
            throw new InstanceNotFoundException(idShop, Shop.class.getName());
        }

        return shop;
    }
}
