package pickle.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pickle.chat.Pickle;

/**
 * A GUI for Pickle using FXML.
 */
public class Main extends Application {

    private Pickle pickle = new Pickle("data/Pickle.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Pickle");
            fxmlLoader.<MainWindow>getController().setPickle(pickle);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
