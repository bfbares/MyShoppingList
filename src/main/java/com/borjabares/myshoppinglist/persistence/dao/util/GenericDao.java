package com.borjabares.myshoppinglist.persistence.dao.util;

import com.borjabares.myshoppinglist.util.Joiner;

import java.util.List;

public interface GenericDao<E> {
    void save(E entity);

    E find(long id);

    E find(long id, Joiner<E> joiner);

    boolean exists(long id);

    void remove(long id);

    List<E> getAll();

    List<E> getAll(Joiner<E> joiner);

    long getCount();
}