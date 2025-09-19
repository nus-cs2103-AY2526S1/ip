package bot;

import java.io.IOException;

import bot.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Lovely using FXML.
 */
public class Main extends Application {

    private final Bot bot = new Bot("Lovely", "data/taskData.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Lovely");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBot(bot);  // inject the bot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
