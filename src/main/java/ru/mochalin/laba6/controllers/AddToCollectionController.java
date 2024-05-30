package ru.mochalin.laba6.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lombok.Setter;
import ru.mochalin.laba6.dao.CollectionDao;
import ru.mochalin.laba6.dao.GameCollectionDao;
import ru.mochalin.laba6.dao.GameDao;
import ru.mochalin.laba6.models.Collections;
import ru.mochalin.laba6.models.Game;
import ru.mochalin.laba6.models.GameCollection;

import java.io.IOException;
import java.util.List;

/**
 * Данный контроллер является модальным окном которое вызывается при нажатии на кнопку
 * добавления игры в подборку в классе MainController.
 */
public class AddToCollectionController {
    CollectionDao collectionDao = new CollectionDao();
    GameCollectionDao gameCollectionDao = new GameCollectionDao();
    GameCollection gameCollection;
    @FXML
    private ListView<Collections> listCollection;

    @Setter
    private Game game;

    @Setter
    private Stage dialogStage;

    @FXML
    protected void initialize() {
        List<Collections> collections = (List<Collections>) collectionDao.findAll();
        ObservableList<Collections> observableCollections = FXCollections.observableArrayList(collections);
        listCollection.setItems(observableCollections);
        listCollection.setCellFactory(param -> new ListCell<Collections>() {
            @Override
            protected void updateItem(Collections item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    @FXML
    private void onAdd(ActionEvent actionEvent) {
        Collections selectedCollection = listCollection.getSelectionModel().getSelectedItem();
        gameCollection = new GameCollection();
        if (selectedCollection != null) {
            gameCollection.setCollectionId(selectedCollection.getId());
            gameCollection.setGameId(game.getId());
            gameCollectionDao.save(gameCollection);
            dialogStage.close();
        } else {
            dialogStage.close();
        }
    }
}
