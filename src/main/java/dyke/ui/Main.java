package dyke.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dyke using FXML.
 */
public class Main extends Application {
    private Dyke dyke = new Dyke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("DYKE Task Manager");
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setDyke(dyke); // inject the Dyke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

