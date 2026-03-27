package jaiden.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Jaiden using FXML.
 */
public class Main extends Application {
    private static final int STAGE_HEIGHT = 220;
    private static final int STAGE_WIDTH = 417;

    private final Jaiden jaiden = new Jaiden("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        assert stage != null;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        try {
            AnchorPane ap = fxmlLoader.load();
            assert ap != null;
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(STAGE_HEIGHT);
            stage.setMinWidth(STAGE_WIDTH);
            fxmlLoader.<MainWindow>getController().setJaiden(this.jaiden);
            stage.setTitle("Jaiden");
            stage.show();

            if (jaiden.hasLoadError()) {
                fxmlLoader.<MainWindow>getController().showLoadingError(
                        "⚠ The data file is corrupted (Content not in the expected format).");
            }

            fxmlLoader.<MainWindow>getController().showWelcome();
        } catch (IOException e) {
            fxmlLoader.<MainWindow>getController().showLoadingError("⚠ The source file is corrupted.");
        }
    }
}


