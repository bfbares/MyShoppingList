package com.borjabares.myshoppinglist.persistence.service.util;

import com.borjabares.myshoppinglist.persistence.dao.util.GenericDao;
import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class GenericServiceImpl<E> implements GenericService<E> {
    @Autowired private GenericDao<E> genericDao;

    @Override
    @Transactional(readOnly = true)
    public E find(long id) {
        return genericDao.find(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(long id) {
        return genericDao.exists(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> getAll(){
        return genericDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount(){
        return genericDao.getCount();
    }

    @Override
    @Transactional
    public void save(E entity){
        genericDao.save(entity);
    }

    @Override
    @Transactional
    public void remove(long id){
        genericDao.remove(id);
    }

}
