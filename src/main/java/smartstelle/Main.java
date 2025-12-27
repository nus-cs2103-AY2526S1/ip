package smartstelle;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private SmartStelle smartStelle;
    private static final double MIN_HEIGHT = 220;
    private static final double MIN_WIDTH = 417;
    private static final double MAX_WIDTH = 417;

    @Override
    public void start(Stage stage) {
        try {
            this.smartStelle = new SmartStelle("data/tasks.txt");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setTitle("SmartStelle");

            stage.setMinHeight(MIN_HEIGHT);
            stage.setMinWidth(MIN_WIDTH);
            stage.setMaxWidth(MAX_WIDTH);

            fxmlLoader.<MainWindow>getController().setSmartStelle(smartStelle);  // inject the SmartStelle instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
