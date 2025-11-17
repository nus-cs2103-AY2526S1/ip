package gui;

import java.io.IOException;

import bara.Bara;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bara using FXML.
 */
public class GuiMain extends Application {

    private final Bara BARA = new Bara();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiMain.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBara(BARA);  // inject the Bara instance
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
