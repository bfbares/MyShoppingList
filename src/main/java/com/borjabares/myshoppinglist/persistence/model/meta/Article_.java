package com.borjabares.myshoppinglist.persistence.model.meta;

import com.borjabares.myshoppinglist.persistence.model.Article;
import com.borjabares.myshoppinglist.persistence.model.Category;
import com.borjabares.myshoppinglist.persistence.model.Price;
import com.borjabares.myshoppinglist.persistence.model.Quantity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Article.class)
public class Article_ {
    public static volatile SingularAttribute<Article, Long> id;
    public static volatile SingularAttribute<Article, String> name;
    public static volatile SingularAttribute<Article, Category> categories;
    public static volatile SingularAttribute<Article, Price> prices;
    public static volatile SingularAttribute<Article, Quantity> quantities;
}
