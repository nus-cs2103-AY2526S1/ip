package bob;

import bob.gui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    private static final String FILE_PATH = "./data/tasks.txt";
    private Bob bob = new Bob(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.getController();
            controller.setBob(bob);

            // Show welcome message
            controller.showWelcome(bob.getWelcomeMessage()); // inject the bob instance
            if (bob.hasTasks()) {
                controller.showWelcome(bob.getResponse("list"));
            }
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


