package ru.mochalin.laba6.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
import ru.mochalin.laba6.MainApplication;
import ru.mochalin.laba6.dao.CollectionDao;
import ru.mochalin.laba6.dao.GameCollectionDao;
import ru.mochalin.laba6.dao.GameDao;
import ru.mochalin.laba6.models.Collections;
import ru.mochalin.laba6.models.Game;
import ru.mochalin.laba6.models.GameCollection;

import java.io.IOException;

public class CardController {
    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private Button addToCollectionBT;

    @FXML
    private ImageView imgView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label summaryLabel;

    @Setter
    private boolean isInCollection = false;
    @Setter
    private Collections collectionForGame;
    @Setter
    private GameInCollectionController gameInCollectionController;
    @Setter
    private MainController mainController;

    private Collections collection;
    private CollectionDao collectionDao;
    private Game game;
    private GameDao gameDao;

    public void setGame(Game game) {
        this.game = game;
        imgView.setImage(new Image(game.getPhoto()));
        nameLabel.setText(game.getName());
        summaryLabel.setText(game.getSummary());
        if (isInCollection) {
            edit.visibleProperty().set(false);
            addToCollectionBT.visibleProperty().set(false);
        }
    }
    public void setCollection(Collections collection) {
        this.collection = collection;
        try {
            imgView.setImage(new Image(collection.getPhoto()));
        } catch (Exception e) {
            imgView.setImage(new Image("file:/Users/nikolaymochalin/Pictures/26b250a738ea4abc7a5af4d42ad93af0.jpg"));
        }
        nameLabel.setText(collection.getName());
        summaryLabel.setText(collection.getSummary());
        addToCollectionBT.setText("open collection");
    }

    public void onDelete(ActionEvent actionEvent) {
        if (isInCollection) {
            GameCollectionDao gameCollectionDao = new GameCollectionDao();
            gameCollectionDao.delete(game, collectionForGame);
            gameInCollectionController.updateList();
        } else {
            if (game != null){
                gameDao = new GameDao();
                gameDao.deleteById(game.getId());
                mainController.updateList();
            } else {
                collectionDao = new CollectionDao();
                collectionDao.deleteById(collection.getId());
                mainController.updateList();
            }
        }
    }

    public void onEdit(ActionEvent actionEvent) {
        if (game != null){
            gameDao = new GameDao();
            try {
                showDialog(game);
                gameDao.update(game);
                mainController.updateList();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            collectionDao = new CollectionDao();
            try {
                showDialog(collection);
                collectionDao.update(collection);
                mainController.updateList();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void onAddToCollection(ActionEvent actionEvent) {
        if (game != null){
            try {
                showCollectionList(game);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                showGameInCollection(collection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showCollectionList(Game game) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("/ru/mochalin/laba6/fxmls/add-to-collection-view.fxml"));
        Parent root = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Информация о игре");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(MainApplication.getMainStage());
        Scene scene = new Scene(root);
        addStage.setScene(scene);
        AddToCollectionController controller = loader.getController();
        controller.setDialogStage(addStage);
        controller.setGame(game);
        addStage.showAndWait();
    }

    private void showGameInCollection(Collections collection) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("/ru/mochalin/laba6/fxmls/game-in-collection-view.fxml"));
        Parent root = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Игры в коллекции");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(MainApplication.getMainStage());
        Scene scene = new Scene(root);
        addStage.setScene(scene);
        GameInCollectionController controller = loader.getController();
        controller.setDialogStage(addStage);
        controller.setCollection(collection);
        addStage.showAndWait();
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
}
