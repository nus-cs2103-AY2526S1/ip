package charli;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX entry point for Charli.
 * <p>
 * Loads the GUI layout and initialising the application.
 */
public class Main extends Application {

    private final Charli charli = new Charli();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            scene.getStylesheets().add(Main.class.getResource("/view/styles.css").toExternalForm());
            stage.setTitle("CharliXCX");
            stage.setScene(scene);
            stage.show();

            MainWindow controller = fxmlLoader.getController();
            controller.setCharli(charli);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}