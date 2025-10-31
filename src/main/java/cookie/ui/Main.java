package cookie.ui;

import java.io.IOException;

import cookie.Cookie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Enhanced GUI for Cookie using FXML with improved styling and responsiveness
 */
public class Main extends Application {

    private Cookie cookie = new Cookie("data/cookie.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            // Ensure dialog bubble styles are loaded
            scene.getStylesheets().add(Main.class.getResource("/css/dialog-box.css").toExternalForm());

            // Enhanced window settings
            stage.setScene(scene);
            stage.setTitle("Cookie - Task Manager");
            stage.setMinHeight(400);
            stage.setMinWidth(450);

            // Set preferred size for better initial appearance
            stage.setWidth(500);
            stage.setHeight(700);

            fxmlLoader.<MainWindow>getController().setCookie(cookie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
