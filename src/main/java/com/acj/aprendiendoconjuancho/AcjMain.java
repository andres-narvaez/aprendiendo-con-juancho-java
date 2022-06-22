package com.acj.aprendiendoconjuancho;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AcjMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AcjMain.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1333, 720);
        stage.setTitle("Aprendiendo con juancho!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}