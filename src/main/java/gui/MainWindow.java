package gui;

import app.Jack;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main gui.
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

    private Jack jack;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/aaronsilly.jpg"));
    private Image jackImage = new Image(this.getClass().getResourceAsStream("/images/aarontongue.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the app.Jack instance */
    public void setJack(Jack j) {
        jack = j;
        // Show welcome message from Jack in the gui when controller is initialized
        if (dialogContainer != null && jack != null) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getJackDialog(jack.getWelcomeMessage(), jackImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jack.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJackDialog(response, jackImage)
        );
        userInput.clear();
    }
}