package ru.mochalin.laba6.dao;

import java.util.Collection;
/**
 * Интерфейс Dao со свойствами <b>findAll</b>,<b>findById</b>,<b>save</b>,<b>update</b> и <b>deleteById</b>.
 * <p>
 * Данный интерфейс позволяет задать стандартное поведение для слоя доступа к данным.
 * @author Николай Мочалин
 */
public interface Dao<T> {
    Collection<T> findAll();
    Collection<T> findById(long id);
    T save(T entity);
    T update(T entity);
    long deleteById(long id);
}
