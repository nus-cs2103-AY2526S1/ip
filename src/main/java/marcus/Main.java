package marcus;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Marcus using FXML.
 */
public class Main extends Application {

    private Marcus marcus = new Marcus();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Marcus");
            fxmlLoader.<MainWindow>getController().setMarcus(marcus);  // inject the Marcus instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
