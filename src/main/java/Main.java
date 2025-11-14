import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import monarch.Monarch;
import monarch.exceptions.MonException;

/**
 * A GUI for Monarch using FXML.
 */
public class Main extends Application {
    private static final String SAVE_PATH = "./save.txt";
    private static Stage stage;
    private Monarch monarch = new Monarch(SAVE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            Main.stage = stage;
            stage.setTitle("Monarch - The one who rules them all!");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setMonarch(monarch);
            stage.show();
        } catch (MonException e) {
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exit() {
        Main.stage.close();
    }
}
