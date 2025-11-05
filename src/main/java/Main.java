import java.io.IOException;

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
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setMinHeight(600);
            stage.setMinWidth(817);

            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/darkblood.jpg")));
            stage.setTitle("Chat with Sunoo!");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
