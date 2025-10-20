package uy;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    /**
     * A GUI for Duke using FXML.
     * Simple launcher that delegates to Uy.main. Kept for compatibility with
     * Gradle/IDE run configurations.
     */
    private Uy uy = new Uy();

    @Override
    public void start(Stage stage) {
        /**
         * Boot the JavaFX UI by loading the FXML layout and injecting
         * the application controller with the Uy instance.
         */
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setUy(uy);  // inject the Uy instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
