package ozil.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Ozil using FXML.
 */
public class Main extends Application {

    private Ozil ozil = new Ozil();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Ozil");
            fxmlLoader.<MainWindow>getController().setOzil(ozil);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
