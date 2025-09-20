package app;

import java.io.IOException;

import airy.Airy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Airy airy = new Airy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Airy");

            // Get controller
            MainWindow controller = fxmlLoader.getController();

            // Inject Airy instance
            controller.setAiry(airy);

            // Show the welcome message
            controller.displayWelcome(airy.getWelcomeUi());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
