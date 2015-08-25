package com.borjabares.myshoppinglist.persistence.bean;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "shops")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Shop implements Serializable {
    private static final long serialVersionUID = -8141330672762078855L;

    private long id;
    private String name;
    private Collection<Price> prices;
    private Collection<Cart> carts;

    public Shop() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id_shop")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
    public Collection<Price> getPrices() {
        return prices;
    }

    public void setPrices(Collection<Price> prices) {
        this.prices = prices;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
    public Collection<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Collection<Cart> carts) {
        this.carts = carts;
    }
}
