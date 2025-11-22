package javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import watermelon.Watermelon;

/**
 * A GUI for Watermelon using FXML.
 */
public class Main extends Application {

    private Watermelon watermelon = new Watermelon();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Watermelon");
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/watermelon.png")));
            fxmlLoader.<MainWindow>getController().setWatermelon(watermelon); // inject the Watermelon instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
