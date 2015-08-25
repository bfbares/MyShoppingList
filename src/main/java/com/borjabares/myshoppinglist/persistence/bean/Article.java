package com.borjabares.myshoppinglist.persistence.bean;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "articles")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Article implements Serializable {
    private static final long serialVersionUID = -8486115604705761246L;

    private long id;
    private String name;
    private Collection<Category> categories;
    private Collection<Price> prices;
    private Collection<Quantity> quantities;

    public Article() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id_article")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", length = 128, updatable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "articles_categories",
            joinColumns = {@JoinColumn(name = "id_article")},
            inverseJoinColumns = {@JoinColumn(name = "id_category")})
    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    public Collection<Price> getPrices() {
        return prices;
    }

    public void setPrices(Collection<Price> prices) {
        this.prices = prices;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    public Collection<Quantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(Collection<Quantity> quantities) {
        this.quantities = quantities;
    }
}
