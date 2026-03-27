package pepe.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pepe.Pepe;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Pepe pepe = new Pepe("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            assert stage != null : "Stage should not be null";
            stage.setTitle("Pepe Chat");
            Image icon = new Image(getClass().getResourceAsStream("/images/pepe_angry.png"));
            assert icon != null : "Pepe icon image not found at /images/pepe_angry.png";
            stage.getIcons().add(icon);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPepe(pepe);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
