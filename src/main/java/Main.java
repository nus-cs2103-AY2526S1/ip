import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dawae using FXML.
 */
public class Main extends Application {
    
    private Dawae dawae = new Dawae("DawaeTasks.txt");
    
    /**
     * {@inheritDoc}
     * Instantiates Dawae and loads the Dawae's GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDawae(dawae);  // inject the Duke instance
            stage.setTitle("dawaeeeee");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
