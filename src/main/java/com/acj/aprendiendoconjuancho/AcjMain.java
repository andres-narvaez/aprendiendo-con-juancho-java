package com.acj.aprendiendoconjuancho;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AcjMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Countdown countdown = new Countdown();
        countdown.start(6);
        System.out.println("Print countdown");
        System.out.println(countdown.getCount());

        FXMLLoader fxmlLoader = new FXMLLoader(AcjMain.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1333, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}