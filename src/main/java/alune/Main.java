package alune;

import java.io.IOException;

import alune.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private final Alune alune = new Alune("./data/record.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("ALUNE ˚ʚ♡ɞ˚");
            stage.setMinHeight(650.0);
            stage.setMinWidth(400.0);
            fxmlLoader.<MainWindow>getController().setAlune(alune);
            stage.show();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                alune.shutdown();
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
