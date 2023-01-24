package com.example.alexfanimageviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MAIN.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 737);
        Image image = new Image("https://cdn-icons-png.flaticon.com/512/564/564232.png");
        stage.setTitle("Image Viewer");
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        FileChooser fileChooser = new FileChooser();

        launch(args);
    }
}