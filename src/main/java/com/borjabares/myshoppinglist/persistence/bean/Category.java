package com.borjabares.myshoppinglist.persistence.bean;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categories")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Category implements Serializable {
    private static final long serialVersionUID = 8637517320504138397L;

    private long id;
    private String name;

    public Category() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id_category")
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
}
