package com.acj.aprendiendoconjuancho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AcjMainController {
    @FXML
    private TextField nameField;

    @FXML
    public void initialize() {
        nameFieldListener();
    }

    @FXML
    protected void onStartButtonClick(ActionEvent event) throws IOException {
        String playerName = nameField.getText();
        Player.getInstance(playerName);
        Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("categories.fxml")));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    private void nameFieldListener() {
        nameField.setOnMouseClicked(e -> nameField.clear());
    }
}