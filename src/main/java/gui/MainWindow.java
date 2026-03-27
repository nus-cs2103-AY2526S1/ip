package gui;

import javafx.util.Duration;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import sunday.Sunday;
import sunday.Response;

/**
 * Controller for the main GUI.
 * Similar to the one in tutorial.
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

    private Sunday sunday;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Avatar.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Sunday instance */
    public void setSunday(Sunday d) {
        sunday = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.isBlank()) {
            return;
        }

        // Use the metadata-aware response so we can style errors differently.
        Response res = sunday.getResponseWithMeta(input); // <-- implement as shown earlier

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                res.isError()
                        ? DialogBox.getDukeErrorDialog(res.getText(), dukeImage)
                        : DialogBox.getDukeDialog(res.getText(), dukeImage)
        );

        userInput.clear();

        if (sunday.willExit(input)) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            PauseTransition pause = new PauseTransition(Duration.millis(250));
            pause.setOnFinished(e -> Platform.exit());
            pause.play();
        }
    }
}