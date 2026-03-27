import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import joe.Joe;
import joe.task.Task;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

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
    @FXML
    private Label reminderLabel;

    private Joe joe;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image joeImage = new Image(this.getClass().getResourceAsStream("/images/joe.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Joe instance */
    public void setJoe(Joe j) {
        joe = j;
        updateReminder();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container. Clears the user
     * input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = joe.getResponse(input);
        if (response.equals("Goodbye!")) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> Platform.exit()));
            timeline.play();
        }
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getJoeDialog(response, joeImage));
        userInput.clear();
        updateReminder();
    }

    public VBox getDialogContainer() {
        return dialogContainer;
    }

    private void updateReminder() {
        Task nextTask = joe.getTaskList().getSoonestTask();
        if (nextTask == null) {
            reminderLabel.setText("No upcoming tasks 🎉");
        } else {
            reminderLabel.setText("Next task: " + nextTask.toString());
        }
    }
}
