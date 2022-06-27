package com.acj.aprendiendoconjuancho;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoundController {
    EventBus eventBus = ServiceLocator.INSTANCE.getService(EventBus.class);
    private static final String basePath = new File("").getAbsolutePath();
    private Categories category;
    private final Player player = Player.getInstance();
    private Round round;
    private List<MatchRoundItem> matchRoundItemList;

    @FXML
    private Label nameField;

    @FXML
    private FlowPane topBox;

    @FXML
    private FlowPane bottomBox;

    @FXML
    private Label countdownLabel;

    @FXML
    private Button gameButton;

    @FXML
    private Label levelNumber;

    @FXML
    private Label levelPoints;

    @FXML
    private Label overallPoints;

    public RoundController() {
        eventBus.addEventHandler(GameEvent.START_COUNTDOWN, event -> {
            clearBoxes();
            buildRound(round.getCurrentLevel());
        });
        eventBus.addEventHandler(GameEvent.UPDATE_COUNTDOWN, event -> {
            updateCountdown(event.getCount());
            updateGameButton(GameStatus.PLAYING);
        });
        eventBus.addEventHandler(GameEvent.END_COUNTDOWN, event -> {
            updateGameButton(GameStatus.HOLD);
            clearMatchRoundItems();
            calculateScore();
            updateLevelStats();
        });
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        round = new Round(this.category, Difficulty.LOW, this.player);
        nameField.setText(player.getName());
    }

    private void buildRound(Levels level) {
        switch (level) {
            case MATCH -> buildMatchRound();
            case SORT -> buildSortRound();
            case LISTEN -> buildListenRound();
        }
    }

    @FXML
    private void clearBoxes() {
        Platform.runLater(
                () -> {
                    if(matchRoundItemList != null) {
                        for (MatchRoundItem item: matchRoundItemList
                             ) {
                            item.removeDraggable();
                            topBox.getChildren().remove(item.getWordNode());
                            bottomBox.getChildren().remove(item.getImageTarget());
                        }
                    }
                }
        );
    }

    @FXML
    private void clearMatchRoundItems() {
        Platform.runLater(
                () -> {
                    if(matchRoundItemList != null) {
                        for (MatchRoundItem item: matchRoundItemList
                        ) {
                            item.removeDraggable();
                        }
                    }
                }
        );
    }

    @FXML
    private void buildMatchRound() {
        Platform.runLater(
                () -> {
                    Level matchLevel = this.round.getLevel(Levels.MATCH);
                    WordDTO[] words = matchLevel.getWords();
                    matchRoundItemList = new ArrayList<>();

                    for (WordDTO word : words) {
                        Label wordLabel = new Label();
                        wordLabel.setText(word.getValue());
                        wordLabel.getStyleClass().add("match-word");
                        Circle circle = new Circle(80, 80, 80, Color.WHITE);
                        Image im = new Image("file:" + basePath + word.getImagePath());
                        circle.setFill(new ImagePattern(im));
                        circle.setStroke(Color.RED);
                        circle.getStyleClass().add("match-circle-image");
                        MatchRoundItem item = new MatchRoundItem(wordLabel, circle);
                        item.makeDraggable();
                        matchRoundItemList.add(item);
                        topBox.getChildren().add(wordLabel);
                        bottomBox.getChildren().add(circle);
                    }

                    topBox.toFront();
                }
        );

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

    private void calculateScore() {
        Levels level = this.round.getCurrentLevel();
        switch (level) {
            case MATCH -> {
                int rightAnswers = MatchRound.calculateAssertions(matchRoundItemList);
                this.round.getScore().addScore(level, rightAnswers);
                resolveMatchScore(this.round.getScore().getLevelScore(level));
            }
            default -> {
            }
        }
    }

    @FXML
    private void resolveMatchScore(int Score) {
        Platform.runLater(
                () -> {
                    if(matchRoundItemList != null) {
                        for (MatchRoundItem item: matchRoundItemList
                        ) {
                            Circle circle = (Circle) item.getImageTarget();
                            if(item.getOnTarget()) circle.setStroke(Color.GREENYELLOW);
                        }
                    }
                }
        );
    }

    @FXML
    private void updateLevelStats() {
        Platform.runLater(
                () -> {
                    Levels level = this.round.getCurrentLevel();
                    String levelText = switch (level) {
                        case MATCH -> "1";
                        case SORT -> "2";
                        case LISTEN -> "3";
                    };
                    levelNumber.setText(levelText);
                    levelPoints.setText(String.valueOf(this.round.getScore().getLevelScore(level)));
                    overallPoints.setText(String.valueOf(this.round.getScore().getOverallScore()));
                }
        );
    }
}