package cookie.ui;

import cookie.Cookie;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    private Cookie cookie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat2.png"));
    private Image cookieImage = new Image(this.getClass().getResourceAsStream("/images/cat.png"));

    /**
     * Initializes GUI components and sets up User Interface.
     * Method is called by JavaFX.
     * Sets autoscroll, displays welcome message and adds enter key support.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Add welcome message when app starts
        Platform.runLater(() -> {
            if (cookie != null) {
                String welcomeMessage = "Hey there! My name is Cookie\nHow can I help you?";
                dialogContainer.getChildren().add(
                        DialogBox.getBotDialog(welcomeMessage, cookieImage, false)
                );
            }
        });

        // Add Enter key support for better UX
        userInput.setOnKeyPressed(this::handleKeyPressed);

        // Focus on input field when window loads
        Platform.runLater(() -> userInput.requestFocus());
    }

    /** Injects the Cookie instance */
    public void setCookie(Cookie c) {
        cookie = c;
    }

    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleUserInput();
        }
    }

    /**
     * Creates dialog boxes with asymmetric design and error handling
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return; // Don't process empty input
        }
        boolean isBye = input.equalsIgnoreCase("bye");

        // Add user message immediately
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        // Clear input and disable send button while processing
        userInput.clear();
        sendButton.setDisable(true);

        // Process response
        try {
            String response = cookie.getResponse(input);
            boolean isError = response.startsWith("Oh no!");

            dialogContainer.getChildren().add(
                    DialogBox.getBotDialog(response, cookieImage, isError)
            );
            if (isBye) {
                // Disable input while waiting to close
                sendButton.setDisable(true);
                userInput.setDisable(true);
                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(evt -> Platform.exit());
                delay.play();
                return;
            }
        } catch (Exception e) {
            // Handle any unexpected errors
            dialogContainer.getChildren().add(
                    DialogBox.getBotDialog("Oh no! Something went wrong: " + e.getMessage(), cookieImage, true)
            );
        } finally {
            // Re-enable send button and focus input
            sendButton.setDisable(false);
            userInput.requestFocus();
        }
    }
}
