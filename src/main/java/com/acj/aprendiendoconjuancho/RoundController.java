package com.acj.aprendiendoconjuancho;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class RoundController {
    EventBus eventBus = ServiceLocator.INSTANCE.getService(EventBus.class);

    private Categories category;
    private final Player player = Player.getInstance();
    private Round round;

    @FXML
    private Label nameField;

    @FXML
    private VBox roundVBox;

    @FXML
    private Label countdownLabel;

    @FXML
    private Button gameButton;

    public RoundController() {
        eventBus.addEventHandler(GameEvent.UPDATE_COUNTDOWN, event -> {
            updateCountdown(event.getCount());
            updateGameButton(GameStatus.PLAYING);
        });
        eventBus.addEventHandler(GameEvent.END_COUNTDOWN, event -> {
            updateGameButton(GameStatus.HOLD);
        });
    }

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
            Circle circle = new Circle(50,50,50, Color.RED);
            makeDraggable(wordLabel, circle);
            roundVBox.getChildren().add(circle);
            roundVBox.getChildren().add(wordLabel);
        }
    }

    private double startX;
    private double startY;

    private void makeDraggable(Label node, Node target) {
        node.setOnMousePressed(e -> {
            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();
        });

        node.setOnMouseDragged(e -> {
            node.setTranslateX(e.getSceneX() -startX);
            node.setTranslateY(e.getSceneY() -startY);

        });

        node.setOnMouseReleased(e -> {
            System.out.println("setOnMouseReleased");
            System.out.println("word :" + e.getSceneX() + " " + e.getSceneY());
            Point2D word = node.localToScene(0.0,0.0);
            System.out.println("word point 2D :" + word.getX() + " " + word.getY());
            Point2D circle = target.localToScene(0.0, 0.0);
            System.out.println("circle :" + circle.getX() + " " + circle.getY());
        });
    }

    @FXML
    private void buildSortRound() {

    }

    @FXML
    private void buildListenRound() {

    }

    @FXML
    private void onClickStartRound(ActionEvent event) {
        if(gameButton.getText().equals("Empezar") || gameButton.getText().equals("Reintentar")) {
            Level matchLevel = this.round.getLevel(this.round.getCurrentLevel());
            Countdown countdown = matchLevel.getCountdown();
            countdown.start(this.round.getRules().getStartTime());
            eventBus.fireEvent(new GameEvent(GameEvent.START_COUNTDOWN));
        } else if(gameButton.getText().equals("Terminar")) {
            GameEvent endEvent = new GameEvent(GameEvent.END_COUNTDOWN);
            eventBus.fireEvent(endEvent);
            updateCountdown("00:00");
        }
    }

    @FXML
    private void updateCountdown(String count) {
        Platform.runLater(
                () -> countdownLabel.setText(count)
        );
    }

    @FXML
    private void updateGameButton(GameStatus status) {
        String text = switch (status) {
            case READY -> "Empezar";
            case PLAYING -> "Terminar";
            case HOLD -> "Reintentar";
            case FINISHED -> "Salir";
        };

        Platform.runLater(
                () -> gameButton.setText(text)
        );
    }
}