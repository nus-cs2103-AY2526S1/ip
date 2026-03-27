package tom;

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

    private Tom tom;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Jerry.jpeg"));
    private Image tomImage = new Image(this.getClass().getResourceAsStream("/images/Tom.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Tom instance */
    public void setTom(Tom t) {
        tom = t;
        dialogContainer.getChildren().addAll(DialogBox.getTomDialog(Ui.greet(), tomImage));
    }

    /**
     * Pauses for 3 seconds before closing the chat window.
     */
    public void delayExit() {
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> {
            Platform.exit();
            System.exit(0);
        });
        delay.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tom's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tom.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTomDialog(response, tomImage)
        );
        userInput.clear();

        if (input.strip().equals("bye")) {
            delayExit();
        }
    }
}
