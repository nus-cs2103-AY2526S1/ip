package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Penny using FXML.
 */
public class Main extends Application {

    private Penny penny = new Penny();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // CHANGE: Load CSS stylesheet for enhanced styling
            scene.getStylesheets().add(getClass().getResource("/styles/chatbot.css").toExternalForm());

            stage.setScene(scene);
            stage.setTitle("Penny Chatbot"); // CHANGE: Add window title

            // inject the Penny instance
            fxmlLoader.<MainWindow>getController().setPenny(penny);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
