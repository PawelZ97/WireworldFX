package Wires;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGui extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("WireController.fxml"));
        Scene scene = new  Scene(root, 600, 425);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Wireworld Java");
        //primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream( "brick-icon.png" )));
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(425);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
}
