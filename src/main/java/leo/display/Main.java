package leo.display;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import leo.Leo;

/**
 * The Main class extends the Application class in JavaFX.
 * This class is used to set up the nodes that are required to
 * render the GUI foe the application.
 */
public class Main extends Application {

    private Leo leo;

    /**
     * This method sets and loads the required components to display an interactive GUI.
     *
     * @param stage Window where the GUI will be displayed in.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            // inject the Leo instance
            this.leo = new Leo(".data/Leo.txt");
            fxmlLoader.<MainWindow>getController().setLeo(leo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

