package com.borjabares.myshoppinglist.persistence.dao.util;

import java.util.List;

public interface GenericDao<E> {
    void save(E entity);

    E find(long id);

    boolean exists(long id);

    void remove(long id);

    List<E> getAll();

    long getCount();
}