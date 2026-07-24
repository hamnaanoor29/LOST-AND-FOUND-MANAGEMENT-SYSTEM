package com.lostandfoundsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainSystem extends Application {

    // Store user information after signup
    public static String savedUsername = "";
    public static String savedPassword = "";
    public static String savedContact = "";

    @Override
    public void start(Stage stage) {

        Scene firstScene = FirstPage.getScene(stage);

        stage.setTitle("Lost & Found Management System");
        stage.setScene(firstScene);

        stage.setWidth(900);
        stage.setHeight(650);
        stage.setResizable(false);
        stage.centerOnScreen();

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
