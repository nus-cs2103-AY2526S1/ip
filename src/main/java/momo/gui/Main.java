package momo.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import momo.Momo;

/**
 * A GUI for Momo using FXML.
 */
public class Main extends Application {

    private final Momo momo = new Momo();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Momo");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/MomoIcon.png")));
            fxmlLoader.<MainWindow>getController().setMomo(momo); // inject the Momo instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
