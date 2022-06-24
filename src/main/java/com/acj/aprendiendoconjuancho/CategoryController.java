package com.acj.aprendiendoconjuancho;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class CategoryController {
    @FXML
    private void onSendData(MouseEvent event) {
        ImageView image = (ImageView) event.getSource();
        Category category = new Category();
        Category.setCategory(Categories.valueOf(image.getAccessibleText()));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("round.fxml"));
            RoundController controller = new RoundController();
            controller.setCategory(category.getCategory());
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