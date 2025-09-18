package tony.ui;

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
import tony.Tony;

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

    private Tony tony;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image tonyImage = new Image(this.getClass().getResourceAsStream("/images/tony.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Tony instance */
    public void setTony(Tony tony) {
        this.tony = tony;
        String response = tony.getGreeting();
        dialogContainer.getChildren().addAll(
                DialogBox.getTonyDialog(response, tonyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tony's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tony.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTonyDialog(response, tonyImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit()); // Close after 3 seconds
            delay.play();
        }
    }
}
