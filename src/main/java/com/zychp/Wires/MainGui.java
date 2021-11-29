package com.zychp.Wires;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGui extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainGui.class.getResource("WireController.fxml"));
        Scene scene = new  Scene(fxmlLoader.load(), 600, 425);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Wireworld Java");
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(425);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
