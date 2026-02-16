package taro;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import taro.corecomp.Taro;
import taro.ui.MainWindow;

/**
 * The entry point for the Taro chatbot application.
 * Loads the FXML layout and initializes the main GUI window.
 */
public class Main extends Application {

    private final Taro taro = new Taro("data/taro.txt");

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);

        // inject Taro into MainWindow controller
        MainWindow controller = fxmlLoader.getController();
        controller.setTaro(taro);

        scene.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setTitle("Taro");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
