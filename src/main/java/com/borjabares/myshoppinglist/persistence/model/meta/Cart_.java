package com.borjabares.myshoppinglist.persistence.model.meta;

import com.borjabares.myshoppinglist.persistence.model.Cart;
import com.borjabares.myshoppinglist.persistence.model.Quantity;
import com.borjabares.myshoppinglist.persistence.model.Shop;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Calendar;

@StaticMetamodel(Cart.class)
public class Cart_ {
    public static volatile SingularAttribute<Cart, Long> id;
    public static volatile SingularAttribute<Cart, String> name;
    public static volatile SingularAttribute<Cart, Calendar> created;
    public static volatile SingularAttribute<Cart, Calendar> bought;
    public static volatile SingularAttribute<Cart, Shop> shop;
    public static volatile SingularAttribute<Cart, Quantity> quantities;
}
