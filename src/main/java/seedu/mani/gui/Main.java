package seedu.mani.gui;

import java.io.IOException;
import seedu.mani.ui.Mani;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * This code was developed with guidance from ChatGPT (OpenAI).
 * ChatGPT provided advice on implementing better UI in JavaFX.
 * Date: 22-Sep-2025
 */
public class Main extends Application {

    private Mani mani = new Mani("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Resizable window
            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.setTitle("Mani - Task Manager");
            stage.setScene(scene);
            stage.show();

            fxmlLoader.<MainWindow>getController().setMani(mani);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

