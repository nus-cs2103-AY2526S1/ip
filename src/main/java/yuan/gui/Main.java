package yuan.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yuan.Yuan;

/**
 * A GUI for Yuan using FXML.
 */
public class Main extends Application {

    private Yuan yuan = new Yuan();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYuan(yuan); // inject the Yuan instance
            stage.setTitle("Yuan");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
