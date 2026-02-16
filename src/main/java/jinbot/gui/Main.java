package jinbot.gui;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jinbot.JinBot;

/**
 * A GUI for JinBot using FXML.
 */
public class Main extends Application {

    private JinBot jinbot = new JinBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            URL css = getClass().getResource("/view/dialog.css");
            if (css != null) {
                scene.getStylesheets().add(css.toExternalForm());
            } else {
                System.out.println("Dialog.css not found!");
            }
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJinBot(jinbot);
            stage.setTitle("JinBot");
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load MainWindow.fxml: " + e.getMessage());
        }
    }
}
