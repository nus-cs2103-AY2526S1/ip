package jerome;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jerome.gui.MainWindow;

/**
 * JavaFX entry point for Jerome chatbot GUI.
 */
public class Main extends Application {
    private Jerome jerome = new Jerome();

    /**
     * Starts the stage
     * @param stage the primary stage for this application
     * @throws Exception when controller fails to load
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainWindow controller = fxmlLoader.getController();
        controller.setJerome(jerome);

        stage.setTitle("Jerome - Your Homie");
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.setScene(scene);
        stage.show();
    }
}

