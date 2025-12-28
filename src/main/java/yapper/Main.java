package yapper;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Yapper yapper = new Yapper();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Yapper Chatbot");
            fxmlLoader.<MainWindow>getController().setYapper(yapper); // inject the Duke instance
            fxmlLoader.<MainWindow>getController().showGreeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
