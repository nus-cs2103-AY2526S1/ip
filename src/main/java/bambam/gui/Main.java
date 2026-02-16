package bambam.gui;

import java.io.IOException;

import bambam.Bambam;
import bambam.BambamException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bambam using FXML.
 */
public class Main extends Application {

    private Bambam bambam;

    @Override
    public void start(Stage stage) {
        try {
            bambam = new Bambam(); // Initialize here, can handle exceptions
        } catch (IOException | BambamException e) {
            e.printStackTrace();
            return; // Stop app if Bambam fails to initialize
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("⋆.˚✮Bambam✮˚.⋆"); // sets title of gui
            fxmlLoader.<MainWindow>getController().setBambam(bambam);  // inject the Duke instance

            stage.setMinHeight(220);
            stage.setMinWidth(417);
            // stage.setMaxWidth(417); // Add this if you didn't automatically resize elements
            fxmlLoader.<MainWindow>getController().setBambam(bambam);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


