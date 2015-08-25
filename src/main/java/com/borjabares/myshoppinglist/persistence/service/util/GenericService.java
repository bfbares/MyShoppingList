package com.borjabares.myshoppinglist.persistence.service.util;

import java.util.List;

public interface GenericService<E> {
    E find(long id);

    boolean exists(long id);

    List<E> getAll();

    long getCount();

    void save(E entity);

    void remove(long id);
}
