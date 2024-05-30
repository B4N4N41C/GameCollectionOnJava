package ru.mochalin.laba6.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Setter;
import ru.mochalin.laba6.MainApplication;
import ru.mochalin.laba6.dao.GameCollectionDao;
import ru.mochalin.laba6.models.Collections;
import ru.mochalin.laba6.models.Game;
import ru.mochalin.laba6.models.GameCollection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Данный контроллер является модальным окном которое вызывается при нажатии на кнопку
 * открытия подборки в классе MainController.
 */
public class GameInCollectionController {

    @Setter
    private Stage dialogStage;

    private List<Parent> views = new ArrayList<>();

    private Parent card;

    private FXMLLoader loader;

    List<Game> gameList;

    @FXML
    private VBox vBox;


    public void setCollection(Collections collection) {
        this.collection = collection;
        updateList();
    }

    Collections collection;

    private GameCollectionDao gameCollectionDao = new GameCollectionDao();

    public void updateList() {
        ResultSet rs;
        vBox.getChildren().clear();
        gameCollectionDao = new GameCollectionDao();
        List<GameCollection> gameCollections = (List<GameCollection>) gameCollectionDao
                .findAllGameInCollections(collection);
        String statmen = "SELECT * FROM game WHERE ";
        if(gameCollections.size() > 0) {
            for (GameCollection gameCollection : gameCollections) {
                if (gameCollection != gameCollections.get(gameCollections.size() - 1)){
                    statmen += "id = " + gameCollection.getGameId() + " OR ";
                } else {
                    statmen += "id = " + gameCollection.getGameId();
                }
            }
        } else {
            for (GameCollection gameCollection : gameCollections) {
                statmen += "id = " + gameCollection.getGameId();
            }
        }

        try (
                PreparedStatement statement = MainApplication.getConnection()
                        .prepareStatement(statmen)
        ) {
            rs = statement.executeQuery();
            gameList = mapper(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for (Game game : gameList) {
            try {
                loader = new FXMLLoader();
                loader.setLocation(MainController.class.getResource("/ru/mochalin/laba6/fxmls/card-view.fxml"));
                card = loader.load();
                CardController controller = loader.getController();
                controller.setGameInCollectionController(this);
                controller.setCollectionForGame(collection);
                controller.setInCollection(true);
                controller.setGame(game);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            views.add(card);
            vBox.getChildren().add(card);
        }
    }

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
}
