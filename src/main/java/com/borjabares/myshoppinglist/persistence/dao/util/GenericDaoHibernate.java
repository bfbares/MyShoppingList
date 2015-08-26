package com.borjabares.myshoppinglist.persistence.dao.util;

import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import com.borjabares.myshoppinglist.util.Expander;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDaoHibernate<E> implements GenericDao<E> {

    private SessionFactory sessionFactory;

    private Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public GenericDaoHibernate() {
        this.entityClass = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(E entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E find(long id) {
        E entity = (E) getSession().get(entityClass, id);
        if (entity == null) {
            throw new InstanceNotFoundException(id, entityClass.getName());
        }
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E find(long id, Expander<E> expander) {
        E entity = (E) getSession().createQuery("SELECT DISTINCT e FROM "+entityClass.getName()+" e " +
                expander.getJoins()+
                " WHERE e.id = :id")
                .setParameter("id", id)
                .uniqueResult();
        if (entity == null) {
            throw new InstanceNotFoundException(id, entityClass.getName());
        }
        return entity;
    }

    @Override
    public boolean exists(long id) {
        return getSession().createCriteria(entityClass).add(
                Restrictions.idEq(id)).setProjection(Projections.id())
                .uniqueResult() != null;
    }

    @Override
    public void remove(long id) {
        getSession().delete(find(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        return getSession().createCriteria(entityClass).list();
    }

    @Override
    public long getCount() {
        return ((Number) getSession().createCriteria(entityClass).setProjection(Projections.rowCount()).uniqueResult()).longValue();
    }
}
