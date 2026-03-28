package clippy.ui.fx;

import clippy.Clippy;
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

    private Clippy clippy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/barney.png"));
    private Image clippyImage = new Image(this.getClass().getResourceAsStream("/images/clippy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setClippy(Clippy c) {
        clippy = c;
    }

    /** Displays the welcome message. */
    public void showWelcome() {
        String welcome = clippy.getWelcome();
        dialogContainer.getChildren().add(
                DialogBox.getClippyDialog(welcome, clippyImage, "Welcome")
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = clippy.getResponse(input);
        String commandType = clippy.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getClippyDialog(response, clippyImage, commandType)
        );
        userInput.clear();
    }
}
