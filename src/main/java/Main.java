import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nina.Nina;

/**
 * A GUI for Nina using FXML.
 */
public class Main extends Application {

    private final Nina nina = new Nina();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Nina");
            fxmlLoader.<MainWindow>getController().setNina(nina);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
