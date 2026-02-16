package mambo.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mambo.Mambo;

/**
 * A GUI for Mambo using FXML.
 *
 * @author kentalim2
 */
public class Main extends Application {

    private Mambo mambo = new Mambo();

    /**
     * Starts running the chatbot application
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(400);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMambo(mambo); // inject the Mambo instance
            stage.setTitle("Mambo");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exits the chatbot application.
     */
    public static void exitApplication() {
        Platform.exit();
        System.exit(0);
    }

}

