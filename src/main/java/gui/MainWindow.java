package gui;

import java.io.IOException;

import amogus.Amogus;
import amogus.AmogusException;
import amogus.FileStorage;
import amogus.Parser;
import amogus.UI;
import command.Command;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tasks.TaskList;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static final String FILE_PATH = "./data/Tasks.TaskList.txt";
    private Amogus amogus;
    private UI ui = new UI();
    private Stage stage;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image amogusImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Gets chatbot to show welcome message when GUI is shown.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().add(
                DialogBox.create(ui.welcome(), amogusImage, true)
        );
    }

    /** Injects the Amogus instance */
    public void setAmogus(Amogus a) {
        amogus = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws AmogusException {
        String input = userInput.getText();
        FileStorage fs = new FileStorage(FILE_PATH);
        TaskList tasks;

        try {
            tasks = fs.loadTasks();
        } catch (AmogusException | IOException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }

        String response;
        Command command;
        try {
            command = Parser.parse(input);
            response = command.execute(tasks, ui, fs);
        } catch (AmogusException e) {
            response = "Oops! I'm not sure what your command is!";
            command = null;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.create(input, userImage, false),
                DialogBox.create(response, amogusImage, true)
        );
        userInput.clear();

        if (command != null && command.isExit() && stage != null) {
            Platform.runLater(() -> {
                PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
                delay.setOnFinished(event -> stage.close());
                delay.play();
            });
        }
    }
}
