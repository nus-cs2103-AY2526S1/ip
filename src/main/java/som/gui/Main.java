package som.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import som.Som;

/**
 * A GUI for Som using FXML.
 */
public class Main extends Application {

    private Som som = new Som();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Som – Your Task Assistant"); // Appears in OS window bar
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setSom(som); // inject the Som instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
