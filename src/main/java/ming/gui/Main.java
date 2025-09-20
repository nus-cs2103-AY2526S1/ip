package ming.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ming.app.Ming;

/**
 * A GUI for Ming using FXML.
 */
public class Main extends Application {
    private Ming ming = new Ming("data/Ming.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/css/dialog-box.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMing(ming); // inject the Ming instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
