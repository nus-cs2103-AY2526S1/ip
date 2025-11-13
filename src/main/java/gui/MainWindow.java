package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import taskbot.TaskBot;

/**
 * Handles user interaction with chat interface
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private TaskBot taskBot;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image taskBotImage = new Image(this.getClass().getResourceAsStream("/images/TaskBot.gif"));

    /**
     * Initialises GUI components after FXML loading
     */
    @FXML
    public void initialise() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setTaskBot(TaskBot t) {
        taskBot = t;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(taskBot.getWelcome(), taskBotImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = taskBot.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, taskBotImage)
        );
        userInput.clear();
    }
}
