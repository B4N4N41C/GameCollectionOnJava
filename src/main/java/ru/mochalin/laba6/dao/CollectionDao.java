package ru.mochalin.laba6.dao;

import ru.mochalin.laba6.MainApplication;
import ru.mochalin.laba6.models.Collections;
import ru.mochalin.laba6.models.Game;
import ru.mochalin.laba6.utils.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionDao implements Dao<Collections> {
    protected List<Collections> mapper(ResultSet rs) {
        List<Collections> list = new ArrayList<>();
        try {
            while (rs.next()) {
                list.add(
                        new Collections(
                                rs.getLong("id"),
                                rs.getString("photo"),
                                rs.getString("name"),
                                rs.getString("summary"),
                                rs.getTimestamp("created_date")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Collection<Collections> findAll() {
        List<Collections> list = null;
        ResultSet rs;
        try (
                PreparedStatement statement = MainApplication.getConnection()
                        .prepareStatement(DBHelper.getProperty("collection.find_all"))
        ) {
            rs = statement.executeQuery();
            list = mapper(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //TODO Обработать ошибку запроса
        }
        return list;
    }

    @Override
    public Collection<Collections> findById(long id) {
        List<Collections> list = null;
        ResultSet rs;
        try (
                PreparedStatement statement = MainApplication.getConnection()
                        .prepareStatement(DBHelper.getProperty("collection.find_by_id"))
        ) {
            statement.setLong(1,id);
            rs = statement.executeQuery();
            list = mapper(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //TODO Обработать ошибку запроса
        }
        return list;
    }

    @Override
    public Collections save(Collections entity) {
        try (
                PreparedStatement statement = MainApplication.getConnection()
                        .prepareStatement(DBHelper.getProperty("collection.insert"))
        ) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSummary());
            statement.setString(3, entity.getPhoto());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }

    @Override
    public Collections update(Collections entity) {
        try (
                PreparedStatement statement = MainApplication.getConnection()
                        .prepareStatement(DBHelper.getProperty("collection.update"))
        ) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSummary());
            statement.setString(3, entity.getPhoto());
            statement.setLong(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }

    @Override
    public long deleteById(long id) {
        try (
                PreparedStatement statement = MainApplication.getConnection()
                        .prepareStatement(DBHelper.getProperty("collection.delete"))
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
}
