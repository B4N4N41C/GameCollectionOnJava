package ru.mochalin.laba6.dao;

import ru.mochalin.laba6.MainApplication;
import ru.mochalin.laba6.models.Collections;
import ru.mochalin.laba6.models.Game;
import ru.mochalin.laba6.models.GameCollection;
import ru.mochalin.laba6.utils.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Класс GameCollectionDao не наследуется от интерфейса Dao, так как имеет специфическую реализации,
 * из-за того что GameCollectionDao реализует взаимодействие с таблицей, которая является связью между
 * двуя другими таблицами.
 * @author Николай Мочалин
 */
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

    public GameCollection save(GameCollection entity) {
        try (
                PreparedStatement statement = MainApplication.getConnection()
                        .prepareStatement(DBHelper.getProperty("game_collection.insert"))
        ) {
            statement.setLong(1, entity.getGameId());
            statement.setLong(2, entity.getCollectionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }

    public List<GameCollection> findAllGameInCollections(Collections collection){
        List<GameCollection> list = null;
        ResultSet rs;
        try (
                PreparedStatement statement = MainApplication.getConnection()
                        .prepareStatement(DBHelper.getProperty("game_collection.find_by_collection_id"))
        ) {
            statement.setLong(1,collection.getId());
            rs = statement.executeQuery();
            list = mapper(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //TODO: Обработать ошибку запроса
        }
        return list;
    }

    public void delete(Game game, Collections collection) {
        try (
                PreparedStatement statement = MainApplication.getConnection()
                        .prepareStatement(DBHelper.getProperty("game_collection.delete"))
        ) {
            statement.setLong(1, game.getId());
            statement.setLong(2, collection.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
