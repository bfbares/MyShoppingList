package com.borjabares.myshoppinglist.persistence.model.meta;

import com.borjabares.myshoppinglist.persistence.model.Cart;
import com.borjabares.myshoppinglist.persistence.model.Price;
import com.borjabares.myshoppinglist.persistence.model.Shop;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Shop.class)
public class Shop_ {
    public static volatile SingularAttribute<Shop, Long> id;
    public static volatile SingularAttribute<Shop, String> name;
    public static volatile SingularAttribute<Shop, Price> prices;
    public static volatile SingularAttribute<Shop, Cart> carts;
}
