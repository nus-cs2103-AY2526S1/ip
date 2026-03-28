package sanbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import sanbot.reminder.DeadlineTasksReminder;
import sanbot.storage.Storage;
import sanbot.tasklist.TaskList;
import sanbot.ui.Ui;

/**
 * Controller for the main GUI.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/DaBot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getBotDialog(Ui.greeting(), botImage)
        );
        TaskList.initializeTaskList(Storage.loadTasks());
        dialogContainer.getChildren().add(
                DialogBox.getBotDialog(DeadlineTasksReminder.remindDeadlinesMessage(), botImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing sanbot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = Sanbot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();
    }
}

