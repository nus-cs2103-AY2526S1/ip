package yang.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Entry point for the Yang JavaFX application.
 * <p>
 * This class loads the main window layout from FXML, sets up the controller,
 * and configures the primary stage.
 */
public class Main extends Application {
    private final yang.Yang yang = new yang.Yang();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxml = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxml.load();

            MainWindow controller = fxml.getController();
            controller.setYang(yang);

            stage.setTitle("Yang");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
