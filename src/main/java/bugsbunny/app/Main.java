package bugsbunny.app;

import java.io.IOException;

import bugsbunny.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Entry point to BugsBunny chatbot GUI using JavaFX
 */
public class Main extends Application {

    private BugsBunny bugsbunny = new BugsBunny();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("BugsBunny");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBugsBunny(bugsbunny); // inject the BugsBunny instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
