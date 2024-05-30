package ru.mochalin.laba6.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Setter;
import ru.mochalin.laba6.MainApplication;
import ru.mochalin.laba6.models.Game;

import java.io.File;
/**
 * Данный контроллер является модальным окном которое вызывается при нажатии на кнопку
 * создания новой игры в классе MainController.
 */
public class GameController {

    private Game game;

    @Setter
    private Stage dialogStage;

    private String photo;

    @FXML
    private TextField markTF;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField summaryTF;

    @FXML
    void onAddImg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open image");

        FileChooser.ExtensionFilter filter1 =
                new FileChooser.ExtensionFilter("All image files", "*.png",
                        "*.jpg", "*.gif");
        FileChooser.ExtensionFilter filter2 =
                new FileChooser.ExtensionFilter("JPEG files", "*.jpg");
        FileChooser.ExtensionFilter filter3 =
                new FileChooser.ExtensionFilter("PNG image Files",
                        "*.png");
        FileChooser.ExtensionFilter filter4 =
                new FileChooser.ExtensionFilter("GIF image Files",
                        "*.gif");

        fileChooser.getExtensionFilters()
                .addAll(filter1, filter2, filter3, filter4);

        File file = fileChooser.showOpenDialog(MainApplication.getMainStage());

        if (file != null) {
            photo = file.toURI().toString();
        }
    }

    @FXML
    void onConfirm(ActionEvent event) {
        game.setName(nameTF.getText());
        game.setMark(Integer.parseInt(markTF.getText()));
        game.setSummary(summaryTF.getText());
        if (photo != null) {
            game.setPhoto(photo);
        }
        dialogStage.close();
    }

    public void setGame(Game game) {
        this.game = game;
        nameTF.setText(game.getName());
        markTF.setText(String.valueOf(game.getMark()));
        summaryTF.setText(game.getSummary());
    }
}
