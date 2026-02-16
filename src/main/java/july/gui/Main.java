package july.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import july.July;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String SAVE_FILE_PATH = "data/savefile.txt";

    private July july = new July(SAVE_FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            // Add theme CSS
            scene.getStylesheets().add(Main.class.getResource("/view/theme.css").toExternalForm());
            stage.setTitle("July");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJuly(july);  // inject the July instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
