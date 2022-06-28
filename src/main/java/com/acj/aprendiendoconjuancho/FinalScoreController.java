package com.acj.aprendiendoconjuancho;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class FinalScoreController {
    private final Player player = Player.getInstance();
    private final Score score = Score.getInstance(Difficulty.LOW);
    @FXML
    private Label nameField;

    @FXML
    private Label overallPoints;

    @FXML
    public void initialize() throws FileNotFoundException {
        buildScore();
    }

    @FXML
    private void buildScore() {
        nameField.setText(player.getName());
        overallPoints.setText(String.valueOf(score.getOverallScore()));
    }

    @FXML
    private void playAgain(Event event) throws IOException {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("categories.fxml")));
            Scene tableViewScene = new Scene(tableViewParent);

            window.setScene(tableViewScene);
            window.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }
}