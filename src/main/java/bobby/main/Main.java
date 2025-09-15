package bobby.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bobby using FXML.
 */
public class Main extends Application {

    private Bobby bobby = new Bobby();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBobby(bobby); // inject the Bobby instance
            stage.setTitle("Bobby");
            stage.show();

            stage.setOnCloseRequest(event -> {
                bobby.save();
                Platform.exit();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
