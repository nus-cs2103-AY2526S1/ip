import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tension.Tension;
import tension.helper.Ui;

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

    private Tension tension = null;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * initializes dialogContainer
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog((new Ui()).displayGreetingAsString(), dukeImage));
    }

    /** Injects the Tension instance */
    public void setTension(Tension tension) {
        try {
            this.tension = tension;
        } catch (Exception e) {
            this.tension = null;
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (tension == null) {
            // Belt-and-suspenders guard (in case someone wires the button differently)
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(
                    "Could not load data path, make sure file data.txt is in top folder", dukeImage));
        }
        String input = userInput.getText();
        String response = tension.responseToInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * displays error in GUI when unable to load data file
     */
    public void displayReadError(String friendlyMessage, Throwable error) {
        // Disable input since core isn’t available
        sendButton.setDisable(true);

        // Show a chat-style message so it appears inside your app
        String detail = (error != null && error.getMessage() != null) ? error.getMessage() : "";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(
                        friendlyMessage + (detail.isBlank() ? "" : "\n\ndata.txt should be in the top folder"),
                        dukeImage
                )
        );
    }
}
