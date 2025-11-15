package nami.ui;

import nami.NamiGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("nami");
            // Inject your logic
            MainWindow controller = fxmlLoader.getController();
            controller.setNami(new NamiGUI());
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
