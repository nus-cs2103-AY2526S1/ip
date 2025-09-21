package merlie.merliefxml;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import merlie.Merlie;

/**
 * A GUI for Merlie using FXML.
 */
public class Main extends Application {
    private Merlie merlie = new Merlie("./data/taskList.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Merlie the Merlion Chat");
            fxmlLoader.<MainWindow>getController().setMerlie(merlie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
