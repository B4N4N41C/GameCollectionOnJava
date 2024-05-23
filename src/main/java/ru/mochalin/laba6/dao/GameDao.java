package ru.mochalin.laba6.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import ru.mochalin.laba6.MainApplication;
import ru.mochalin.laba6.models.Game;
import ru.mochalin.laba6.utils.DBHelper;

public class GameDao implements Dao<Game> {
    protected List<Game> mapper(ResultSet rs) {
        List<Game> list = new ArrayList<>();
        try {
            while (rs.next()) {
                list.add(
                        new Game(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("summary"),
                                rs.getInt("mark"),
                                rs.getString("photo"),
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
    public Collection<Game> findAll() {
        List<Game> list = null;
        ResultSet rs;
        try (
                PreparedStatement statement = MainApplication.getConnection()
                        .prepareStatement(DBHelper.getProperty("game.find_all"))
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
    public Collection<Game> findById(long id) {
        List<Game> list = null;
        ResultSet rs;
        try (
                PreparedStatement statement = MainApplication.getConnection()
                        .prepareStatement(DBHelper.getProperty("game.find_by_id"))
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
    public Game save(Game entity) {
        try (
                PreparedStatement statement = MainApplication.getConnection()
                        .prepareStatement(DBHelper.getProperty("game.insert"))
        ) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSummary());
            statement.setInt(3, entity.getMark());
            statement.setString(4, entity.getPhoto());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }

    @Override
    public Game update(Game entity) {
        try (
                PreparedStatement statement = MainApplication.getConnection()
                        .prepareStatement(DBHelper.getProperty("game.update"))
        ) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSummary());
            statement.setInt(3, entity.getMark());
            statement.setString(4, entity.getPhoto());
            statement.setLong(5, entity.getId());
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
                        .prepareStatement(DBHelper.getProperty("game.delete"))
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
}
