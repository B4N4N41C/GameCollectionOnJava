package ru.mochalin.laba6.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.mochalin.laba6.models.Collections;
import ru.mochalin.laba6.models.Game;

public class CardController {
    @FXML
    private ImageView imgView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label summaryLabel;

    private Collections collections;
    private Game game;

    //TODO: add interfase and class card for useing one method(setCard)
    public void setGame(Game game) {
        this.game = game;

        nameLabel.setText(game.getName());
        summaryLabel.setText(game.getSummary());
    }
    public void setCollections(Collections collections) {
        this.collections = collections;
        imgView.setImage(new Image(collections.getPhoto()));
        nameLabel.setText(collections.getName());
        summaryLabel.setText(collections.getSummary());
    }

}
