
package ozil.main;

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
 * Controller for the main GUI window.
 * Handles user interactions and displays conversation between user and Ozil.
 */
public class MainWindow extends AnchorPane {
    private static final String EXIT_COMMAND_RESPONSE = "bye";
    private static final int EXIT_DELAY_SECONDS = 2;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ozil ozil;
    private final Image userImage = new Image(getClass().getResourceAsStream("/images/User.png"));
    private final Image ozilImage = new Image(getClass().getResourceAsStream("/images/Ozil.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    /** Sets the Ozil instance for this controller */
    public void setOzil(Ozil ozil) {
        this.ozil = ozil;
    }

    /**
     * Handles user input by processing it through Ozil and displaying the conversation.
     * Clears the input field after processing. Exits application after delay if "bye" is received.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        String response = ozil.getResponse(input);
        String ozilResponse = EXIT_COMMAND_RESPONSE.equals(response)
                ? Messages.outro()
                : response;

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOzilDialog(ozilResponse, ozilImage)
        );

        userInput.clear();

        if (EXIT_COMMAND_RESPONSE.equals(response)) {
            scheduleApplicationExit();
        }
    }

    /** Displays welcome message from Ozil when application starts */
    @FXML
    private void showWelcomeMessage() {
        dialogContainer.getChildren().add(
                DialogBox.getOzilDialog(Messages.intro(), ozilImage)
        );
    }

    /** Schedules application exit after a delay */
    private void scheduleApplicationExit() {
        PauseTransition delay = new PauseTransition(Duration.seconds(EXIT_DELAY_SECONDS));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}