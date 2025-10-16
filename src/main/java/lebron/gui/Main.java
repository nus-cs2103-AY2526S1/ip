package lebron.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lebron.main.LeBron;

/**
 * A GUI for LeBron using FXML.
 */
public class Main extends Application {

    private LeBron lebron = new LeBron();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("LeBron");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLeBron(lebron); // inject the LeBron instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
