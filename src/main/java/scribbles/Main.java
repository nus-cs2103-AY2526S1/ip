package scribbles;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import scribbles.ui.MainWindow;

/**
 * A GUI for Scribbles using FXML.
 */
public class Main extends Application {

    private static Stage stage;
    private final Scribbles scribbles = new Scribbles();

    @Override
    public void start(Stage stage) {
        try {
            Main.stage = stage;
            stage.setTitle("Scribbles :)");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setScribbles(scribbles);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exits the application
     */
    public static void exit() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> Main.stage.close());
        delay.play();
    }
}
