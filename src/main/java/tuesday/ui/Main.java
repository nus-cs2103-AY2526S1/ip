package tuesday.ui;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tuesday.Tuesday;


/**
 * A GUI for Tuesday using FXML.
 */

public class Main extends Application {


    @Override
    public void start(Stage stage) {

        // Load data from data/tuesday.txt to list
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "/data/tuesday.txt";

        File file = new File(filePath);
        File parent = file.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            System.out.println("Failed to create directory: " + parent.getAbsolutePath());
        }

        System.out.println("Loading from: " + filePath);
        // Load data from data/tuesday.txt to list
        Tuesday tuesday = new Tuesday(filePath);

        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTuesday(tuesday);

            stage.setTitle("Tuesday");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}