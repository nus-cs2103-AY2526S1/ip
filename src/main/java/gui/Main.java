package gui;

import hachi.Hachi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class Main extends Application {
    private static Stage primaryStage;

    // Default save directory under user home
    private static final Path BASE_DIR = Paths.get(System.getProperty("user.home"), "hachi");
    private static final String TASK_FILE = "task.properties";
    private static final String NOTE_FILE = "note.properties";

    private final Hachi hachi =
            new Hachi(BASE_DIR.resolve(TASK_FILE).toString(),
                    BASE_DIR.resolve(NOTE_FILE).toString());

    @Override
    public void start(Stage stage) {
        try {
            ensureSaveDirectory(); //ensure data can be saved

            primaryStage = stage; //set reference for main stage

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();

            //apply stylesheet to scene
            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("/view/dialog.css").toExternalForm()
            );

            // Inject Hachi into the controller
            MainWindow controller = fxmlLoader.getController();
            controller.setHachi(hachi);

            stage.setScene(scene);
            stage.setTitle("Hachi");

            // Intercept close request
            stage.setOnCloseRequest(event -> {
                event.consume();
                handleClose(stage);
            });

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ensure the save directory exists.
     */
    private void ensureSaveDirectory() {
        File saveDir = BASE_DIR.toFile();
        if (!saveDir.exists() && !saveDir.mkdirs()) {
            throw new RuntimeException("Could not create save directory: " + saveDir);
        }
    }

    /**
     * Handles confirmation before closing the stage.
     */
    private void handleClose(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Did you remember to say bye to Hachi?");
        alert.setContentText("Press OK to exit without saving your tasks.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage.close();
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}


