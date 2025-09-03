package beebong;

import java.io.IOException;

import beebong.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the main application for my chatbot's UI.
 */
public class Main extends Application {
    private final BeeBong beeBong = new BeeBong();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            // Set window title
            stage.setTitle("BeeBong");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setBot(beeBong);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
