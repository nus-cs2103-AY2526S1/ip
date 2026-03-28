package lenny.gui;

import java.net.URL;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lenny.logic.Lenny;

/**
 * JavaFX application entry point for the Lenny chatbot.
 * <p>
 * This class loads {@code MainWindow.fxml}, wires the controller with a
 * {@link lenny.logic.Lenny} instance, and shows the primary stage.
 * </p>
 */
public class Gui extends Application {

    /**
     * Starts the JavaFX application by loading the main window layout and
     * setting up the scene.
     *
     * @param stage the primary stage provided by the JavaFX runtime
     * @throws Exception if the FXML cannot be found or loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        URL url = Gui.class.getResource("/view/MainWindow.fxml");
        Objects.requireNonNull(url, "MainWindow.fxml not found under /view");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        MainWindow controller = loader.getController();
        controller.setLenny(new Lenny("data/LennyData.txt"), true);

        stage.setTitle("Lenny");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
