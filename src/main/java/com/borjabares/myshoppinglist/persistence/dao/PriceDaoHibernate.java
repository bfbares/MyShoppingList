package com.borjabares.myshoppinglist.persistence.dao;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDaoHibernate;
import com.borjabares.myshoppinglist.persistence.model.Price;
import org.springframework.stereotype.Repository;

@Repository
public class PriceDaoHibernate extends GenericDaoHibernate<Price> implements PriceDao {
}
