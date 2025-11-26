package aries.gui;

import java.io.IOException;

import aries.Aries;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class GuiMain extends Application {

    private Aries aries = new Aries();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiMain.class.getResource("/view/GuiMainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Aries - Your Personal Task Manager");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<GuiMainWindow>getController().setAries(aries); // inject the Aries instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
