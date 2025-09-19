package LunarBot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Lunarbot using FXML.
 */
public class Main extends Application {

    private LunarBot lunar = new LunarBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/LunarBot/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setScene(scene);
            stage.setTitle("LunarBot");
            fxmlLoader.<MainWindow>getController().setLunar(lunar);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
