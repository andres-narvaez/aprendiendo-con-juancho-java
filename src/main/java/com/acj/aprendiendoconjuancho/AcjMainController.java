package com.acj.aprendiendoconjuancho;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AcjMainController {
    @FXML
    private TextField nameField;

    @FXML
    public void initialize() {
        nameFieldListener();
    }

    @FXML
    protected void onStartButtonClick() {
        String playerName = nameField.getText();
        Player.getInstance(playerName);
        System.out.println(nameField.getText());
    }

    private void nameFieldListener() {
        nameField.setOnMouseClicked(e -> nameField.clear());
    }
}