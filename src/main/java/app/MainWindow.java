package app;

import airy.Airy;
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

    private Airy airy;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Peter.png"));
    private final Image airyImage = new Image(this.getClass().getResourceAsStream("/images/Brian.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setAiry(Airy d) {
        airy = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = airy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAiryDialog(response, airyImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            // Delay the close for user to see the bye message
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            // Needed as its asynchronous. We cannot use synchronous for javaFX.
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

    /**
     * Displays a welcome message in the dialog container.
     *
     * @param text the message to be displayed
     */
    public void displayWelcome(String text) {
        dialogContainer.getChildren().add(
                DialogBox.getAiryDialog(text, airyImage)
        );
    }
}
