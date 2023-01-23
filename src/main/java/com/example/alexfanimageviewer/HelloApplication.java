package com.example.alexfanimageviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MAIN.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 700);


        stage.setTitle("Image Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        FileChooser fileChooser = new FileChooser();

        launch(args);
    }
}