package sora.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sora.Sora;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Sora sora = new Sora("./data/sora.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Sora");
            scene.getStylesheets().add(Main.class.getResource("/view/style.css").toExternalForm());
            fxmlLoader.<MainWindow> getController().setSora(sora); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



