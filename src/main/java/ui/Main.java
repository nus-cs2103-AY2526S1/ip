package ui;

import java.io.IOException;
import java.util.Objects;

import app.GenieWeenie;
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

    private final GenieWeenie genieWeenie = new GenieWeenie("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("GenieWeenie Task Manager");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/genie.jpg")));
            stage.getIcons().add(icon);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPepe(genieWeenie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
