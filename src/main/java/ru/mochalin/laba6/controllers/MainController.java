package ru.mochalin.laba6.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import ru.mochalin.laba6.dao.CollectionDao;
import ru.mochalin.laba6.dao.GameDao;
import ru.mochalin.laba6.models.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private Parent card;
    private List<Parent> views = new ArrayList<>();;
    private FXMLLoader loader;
    private GameDao gameDao;
    private CollectionDao collectionDao;
    @FXML
    private VBox rightVBox;

    @FXML
    void onCollections(ActionEvent event) {

    }

    @FXML
    void onCreate(ActionEvent event) {

    }

    @FXML
    void onDelete(ActionEvent event) {

    }

    @FXML
    void onEdit(ActionEvent event) {

    }

    @FXML
    void onGame(ActionEvent event) {

    }
    @FXML
    protected void initialize(){
        gameDao = new GameDao();
        List<Game> games = (List<Game>) gameDao.findAll();
        for (Game game : games) {
            try {
                loader = new FXMLLoader();
                loader.setLocation(MainController.class.getResource("/ru/mochalin/laba6/fxmls/card-view.fxml"));
                card = loader.load();
                CardController controller = loader.getController();
                controller.setGame(game);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            views.add(card);
            rightVBox.getChildren().add(card);
        }
    }
}
