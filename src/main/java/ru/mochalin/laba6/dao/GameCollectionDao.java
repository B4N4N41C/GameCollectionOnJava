package ru.mochalin.laba6.dao;

import ru.mochalin.laba6.MainApplication;
import ru.mochalin.laba6.models.Collections;
import ru.mochalin.laba6.models.GameCollection;
import ru.mochalin.laba6.utils.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameCollectionDao {
    protected List<GameCollection> mapper(ResultSet rs) {
        List<GameCollection> list = new ArrayList<>();
        try {
            while (rs.next()) {
                list.add(
                        new GameCollection(
                                rs.getLong("id"),
                                rs.getLong("game_id"),
                                rs.getLong("collection_id")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Collection<GameCollection> findAll() {
        List<GameCollection> list = null;
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
}
