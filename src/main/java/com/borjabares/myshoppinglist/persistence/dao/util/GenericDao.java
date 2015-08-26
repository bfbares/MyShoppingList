package com.borjabares.myshoppinglist.persistence.dao.util;

import com.borjabares.myshoppinglist.util.Expander;

import java.util.List;

public interface GenericDao<E> {
    void save(E entity);

    E find(long id);

    E find(long id, Expander<E> expander);

    boolean exists(long id);

    void remove(long id);

    List<E> getAll();

    List<E> getAll(Expander<E> expander);

    long getCount();
}