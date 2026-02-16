package geni;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Geni using FXML.
 */
public class Main extends Application {

    private Geni geni;

    @Override
    public void start(Stage stage) {
        try {
            this.geni = new Geni("./geni.txt");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Attach stylesheet here
            String cssPath = "/styles/styles.css";
            var cssUrl = Main.class.getResource(cssPath);
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
                System.out.println("Loaded stylesheet: " + cssUrl.toExternalForm());
            } else {
                System.err.println("ERROR: stylesheet not found at " + cssPath);
            }

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(geni);
            stage.setTitle("Geni - Your Personal Assistant");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

