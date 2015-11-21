package com.borjabares.myshoppinglist.persistence.service.util;

import com.borjabares.myshoppinglist.util.Joiner;

import java.util.List;

public interface GenericService<E> {
    E find(long id);

    E find(long id, Joiner<E> joiner);

    boolean exists(long id);

    List<E> getAll();

    List<E> getAll(Joiner<E> joiner);

    long getCount();

    void save(E entity);

    void remove(long id);
}
