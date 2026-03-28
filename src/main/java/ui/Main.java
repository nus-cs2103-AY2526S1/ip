package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mark.Mark;

/**
 * A GUI for Mark using FXML.
 */
public class Main extends Application {

    private Mark mark = new Mark();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setScene(scene);
            stage.setMinHeight(620);
            stage.setMinWidth(417);
            stage.setTitle("Mark");

            fxmlLoader.<MainWindow>getController().setMark(mark); // inject the Mark instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

