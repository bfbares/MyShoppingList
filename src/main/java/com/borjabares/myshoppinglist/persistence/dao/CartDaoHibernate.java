package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import com.borjabares.myshoppinglist.persistence.model.Cart;
import org.springframework.stereotype.Repository;

@Repository
public class CartDaoHibernate extends GenericDaoHibernate<Cart> implements CartDao {
    @Override
    public Cart findWithArticles(long idCart) {
        Cart cart = (Cart) getEm()
                .createQuery("SELECT c FROM Cart c " +
                        "LEFT JOIN FETCH c.quantities q " +
                        "LEFT JOIN FETCH q.article a " +
                        "WHERE c.id = :id")
                .setParameter("id", idCart)
                .getSingleResult();

        if (cart == null) {
            throw new InstanceNotFoundException(idCart, Cart.class.getName());
        }

        return cart;
    }
}
