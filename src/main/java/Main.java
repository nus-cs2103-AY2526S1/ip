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
    //create Dukey with the filePath of a .txt file
    private Dukey dukey = new Dukey("Dukey.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Dukey");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(dukey); // inject the Dukey instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

