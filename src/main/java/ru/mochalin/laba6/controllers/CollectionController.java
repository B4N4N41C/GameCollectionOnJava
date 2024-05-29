package ru.mochalin.laba6.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.mochalin.laba6.MainApplication;
import ru.mochalin.laba6.models.Collections;
import ru.mochalin.laba6.models.Game;

import java.io.File;

public class CollectionController {
    private Collections collection;

    private Stage dialogStage;

    private String photo;

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
        collection.setName(nameTF.getText());
        collection.setSummary(summaryTF.getText());
        if (photo != null) {
            collection.setPhoto(photo);
        }
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCollection(Collections collection) {
        this.collection = collection;
        nameTF.setText(collection.getName());
        summaryTF.setText(collection.getSummary());
    }
}
