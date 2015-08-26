package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.bean.Quantity;
import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository
public class QuantityDaoHibernate extends GenericDaoHibernate<Quantity> implements QuantityDao {
    @Override
    public Quantity findByArticleAndCart(long idArticle, long idCart) {
        return (Quantity) getSession()
                .createQuery("SELECT q FROM Quantity q " +
                        "INNER JOIN FETCH q.article a " +
                        "INNER JOIN FETCH q.cart c " +
                        "WHERE a.id = :idArticle " +
                        "AND c.id = :idCart")
                .setParameter("idArticle", idArticle)
                .setParameter("idCart", idCart)
                .uniqueResult();
    }
}
