package woop.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import woop.logic.Storage;
import woop.logic.WoopLogic;

public class WoopApp extends Application {
    private static final int MIN_HEIGHT = 220;
    private static final int MIN_WIDTH = 417;
    private WoopLogic woop;

    @Override
    public void start(Stage stage) {
        Storage.checkDirectory();
        woop = new WoopLogic(Storage.retrieveSave());

        try {
            setDimensions(stage);
            loadUi(stage);
            stage.show();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load UI.").showAndWait();
            Platform.exit();
        }
    }

    private void setDimensions(Stage s) {
        s.setMinHeight(MIN_HEIGHT);
        s.setMinWidth(MIN_WIDTH);
    }

    private void loadUi(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WoopApp.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.setTitle("Woop");
        fxmlLoader.<MainWindow>getController().setWoop(woop);
    }
}
