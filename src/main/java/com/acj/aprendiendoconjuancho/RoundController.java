package com.acj.aprendiendoconjuancho;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class RoundController {
    private Categories category;
    private final Player player = Player.getInstance();
    private Round round;

    @FXML
    private Label nameField;

    @FXML
    private VBox roundVBox;

    public void setCategory(Categories category) {
        this.category = category;
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        round = new Round(this.category, Difficulty.LOW, this.player);
        nameField.setText(player.getName());
        buildRound(round.getCurrentLevel());
    }

    private void buildRound(Levels level) {
        switch (level) {
            case MATCH -> buildMatchRound();
            case SORT -> buildSortRound();
            case LISTEN -> buildListenRound();
        }
    }

    @FXML
    private void buildMatchRound() {
        Level matchLevel = this.round.getLevel(Levels.MATCH);
        WordDTO[] words = matchLevel.getWords();

        for(WordDTO word : words) {
            Label wordLabel = new Label();
            wordLabel.setText(word.getValue());
            makeDraggable(wordLabel);
            Circle circle = new Circle(50,50,50, Color.RED);
            targetImage(circle);
            roundVBox.getChildren().add(circle);
            roundVBox.getChildren().add(wordLabel);
        }
    }

    private double startX;
    private double startY;

    private void makeDraggable(Node node) {
        node.setOnMousePressed(e -> {
            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();
        });

        node.setOnMouseDragged(e -> {
            node.setTranslateX(e.getSceneX() -startX);
            node.setTranslateY(e.getSceneY() -startY);
        });

        //node.setOnDragOver();
    }

    private void targetImage(Node node) {
        node.setOnDragDetected((MouseEvent e) -> {
            System.out.println(e);
        });
    }

    @FXML
    private void buildSortRound() {

    }

    @FXML
    private void buildListenRound() {

    }

}