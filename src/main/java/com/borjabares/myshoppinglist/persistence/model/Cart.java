package com.borjabares.myshoppinglist.persistence.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "carts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cart implements Serializable {
    private static final long serialVersionUID = 5622166706707474363L;

    private long id;
    private String name;
    private Calendar created;
    private Calendar bought;
    private Shop shop;
    private Set<Quantity> quantities;

    public Cart() {
        this.created = Calendar.getInstance();
    }

    @Id
    @GeneratedValue
    @Column(name = "id_cart")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    @Column(name = "bought")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getBought() {
        return bought;
    }

    public void setBought(Calendar bought) {
        this.bought = bought;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_shop")
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
    public Set<Quantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(Set<Quantity> quantities) {
        this.quantities = quantities;
    }
}
