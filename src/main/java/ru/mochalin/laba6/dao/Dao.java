package ru.mochalin.laba6.dao;

import java.util.Collection;

public interface Dao<T> {
    Collection<T> findAll();
    Collection<T> findById(long id);
    T save(T entity);
    T update(T entity);
    long deleteById(long id);
}
