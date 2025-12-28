package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tsunderechan.TsundereChan;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private TsundereChan tsundereChan = new TsundereChan("data/TsundereChan.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            stage.setTitle("TsundereChan");

            Image image = new Image("/images/Icon.jpg");
            stage.getIcons().add(image);

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTsundereChan(tsundereChan); // inject the TsundereChan instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
