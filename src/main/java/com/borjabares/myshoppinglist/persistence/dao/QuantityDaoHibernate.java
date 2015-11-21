package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import com.borjabares.myshoppinglist.persistence.model.Quantity;
import org.springframework.stereotype.Repository;

@Repository
public class QuantityDaoHibernate extends GenericDaoHibernate<Quantity> implements QuantityDao {
    @Override
    public Quantity findByArticleAndCart(long idArticle, long idCart) {
        Quantity quantity = (Quantity) getEm()
                .createQuery("SELECT q FROM Quantity q " +
                        "INNER JOIN FETCH q.article a " +
                        "INNER JOIN FETCH q.cart c " +
                        "WHERE a.id = :idArticle " +
                        "AND c.id = :idCart")
                .setParameter("idArticle", idArticle)
                .setParameter("idCart", idCart)
                .getSingleResult();
        if (quantity == null) {
            throw new InstanceNotFoundException(idArticle + " " + idCart, Quantity.class.getName());
        }
        return quantity;
    }
}
