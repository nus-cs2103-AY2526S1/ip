package piper.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import piper.Piper;

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

    private Piper piper;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image piperImage = new Image(this.getClass().getResourceAsStream("/images/DaPiper.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Piper instance */
    public void setPiper(Piper d) {
        piper = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Piper's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        final String input = userInput.getText();
        if (input == null || input.isBlank()) {
            return;
        }

        String reply;
        try {
            if (piper == null) {
                reply = "Piper has flown out for the time being. Please try again in a moment.";
            } else {
                reply = piper.run(input);
            }
        } catch (Exception e) {
            // Never let exceptions bubble to JavaFX
            reply = e.getMessage() != null ? e.getMessage() : "Something went wrong.";
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPiperDialog(reply, piperImage)
        );

        userInput.clear();

        // Close window if backend signalled exit (e.g., after "bye").
        if (piper != null && piper.isExit()) {
            // Hides the window without System.exit (friendlier to tests/gradle).
            getScene().getWindow().hide();
        }
    }

    public void showGreeting(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getPiperDialog(message, piperImage)
        );
    }

}
