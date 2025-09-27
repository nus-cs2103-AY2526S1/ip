package eve.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Entry point for launching the Eve GUI application.
 * <p>
 * This class initializes the JavaFX runtime and loads the main window layout
 * from the FXML file. It also injects the {@link eve.Eve} backend instance
 * into the controller, enabling interaction between the GUI and the core logic.
 */
public class Main extends Application {

    private eve.Eve eve = new eve.Eve();

    /**
     * Starts the JavaFX application by setting up the primary stage.
     * <p>
     * This method:
     * <ul>
     * <li>Loads the {@code MainWindow.fxml} layout via {@link FXMLLoader}.</li>
     * <li>Creates and attaches a {@link Scene} containing the loaded layout.</li>
     * <li>Injects the {@link eve.Eve} instance into the {@link MainWindow}
     * controller.</li>
     * <li>Configures the primary stage's title and displays it.</li>
     * </ul>
     *
     * @param stage the primary stage provided by the JavaFX runtime
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setEve(eve);
            stage.setTitle("Eve");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("MainWindow.fxml loading error");
        }
    }
}
