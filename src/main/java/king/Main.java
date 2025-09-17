package king;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for King using FXML.
 */
public class Main extends Application {

    private final King king = new King();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox vb = fxmlLoader.load();
            Scene scene = new Scene(vb);
            stage.setScene(scene);
            stage.setTitle("The King Chatbot");
            fxmlLoader.<MainWindow>getController().setKing(king); // inject the King instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
