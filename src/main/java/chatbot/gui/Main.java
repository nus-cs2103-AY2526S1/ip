package chatbot.gui;

import java.io.IOException;

import chatbot.ui.B33pbop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final B33pbop b33pbop = new B33pbop();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "MainWindow.fxml failed to load or returned null";

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setTitle("B33PBOP");

            stage.setMinHeight(250);
            stage.setMinWidth(417);

            MainWindow controller = fxmlLoader.getController();
            assert controller != null : "FXML controller is null";
            controller.setDuke(b33pbop);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
