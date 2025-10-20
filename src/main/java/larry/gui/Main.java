package larry.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import larry.LarryCore;

public class Main extends Application {
    private final LarryCore larry = new LarryCore();

    /** JavaFX application entry that loads the FXML UI and injects LarryCore. */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = loader.load();

            // IMPORTANT: inject logic into controller
            MainWindow controller = loader.getController();
            controller.setLarry(larry);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Larry");
            stage.setMinWidth(417);
            stage.setMinHeight(220);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
