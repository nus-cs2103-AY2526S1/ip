package tsundere;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Tsundere tsundere = new Tsundere();
    private Image tsundereImage = new Image(this.getClass().getResourceAsStream("/images/archer.jpg"));


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Tsundere");
            stage.getIcons().add(tsundereImage);
            fxmlLoader.<MainWindow>getController().setTsundere(tsundere); // inject the Tsundere instance
            stage.show();
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
