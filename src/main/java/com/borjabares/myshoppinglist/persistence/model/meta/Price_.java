package com.borjabares.myshoppinglist.persistence.model.meta;

import com.borjabares.myshoppinglist.persistence.model.Article;
import com.borjabares.myshoppinglist.persistence.model.Price;
import com.borjabares.myshoppinglist.persistence.model.Shop;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.util.Calendar;

@StaticMetamodel(Price.class)
public class Price_ {
    public static volatile SingularAttribute<Price, Long> id;
    public static volatile SingularAttribute<Price, BigDecimal> price;
    public static volatile SingularAttribute<Price, Calendar> date;
    public static volatile SingularAttribute<Price, Article> article;
    public static volatile SingularAttribute<Price, Shop> shop;
}
