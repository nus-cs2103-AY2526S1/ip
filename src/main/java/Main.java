
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import rafayel.Rafayel;
import rafayel.RafayelException;

/**
 * A GUI for Rafayel using FXML.
 */
public class Main extends Application {

    private final Rafayel rafayel = new Rafayel("src/main/java/data/rafayel.txt");
    private MainWindow mainWindow;
    private Timer reminderTimer;

    private final int ONE_SECOND = 1000;
    private final int FIVE_MIN = 5 * 60 * ONE_SECOND;

    public Main() throws RafayelException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Rafayel Chatbot");

            // Get the controller and set Rafayel instance
            mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setRafayel(rafayel);

            stage.show();

            // Schedule reminder checks 
            Timer reminderTimer = new Timer();
            reminderTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        mainWindow.checkAndShowReminders();
                    });
                }
            }, ONE_SECOND, FIVE_MIN);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Override stop function to stop timer and exit platform.
     */
    @Override
    public void stop() {
        try {
            rafayel.save();
        } catch (RafayelException e) {
            e.printStackTrace();
        }

        // Cancel the timer to exit the program
        if (reminderTimer != null) {
            reminderTimer.cancel();
        }

        Platform.exit();
        System.exit(0);
    }
}