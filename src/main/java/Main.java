import java.io.IOException;

import heimerdinger.Heimerdinger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Heimerdinger heimerdinger = new Heimerdinger("./data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(500);
            stage.setMinWidth(400);
            stage.setTitle("Heimerdinger");
            fxmlLoader.<MainWindow>getController().setHeimerdinger(heimerdinger); // inject the Heimerdinger instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
