package sophia;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Sophia using FXML.
 */
public class Main extends Application {

    private Sophia sophia = new Sophia("./data/test_storage.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Sophia");
            Image icon = new Image(this.getClass().getResourceAsStream("/images/icon.png"));
            stage.getIcons().add(icon);
            stage.setMinHeight(650);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSophia(sophia);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
