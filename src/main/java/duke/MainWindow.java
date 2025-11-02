package duke;

import javafx.application.Platform;
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

    private Penguin penguin;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image penguinImage = new Image(this.getClass().getResourceAsStream("/images/DaPenguin.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Penguin instance */
    public void setPenguin(Penguin p) {
        penguin = p;
        // Show welcome message
        String welcomeMessage = "Hello! I'm Penguin\nWhat can I do for you?";
        dialogContainer.getChildren().add(
                DialogBox.getPenguinDialog(welcomeMessage, penguinImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Penguin's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }

        String response = penguin.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPenguinDialog(response, penguinImage)
        );
        userInput.clear();

        // Close the application if user types "bye"
        if (input.trim().equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }
}
