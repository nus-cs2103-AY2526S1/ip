package reboot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import reboot.Reboot;
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

    private Reboot reboot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Reboot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Reboot instance */
    public void setReboot(Reboot r) {
        reboot = r;
        showGreeting();
    }

    /**
     * Creates the welcome message.
     */
    @FXML
    private void showGreeting() {
        String welcome = reboot.getWelcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcome, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = reboot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

