package clam;

import com.formdev.flatlaf.FlatLightLaf;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;


public class Main extends Application {

    static {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
    }

    private final Clam c = new Clam("./save.txt");

    /**
     * Sets up the stage and runs the JavaFX application.
     *
     * @param stage the stage for the application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setClam(c);  // inject the Clam instance
            fxmlLoader.<MainWindow>getController().startup();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
