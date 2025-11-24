package burgerburglar;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * The JavaFX entry point for BurgerBurglar.
 * <p>
 * Responsible for loading the GUI layout and initializing the application.
 */
public class Main extends Application {

    private static final String MAIN_WINDOW_FXML = "/view/MainWindow.fxml";
    private static final String WINDOW_TITLE = "BurgerBurglar";
    private static final String DEFAULT_FILE_PATH = "data/burgerburglar.txt";

    private final BurgerBurglar burgerburglar = new BurgerBurglar(DEFAULT_FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_FXML));
            AnchorPane mainLayout = fxmlLoader.load();

            Scene scene = new Scene(mainLayout);
            stage.setTitle(WINDOW_TITLE);
            stage.setScene(scene);
            stage.show();

            MainWindow controller = fxmlLoader.getController();
            controller.setBurgerBurglar(burgerburglar);
        } catch (IOException e) {
            showFatalError(e);
        }
    }

    /**
     * Displays a fatal error in the console if the application cannot start.
     *
     * @param e the IOException that prevented loading the main window
     */
    private void showFatalError(IOException e) {
        System.err.println("Failed to load the main window: " + e.getMessage());
        e.printStackTrace();
    }
}
