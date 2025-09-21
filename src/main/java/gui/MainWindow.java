package gui;
import java.util.List;

import itachi.Itachi;
import javafx.animation.PauseTransition;
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

    private Itachi itachi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaItachi.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setItachi(Itachi itachi) {
        this.itachi = itachi;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // Show the user input
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        // Get bot responses
        List<String> responses = itachi.getResponse(input);

        // Merge responses into one string
        String botMessage = String.join(" ", responses); // join with space for one line

        // Show bot message in a single dialog
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(botMessage, dukeImage));

        // Clear input field
        userInput.clear();

        // Check for exit command
        if (input.equalsIgnoreCase("bye")) {
            // Delay exit slightly so user can see message
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(e -> javafx.application.Platform.exit());
            delay.play();
        }
    }

}
