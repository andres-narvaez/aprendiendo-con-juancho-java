package com.acj.aprendiendoconjuancho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CategoryController {
    @FXML
    private TextField nameField;

    @FXML
    public void initialize() {

    }

    @FXML
    private void onSendData(ActionEvent event) {
        Category category = new Category();
        Category.setCategory(Categories.ANIMALS);
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("round.fxml"));
            RoundController controller = new RoundController();
            controller.setCategory(category.getCategory());
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }
}