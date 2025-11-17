package pagrobot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pagrobot.ui.MainWindow;

/**
 * Represents a GUI for PagroBot using FXML.
 */
public class Main extends Application {

    private PagroBot pagroBot = new PagroBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("PagroBot");
            fxmlLoader.<MainWindow>getController().setBot(pagroBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
