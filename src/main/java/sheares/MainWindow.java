package sheares;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    private Sheares sheares;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/dukeImage.png"));

    /**
     * Initializes the entry layout + initial message of the chatbot
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String initMessage = "";
        String first = "    Hello! I'm Sheares, your personal chatbot! \n";
        String second = "    What can i do for you? \n";
        initMessage += first + second;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(initMessage, dukeImage)
        );
    }

    /** Injects the Duke instance */
    public void setSheares(Sheares s) {
        sheares = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        String response = sheares.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input + "    ", userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        // I used AI (chatgpt) for the below code to close the app after typing bye
        // If user types "bye", schedule app to close after short delay
        if ("bye".equalsIgnoreCase(input.trim())) {
            PauseTransition pause = new PauseTransition(Duration.seconds(3.0));
            pause.setOnFinished(e -> {
                // Get the stage from any node (e.g., userInput) and close it
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close(); // Equivalent to clicking "X"
            });
            pause.play();
        }
    }
}
