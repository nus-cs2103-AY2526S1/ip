package ui;
import alfred.Alfred;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Alfred using FXML.
 */
public class Main extends Application {
    private Alfred alfred;

    @Override
    public void start(Stage stage) {
        try {
            alfred = new Alfred("data/alfred.txt");
            stage.setTitle("Alfred");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAlfred(alfred);  // inject the Alfred instance
            stage.show();

            // Add shutdown hook to save tasks when application closes
            stage.setOnCloseRequest(event -> {
                try {
                    alfred.saveToStorage();  // Save tasks before closing
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
