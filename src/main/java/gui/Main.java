package gui;

import bobby.Bobby;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

//gui code was from the GUI tutorial on https://se-education.org/guides/tutorials/javaFx.html

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Bobby bobby = new Bobby();
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setScene(scene);
            stage.show();

            fxmlLoader.<MainWindow>getController().setBobby(bobby);  // inject the Duke instance
            fxmlLoader.<MainWindow>getController().setParentStage(stage);
            fxmlLoader.<MainWindow>getController().startText();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
