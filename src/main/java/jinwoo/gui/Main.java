package jinwoo.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jinwoo.Jinwoo;

/**
 * A GUI for Jinwoo using FXML.
 */
public class Main extends Application {

    private Jinwoo jinwoo = new Jinwoo("./data/save.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Jinwoo Chatbot");
            fxmlLoader.<MainWindow>getController().setJinwoo(jinwoo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
