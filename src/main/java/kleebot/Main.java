package kleebot;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kleebot.controller.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final String DEFAULT_FILE_PATH = "KleeData/taskFile";

    private KleeBot kleeBot = new KleeBot(DEFAULT_FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setTitle("KleeBot!! <3");
            Image kleeIcon = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/images/kleeIcon.jpg")));
            stage.getIcons().add(kleeIcon);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setKlee(kleeBot);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
