package bytebuddy.gui;

import java.io.IOException;

import bytebuddy.ByteBuddy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for ByteBuddy using FXML.
 */
public class Main extends Application {

    private final ByteBuddy byteBuddy = new ByteBuddy("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setByteBuddy(byteBuddy);  // inject the ByteBuddy instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
