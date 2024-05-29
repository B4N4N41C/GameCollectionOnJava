package ru.mochalin.laba6.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.mochalin.laba6.MainApplication;
import ru.mochalin.laba6.dao.CollectionDao;
import ru.mochalin.laba6.dao.GameDao;
import ru.mochalin.laba6.models.Collections;
import ru.mochalin.laba6.models.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private Parent card;
    private List<Parent> views = new ArrayList<>();
    private FXMLLoader loader;
    private GameDao gameDao;
    private CollectionDao collectionDao;
    //false = collection, true = game
    private boolean switchBetweenGameAndCollection = true;
    @FXML
    private VBox rightVBox;

    @FXML
    void onCollections(ActionEvent event) {
        switchBetweenGameAndCollection = false;
        updateList();
    }

    @FXML
    void onCreate(ActionEvent event) {
        if(switchBetweenGameAndCollection) {
            Game game = new Game();
            try {
                showDialog(game);
                gameDao.save(game);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            updateList();
        } else {
            Collections collection = new Collections();
            try {
                showDialog(collection);
                collectionDao.save(collection);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            updateList();
        }
    }

    @FXML
    void onGame(ActionEvent event) {
        switchBetweenGameAndCollection = true;
        updateList();
    }
    @FXML
    protected void initialize(){
       updateList();
    }

    public void updateList(){
        if(switchBetweenGameAndCollection){
            rightVBox.getChildren().clear();
            gameDao = new GameDao();
            List<Game> games = (List<Game>) gameDao.findAll();
            for (Game game : games) {
                try {
                    loader = new FXMLLoader();
                    loader.setLocation(MainController.class.getResource("/ru/mochalin/laba6/fxmls/card-view.fxml"));
                    card = loader.load();
                    CardController controller = loader.getController();
                    controller.setMainController(this);
                    controller.setGame(game);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                views.add(card);
                rightVBox.getChildren().add(card);
            }
        } else {
            rightVBox.getChildren().clear();
            collectionDao = new CollectionDao();
            List<Collections> collections = (List<Collections>) collectionDao.findAll();
            for (Collections collection : collections) {
                try {
                    loader = new FXMLLoader();
                    loader.setLocation(MainController.class.getResource("/ru/mochalin/laba6/fxmls/card-view.fxml"));
                    card = loader.load();
                    CardController controller = loader.getController();
                    controller.setMainController(this);
                    controller.setCollection(collection);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                views.add(card);
                rightVBox.getChildren().add(card);
            }
        }
    }

    private void showDialog(Game game) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("/ru/mochalin/laba6/fxmls/game-view.fxml"));
        Parent root = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Информация о игре");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(MainApplication.getMainStage());
        Scene scene = new Scene(root);
        addStage.setScene(scene);
        GameController controller = loader.getController();
        controller.setDialogStage(addStage);
        controller.setGame(game);
        addStage.showAndWait();
    }

    private void showDialog(Collections collection) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("/ru/mochalin/laba6/fxmls/collection-view.fxml"));
        Parent root = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Информация о подборках");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(MainApplication.getMainStage());
        Scene scene = new Scene(root);
        addStage.setScene(scene);
        CollectionController controller = loader.getController();
        controller.setDialogStage(addStage);
        controller.setCollection(collection);
        addStage.showAndWait();
    }

    public void onUpdateList(ActionEvent actionEvent) {
        updateList();
    }
}
