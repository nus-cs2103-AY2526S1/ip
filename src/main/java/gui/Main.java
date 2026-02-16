package gui;

import javafx.scene.paint.Color;
import nicholas.Nicholas;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application{

    private Nicholas nic = new Nicholas();
    private static final String TITLE = "Nicholas";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap, Color.LIGHTBLUE);
            stage.setScene(scene);
            stage.setTitle(TITLE);
            fxmlLoader.<MainWindow>getController().setNicholas(nic);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
