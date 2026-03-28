package vicky.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import vicky.controls.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Vicky vicky = new Vicky();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            MainWindow controller = fxmlLoader.getController();
            controller.setVicky(vicky); // inject the Vicky instance

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}