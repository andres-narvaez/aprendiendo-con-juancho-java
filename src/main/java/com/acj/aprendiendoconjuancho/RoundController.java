package com.acj.aprendiendoconjuancho;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class RoundController {
    EventBus eventBus = ServiceLocator.INSTANCE.getService(EventBus.class);
    private static final String basePath = new File("").getAbsolutePath();
    private Categories category;
    private final Player player = Player.getInstance();
    private Round round;
    private List<MatchRoundItem> matchRoundItemList;
    private List<SortRoundItem> sortRoundItemList;
    private WordDTO sortRoundWord;
    private Circle sortRoundImage;
    private List<Rectangle> sortLetterContainers;
    private WordDTO listenRoundWord;

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

    @FXML
    private Button gameButtonNext;

    @FXML
    private Pane roundText;



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
            clearMatchRoundItemsDrag();
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
        updateRoundText(this.round.getCurrentLevel());
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
                    if(sortRoundItemList != null) {
                        bottomBox.getChildren().remove(sortRoundImage);
                        for (SortRoundItem item: sortRoundItemList
                             ) {
                            item.removeDraggable();
                            topBox.getChildren().remove(item.getLetterNode());
                            bottomBox.getChildren().remove(item.getRectangle());
                        }
                    }
                }
        );
    }

    @FXML
    private void clearMatchRoundItemsDrag() {
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
                        circle.setStrokeWidth(3);
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
        Platform.runLater(
                () -> {
                    Level matchLevel = this.round.getLevel(Levels.MATCH);
                    WordDTO[] words = matchLevel.getWords();
                    sortRoundWord = words[0];
                    List<String> lettersList = this.round.getGlossary().getShuffleWord(sortRoundWord.getValue());
                    sortRoundItemList = new ArrayList<>();
                    sortLetterContainers = new ArrayList<>();
                    sortRoundImage = new Circle(100, 100, 100, Color.WHITE);
                    Image im = new Image("file:" + basePath + sortRoundWord.getImagePath());
                    sortRoundImage.setFill(new ImagePattern(im));
                    sortRoundImage.setStroke(Color.LIGHTSKYBLUE);
                    sortRoundImage.setStrokeWidth(3);
                    sortRoundImage.getStyleClass().add("match-circle-image");
                    bottomBox.getChildren().add(sortRoundImage);

                    for (String letter:
                         lettersList) {
                        Label letterLabel = new Label(letter);
                        Rectangle rectangle = new Rectangle(60,80);
                        SortRoundItem sortLetter = new SortRoundItem(letterLabel, rectangle);
                        letterLabel.setPrefWidth(45);
                        letterLabel.setPrefHeight(45);
                        letterLabel.getStyleClass().add("sort-letter");
                        rectangle.setFill(Color.TRANSPARENT);
                        rectangle.setStroke(Color.LIGHTPINK);
                        rectangle.setStrokeWidth(3);
                        rectangle.getStyleClass().add("sort-letter-container");
                        sortLetter.makeDraggable();
                        sortRoundItemList.add(sortLetter);
                        sortLetterContainers.add(rectangle);
                        topBox.getChildren().add(letterLabel);
                        bottomBox.getChildren().add(rectangle);
                    }

                }
        );
    }

    @FXML
    private void buildListenRound() {
        Platform.runLater(() -> {
            Level matchLevel = this.round.getLevel(Levels.MATCH);
            WordDTO[] words = matchLevel.getWords();
            listenRoundWord = words[1];


        });
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
            showNextButton(true);
        }
    }

    @FXML
    private void showNextButton(Boolean show) {
        gameButtonNext.setVisible(show);
    }

    @FXML
    private void onClickNextRound(ActionEvent event) {
        Levels currentLevel = this.round.getCurrentLevel();
        Levels nextLevel = switch (currentLevel) {
            case MATCH -> Levels.SORT;
            case SORT -> Levels.LISTEN;
            default -> Levels.MATCH;
        };
        this.round.setCurrentLevel(nextLevel);
        clearBoxes();
        showNextButton(false);
        updateGameButton(GameStatus.READY);
        updateLevelStats();
        updateRoundText(nextLevel);
    }

    @FXML
    private void updateRoundText(Levels level) {
        String style = switch (level) {
            case MATCH -> "text-match";
            case SORT -> "text-sort";
            case LISTEN -> "text-listen";
        };

        roundText.getStyleClass().add(style);
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
            case SORT -> {
                int rightAnswers = SortRound.calculateAssertions(sortRoundItemList, sortRoundWord.getValue());
                this.round.getScore().addScore(level, rightAnswers);
                resolveSortScore(rightAnswers);
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
    private void resolveSortScore(int rightAnswers) {
        Platform.runLater(
                () -> {
                    if(sortRoundItemList != null) {
                        for (int i = 0; i < sortRoundItemList.size(); i++) {
                            Boolean isRight = sortRoundItemList.get(i).getCorrect();

                            if (isRight) {
                                sortLetterContainers.get(i).setStroke(Color.GREENYELLOW);
                            } else {
                                sortLetterContainers.get(i).setStroke(Color.RED);
                            }
                        }

                        if (rightAnswers == sortRoundItemList.size()) {
                            sortRoundImage.setStroke(Color.GREENYELLOW);
                        } else {
                            sortRoundImage.setStroke(Color.RED);
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

    @FXML
    private void getFinalScore(Event event) {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("finalScore.fxml"));
            FinalScoreController controller = new FinalScoreController();
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }
}