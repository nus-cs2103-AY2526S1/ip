package jobe.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jobe.Jobe;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    
    private Jobe jobe = new Jobe();
    
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            
            scene.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());
            
            MainWindow controller = fxmlLoader.getController();
            controller.setJobe(jobe);
            controller.showStartupMessages();
            
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
