package com.acj.aprendiendoconjuancho;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class RoundController {
    private Categories category;
    private final Player player = Player.getInstance();

    @FXML
    private Label nameField;

    public void setCategory(Categories category) {
        this.category = category;
    }

    @FXML
    public void initialize() {
        nameField.setText(player.getName());
    }

    @FXML
    private void receiveData(MouseEvent event) {
        // Step 1
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        // Step 2
        Category u = (Category) stage.getUserData();
        // Step 3
        Categories name = u.getCategory();

        System.out.println(name);
    }

}