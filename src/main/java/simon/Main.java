package simon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * JavaFX application entry point for the Simon chatbot GUI.
 * A <code>Main</code> object sets up and displays the graphical user interface.
 */
public class Main extends Application {

    private final Simon simon = new Simon("./data/simon.txt");

    /**
     * Starts the JavaFX application by loading the FXML layout and setting up the stage.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Simon");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            // stage.setMaxWidth(417);
            fxmlLoader.<MainWindow>getController().setSimon(simon);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
