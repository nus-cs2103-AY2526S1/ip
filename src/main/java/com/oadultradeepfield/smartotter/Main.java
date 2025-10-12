package com.oadultradeepfield.smartotter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.oadultradeepfield.smartotter.component.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for SmartOtter using FXML.
 */
public class Main extends Application {
    private static final SmartOtter SMART_OTTER = new SmartOtter(SmartOtterConstant.SAVE_PATH);

    private static final int MIN_HEIGHT = 220;
    private static final int MIN_WIDTH = 417;
    private static final String APP_NAME = "SmartOtter";

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Starts the JavaFX application by loading the main window and setting up the stage.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(MIN_HEIGHT);
            stage.setMinWidth(MIN_WIDTH);
            stage.setTitle(APP_NAME);

            fxmlLoader.<MainWindow>getController().setSmartOtter(SMART_OTTER);

            stage.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load main window FXML", e);
        }
    }
}
