package pengu.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pengu.Pengu;

/**
 * A GUI for Pengu using FXML.
 */
public class Main extends Application {

    private Pengu pengu = new Pengu();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pengu");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPengu(pengu); // inject the Pengu instance
            fxmlLoader.<MainWindow>getController().start();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!pengu.isRunning()) {
            Platform.exit();
        }
    }
}
