package bro.ui;

import java.io.IOException;

import bro.commands.ByeCommand;
import bro.commands.Command;
import bro.tasks.Tasks;
import bro.utils.FileIo;
import bro.utils.Parser;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The main class for the Bro application, responsible for initializing and
 * running the program.
 */
public class Bro extends Application {
    private Parser parser;
    private Tasks tasks;

    private Bro bro = this;

    /**
     * Initializes the Bro application by setting up the user interface, the user
     * input parser and loading existing tasks from storage.
     */
    public Bro() {
        try {
            this.parser = new Parser();
            this.tasks = new Tasks(FileIo.loadData());
        } catch (Exception e) {
            System.out.println("Error initializing Bro.");
        }
    }

    /**
     * Starts the Bro application, allowing user interaction.
     */
    public String getResponse(String input) {
        Command command = parser.getCommand(input);
        String output = command.execute(tasks);
        if (command instanceof ByeCommand) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
        return output;
    }

    @Override
    public void start(Stage stage) {
        try {
            Image broImage = new Image(this.getClass().getResourceAsStream("/images/broProfilePic.jpg"));
            FXMLLoader fxmlLoader = new FXMLLoader(Bro.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Bro");
            stage.getIcons().add(broImage);
            fxmlLoader.<MainWindow>getController().setBro(bro);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
