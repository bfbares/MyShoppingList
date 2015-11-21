package com.borjabares.myshoppinglist.persistence.dao.util;

import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import com.borjabares.myshoppinglist.util.Joiner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDaoHibernate<E> implements GenericDao<E> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "punMsl")
    private EntityManager entityManager;

    private Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public GenericDaoHibernate() {
        this.entityClass = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected EntityManager getEm() {
        return entityManager;
    }

    public void save(E entity) {
        getEm().persist(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E find(long id) {
        E entity = getEm().find(entityClass, id);
        if (entity == null) {
            throw new InstanceNotFoundException(id, entityClass.getName());
        }
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E find(long id, Joiner<E> joiner) {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);

        Root<?> root = joiner.makeJoins(query, entityClass);

        query.where(criteriaBuilder.equal(root.get("id"), id));

        E entity = getEm().createQuery(query).getSingleResult();

        if (entity == null) {
            throw new InstanceNotFoundException(id, entityClass.getName());
        }
        return entity;
    }

    @Override
    public boolean exists(long id) {
        return getEm().find(entityClass, id) != null;
    }

    @Override
    public void remove(long id) {
        getEm().remove(find(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        CriteriaQuery<E> query = getEm().getCriteriaBuilder().createQuery(entityClass);
        query.from(entityClass);

        return getEm().createQuery(query).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> getAll(Joiner<E> joiner) {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);

        joiner.makeJoins(query, entityClass);

        return getEm().createQuery(query).getResultList();
    }

    @Override
    public long getCount() {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        query.select(criteriaBuilder.count(query.from(entityClass)));

        return getEm().createQuery(query).getSingleResult();
    }
}
