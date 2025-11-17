package sophia;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    private VBox reminderContainer;

    private Sophia sophia;
    private ReminderBox rb;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image sophiaImage = new Image(this.getClass().getResourceAsStream("/images/sophia.png"));
    /**
     * Initialise the UI on anchorpane
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    public void setSophia(Sophia s) {
        sophia = s;
        dialogContainer.getChildren().add(
                DialogBox.getSophiaDialog(
                        "Hello! I’m Sophia. How can I help you today?",
                        sophiaImage
                )
        );
        rb = ReminderBox.getReminderBox();
        rb.updateReminders(sophia);
        reminderContainer.getChildren().add(rb);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Sophia's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = sophia.run(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getSophiaDialog(response, sophiaImage)
            );
        } catch (SophiaException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getSophiaDialog(e.getMessage(), sophiaImage)
            );
        }
        rb.updateReminders(sophia);
        userInput.clear();
        if (sophia.isExit()) {
            java.lang.System.exit(0);
        }
    }
}

