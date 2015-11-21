package com.borjabares.myshoppinglist.persistence.model.meta;

import com.borjabares.myshoppinglist.persistence.model.Article;
import com.borjabares.myshoppinglist.persistence.model.Cart;
import com.borjabares.myshoppinglist.persistence.model.Quantity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Quantity.class)
public class Quantity_ {
    public static volatile SingularAttribute<Quantity, Long> id;
    public static volatile SingularAttribute<Quantity, Integer> quantity;
    public static volatile SingularAttribute<Quantity, Cart> cart;
    public static volatile SingularAttribute<Quantity, Article> article;
}
