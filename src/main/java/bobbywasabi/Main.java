package bobbywasabi;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The entry point for the BobbyWasabi JavaFX application.
 * <p>
 * This class is responsible for launching the JavaFX UI and initializing the main application logic.
 * It sets up the primary stage, loads the FXML layout for the main window, and injects
 * the BobbyWasabi instance into the controller.
 */
public class Main extends Application {
    private BobbyWasabi bobbywasabi = new BobbyWasabi();

    /**
     * Starts the JavaFX application.
     * <p>
     * Loads the main window from FXML, sets the scene on the provided stage,
     * and links the BobbyWasabi instance to the controller.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("BobbyWasabi");
            fxmlLoader.<MainWindow>getController().setBobbyWasabi(bobbywasabi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
