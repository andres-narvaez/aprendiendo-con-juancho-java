package com.acj.aprendiendoconjuancho;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AcjMainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}