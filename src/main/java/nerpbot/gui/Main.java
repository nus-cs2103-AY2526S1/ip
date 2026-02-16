package nerpbot.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nerpbot.NerpBot;

/**
 * A GUI for NerpBot using FXML.
 */
public class Main extends Application {

    private static final String FILE_PATH = "data/nerpbot.txt";
    private final NerpBot nerpBot = new NerpBot(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("NerpBot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNerpBot(nerpBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
