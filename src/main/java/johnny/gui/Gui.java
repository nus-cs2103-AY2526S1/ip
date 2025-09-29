package johnny.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import johnny.bot.Johnny;
import johnny.guielements.MainWindow;

public class Gui extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            MainWindow windowController = fxmlLoader.<MainWindow>getController();

            // Create a new johnny instance with a callback to MainWindow error dialog
            Johnny johnny = new Johnny("data/tasks.txt", msg -> {
                Platform.runLater(() -> windowController.showError(msg));
            });
            windowController.setJohnny(johnny); // inject the Johnny instance
            fxmlLoader.<MainWindow>getController().greeting(); // Show dialog box with greeting
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/johnny.png")));
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Johnny");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
