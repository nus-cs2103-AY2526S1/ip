package JohnGUI;

import java.io.IOException;

import JohnMain.John;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for John.John using FXML.
 */
public class Main extends Application {

    private final John john = new John();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("John - Task Assistant");
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setJohn(john);  // inject the John instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
