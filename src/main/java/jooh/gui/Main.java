package jooh.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Jooh");
            stage.setScene(scene);
            stage.setMinWidth(400);
            stage.setMinHeight(600);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}