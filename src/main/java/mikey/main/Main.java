
package mikey.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Mikey using FXML.
 */
public class Main extends Application {

    private Mikey mikey = new Mikey("data/mikey.txt");

    //Claude AI was used for improving this method
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Configure stage for better UX
            stage.setTitle("Mikey - Your Personal Task Assistant");
            stage.setScene(scene);
            stage.setMinWidth(350);
            stage.setMinHeight(400);
            stage.setResizable(true);

            // Set window icon (optional - if you have an icon)
            try {
                stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/mikey.png")));
            } catch (Exception e) {
                // Icon loading failed, continue without icon
            }

            fxmlLoader.<MainWindow>getController().setMikey(mikey);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}