package stewie.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stewie.Stewie;

/**
 * Enhanced GUI for Stewie with modern styling and better UX
 */
public class Main extends Application {

    private static Stewie stewie = new Stewie();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/stewie/gui/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Add the enhanced CSS stylesheets
            scene.getStylesheets().add(getClass().getResource("/stewie/gui/css/main.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/stewie/gui/css/dialog-box.css").toExternalForm());

            // Configure stage with proper window properties
            stage.setTitle("Stewie Task Assistant - Your Personal Task Manager");
            stage.setMinHeight(400);
            stage.setMinWidth(350);
            stage.setMaxHeight(Double.MAX_VALUE);
            stage.setMaxWidth(Double.MAX_VALUE);
            stage.setResizable(true);

            // Allow fullscreen
            stage.setFullScreenExitHint("Press ESC to exit fullscreen");
            stage.setFullScreenExitKeyCombination(javafx.scene.input.KeyCombination.keyCombination("ESC"));

            // Set application icon if available
            try {
                Image icon = new Image(getClass().getResourceAsStream("/stewie/gui/images/stewie.png"));
                stage.getIcons().add(icon);
            } catch (Exception e) {
                // Icon not found, continue without it
                System.out.println("App icon not found, continuing without icon");
            }

            stage.setScene(scene);

            // Inject Stewie instance into controller
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setStewie(stewie);

            // Center stage on screen
            stage.centerOnScreen();

            // Show with fade-in effect
            stage.setOpacity(0);
            stage.show();

            // Fade in animation
            javafx.animation.Timeline fadeIn = new javafx.animation.Timeline(
                    new javafx.animation.KeyFrame(
                            javafx.util.Duration.millis(300),
                            new javafx.animation.KeyValue(stage.opacityProperty(), 1)
                    )
            );
            fadeIn.play();

        } catch (IOException e) {
            System.err.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
