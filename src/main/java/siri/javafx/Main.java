package siri.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import siri.Siri;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Siri siri = new Siri("./data/data.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            // stage.setMaxWidth(417); // Add this if you didn't automatically resize elements
            fxmlLoader.<MainWindow>getController().setSiri(siri);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

