package guibot.gui;

import java.io.IOException;

import guibot.Guibot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A GUI for Guibot using FXML.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            MainWindow mw = fxmlLoader.getController();
            stage.setScene(scene);
            stage.setTitle("Guibot");
            Image icon = new Image(getClass().getResourceAsStream("/images/icon.png"));
            stage.getIcons().add(icon);
            mw.setGuibot(new Guibot()); // inject the Guibot instance
            stage.show();
            mw.prefWidthProperty().bind(stage.getScene().widthProperty());
            mw.prefHeightProperty().bind(stage.getScene().heightProperty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
