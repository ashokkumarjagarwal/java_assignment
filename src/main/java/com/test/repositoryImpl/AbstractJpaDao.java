package com.test.repositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public abstract class AbstractJpaDao<T extends Serializable> {

    private Class<T> clazz;

    @PersistenceContext
    private EntityManager em;

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T findOne(int id) {
        return em.find(clazz, id);
    }

    public T save(T entity) {
        em.persist(entity);
        return entity;
    }
}
