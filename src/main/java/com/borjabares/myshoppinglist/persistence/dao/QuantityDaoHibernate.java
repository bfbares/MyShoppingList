package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import com.borjabares.myshoppinglist.persistence.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

@Repository
public class QuantityDaoHibernate extends GenericDaoHibernate<Quantity> implements QuantityDao {

    @Override
    @SuppressWarnings("unchecked")
    public Quantity findByArticleAndCart(long idArticle, long idCart) {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<Quantity> query = criteriaBuilder.createQuery(Quantity.class);

        Root<Quantity> quantityRoot = query.from(Quantity.class);
        Join<Quantity, Article> articleFetch = (Join<Quantity, Article>) quantityRoot.fetch(Quantity_.article);
        Join<Quantity, Cart> cartFetch = (Join<Quantity, Cart>) quantityRoot.fetch(Quantity_.cart);

        query.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(articleFetch.get(Article_.id), idArticle),
                        criteriaBuilder.equal(cartFetch.get(Cart_.id), idCart)
                ));

        Quantity quantity = getEm().createQuery(query).getSingleResult();

        if (quantity == null) {
            throw new InstanceNotFoundException(idArticle + " " + idCart, Quantity.class.getName());
        }
        return quantity;
    }
}
