package khat;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Khat using FXML.
 */
public class Main extends Application {

    private Khat khat = new Khat("./data/KhatTasks.txt");
    private Image image = new Image("/images/lbxx.png");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Khat");
            stage.getIcons().add(image);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setKhat(khat); // inject the Khat instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
