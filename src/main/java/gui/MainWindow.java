package gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import kane.Kane;

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

    private Kane kane;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image kaneImage = new Image(this.getClass().getResourceAsStream("/images/kane.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Kane instance */
    public void setKane(Kane d) {
        kane = d;
        String welcomeMessage = "Hello! I'm Kane!\nWhat can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getKaneDialog(welcomeMessage, kaneImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kane.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKaneDialog(response, kaneImage)
        );
        userInput.clear();

        // Check if the command is "bye" to close the application.
        if (input.trim().equalsIgnoreCase("bye")) {
            // Disable input field and button after saying bye
            userInput.setDisable(true);
            sendButton.setDisable(true);

            // Create a 1-second pause before closing the platform.
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }

        userInput.clear();
    }
}
