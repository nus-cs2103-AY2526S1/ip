package george;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for George using FXML.
 */
public class Main extends Application {

    private George george = new George();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setGeorge(george);
            controller.setStage(stage); // Pass the stage reference

            stage.setTitle("George"); // Optional: set a title for the window
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
