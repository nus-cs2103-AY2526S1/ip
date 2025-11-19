import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mochibot.MochiBot;

/**
 * A GUI for MochiBot using FXML.
 */
public class Main extends Application {

    private MochiBot mochibot = new MochiBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("MochiBot");
            Image image = new Image(Main.class.getResourceAsStream("/images/MochiBot.png"));
            stage.getIcons().add(image);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(620);
            stage.setMinWidth(530);
            // stage.setMaxWidth(417); // Add this if you didn't automatically resize elements
            fxmlLoader.<MainWindow>getController().setMochiBot(mochibot); // inject the mochibot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
