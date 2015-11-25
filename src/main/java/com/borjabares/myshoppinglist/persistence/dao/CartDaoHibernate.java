package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import com.borjabares.myshoppinglist.persistence.model.Cart;
import com.borjabares.myshoppinglist.persistence.model.Cart_;
import com.borjabares.myshoppinglist.persistence.model.Quantity_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class CartDaoHibernate extends GenericDaoHibernate<Cart> implements CartDao {
    @Override
    public Cart findWithArticles(long idCart) {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<Cart> query = criteriaBuilder.createQuery(Cart.class);

        Root<Cart> cartRoot = query.from(Cart.class);
        cartRoot.fetch(Cart_.quantities).fetch(Quantity_.article);

        query.where(criteriaBuilder.equal(cartRoot.get(Cart_.id), idCart));

        Cart cart = getEm().createQuery(query).getSingleResult();

        if (cart == null) {
            throw new InstanceNotFoundException(idCart, Cart.class.getName());
        }

        return cart;
    }
}
