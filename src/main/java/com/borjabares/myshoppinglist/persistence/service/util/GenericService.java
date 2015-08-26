package com.borjabares.myshoppinglist.persistence.service.util;

import com.borjabares.myshoppinglist.util.Expander;

import java.util.List;

public interface GenericService<E> {
    E find(long id);

    E find(long id, Expander<E> expander);

    boolean exists(long id);

    List<E> getAll();

    List<E> getAll(Expander<E> expander);

    long getCount();

    void save(E entity);

    void remove(long id);
}
