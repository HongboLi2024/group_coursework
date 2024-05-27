package com.cx.Page;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Login.fxml"))));
        primaryStage.setTitle("Welcome to Children Bank");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
