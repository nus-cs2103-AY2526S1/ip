package izayoi.ui;

import java.io.IOException;

import izayoi.Izayoi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class to launch I
 */
public class IzayoiApplication extends Application {
    private Izayoi izayoi;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(IzayoiApplication.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow mw = fxmlLoader.<MainWindow>getController();
            this.izayoi = new Izayoi(mw);
            mw.setIzayoi(izayoi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
