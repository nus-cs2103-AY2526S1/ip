package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shrek.Shrek;

/**
 * A GUI for your chatbot using FXML.
 * This class sets up the main window and starts the JavaFX application.
 */
public class Main extends Application {

    private Shrek shrek = new Shrek("./data/shrek.txt");

    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file that defines the GUI layout
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Get the controller and inject dependencies
            MainWindow controller = fxmlLoader.getController();
            controller.setShrek(shrek);
            controller.setStage(stage); // Pass the stage reference

            // Create the scene and set it on the stage
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setMinHeight(220);
            stage.setMinWidth(417);
            // stage.setMaxWidth(417); // Add this if you didn't automatically resize elements

            // Set window title and show it
            stage.setTitle("Shrek Chatbot");

            // Load and set the application icon (onion icon for Shrek)
            try {
                Image icon = new Image(getClass().getResourceAsStream("/images/onion.png"));
                stage.getIcons().add(icon);
            } catch (Exception e) {
                System.out.println("Could not load icon: " + e.getMessage());
                // Continue without icon if there's an error
            }
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
