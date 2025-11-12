package gui;

import chatbot.Chatbot9000;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Chatbot9000 chatbot9000 = new Chatbot9000();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.getIcons().add(
                    new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"))
            );
            stage.setTitle("Chatbot9000");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChatbot9000(chatbot9000);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
