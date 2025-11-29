package seedu.duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * JavaFX entry point for the Chatnius application.
 * Loads the main window and injects a {@link Chatnius} instance.
 */
public class Main extends Application {

    /** Chatnius instance shared with the GUI. */
    private final Chatnius chat = new Chatnius("data/duke.txt");

    @Override
    public void start(Stage stage) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            final BorderPane root = fxmlLoader.load();
            final Scene scene = new Scene(root);
            stage.setTitle("Chatnius");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setMinWidth(500);
            stage.setMinHeight(400);

            final MainWindow controller = fxmlLoader.getController();
            controller.setDuke(chat);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
