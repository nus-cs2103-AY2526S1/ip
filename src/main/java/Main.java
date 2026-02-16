import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import talkgpt.Talkgpt;
import talkgpt.component.MainWindow;


/**
 * Main entry point for the TalkGPT JavaFX application.
 * Initializes the application and sets up the main window.
 */
public class Main extends Application {
    private Talkgpt talkgpt = new Talkgpt("data/data.txt");

    /**
     * Starts the JavaFX application and loads the main window.
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
            fxmlLoader.<MainWindow>getController().setTalkgpt(talkgpt); //inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



