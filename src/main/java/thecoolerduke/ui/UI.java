package thecoolerduke.ui;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import thecoolerduke.main.TheCoolerDuke;


/**
 * The main application handling the display and bot processing.
 */
public class UI extends Application {
    private final TheCoolerDuke bot = new TheCoolerDuke();
    private final Image iconImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Bot.png")));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(UI.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBot(bot); // inject the Bot instance

            //Sets app title
            stage.setTitle("TheCoolerDuke");

            //Sets app icon
            stage.getIcons().add(iconImage);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
