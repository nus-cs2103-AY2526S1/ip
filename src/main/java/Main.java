import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tension.Tension;

/**
 * A GUI for Tension using FXML.
 */
public class Main extends Application {

    private Tension tension;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            try {
                tension = new Tension("data.txt");
            } catch (Exception e) {
                MainWindow controller = fxmlLoader.getController();
                controller.displayReadError("Couldn’t load data.txt. Please check the file and try again.", e);
            }
            fxmlLoader.<MainWindow>getController().setTension(tension); // inject the tension instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
