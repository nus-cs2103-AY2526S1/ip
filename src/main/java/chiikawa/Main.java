package chiikawa;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Chiikawa using FXML.
 */
public class Main extends Application {

    private Chiikawa chiikawa = new Chiikawa("data/list.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Chiikawa");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChiikawa(chiikawa);  // inject the Chiikawa instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
