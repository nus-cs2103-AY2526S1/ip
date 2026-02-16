import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import core.TronaldDump;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private TronaldDump tronaldDump = new TronaldDump();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            
            // Set the window title with the product name
            stage.setTitle("TronaldDump - Task Management Chatbot");
            
            // Set minimum window size to ensure usability
            stage.setMinWidth(400);
            stage.setMinHeight(400);
            
            stage.setScene(scene);
            
            // Inject the TronaldDump instance into the controller
            MainWindow controller = fxmlLoader.getController();
            if (controller != null) {
                controller.setTronaldDump(tronaldDump);
            } else {
                System.err.println("Warning: Could not get MainWindow controller");
            }
            
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load FXML file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error during application startup: " + e.getMessage());
            e.printStackTrace();
        }
    }
}