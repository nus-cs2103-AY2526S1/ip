package jone;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Jone jone = new Jone("data/jone.txt");

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);

        stage.setMinHeight(220);
        stage.setMinWidth(417);

        MainWindow controller = fxmlLoader.getController();
        controller.setJone(new Jone("data/jone.txt")); // use your existing storage

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
