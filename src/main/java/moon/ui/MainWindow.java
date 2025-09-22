package moon.ui;

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
import moon.logic.Moon;

/**
 * JavaFX controller for the main chat window.
 * <p>
 * Manages message flow between the text field and the {@link Moon} logic, and renders
 * conversation bubbles into a scrollable container.
 */
public class MainWindow extends AnchorPane {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private Moon moon;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserPic.jpg"));
    private final Image moonImage = new Image(this.getClass().getResourceAsStream("/images/BotPic.jpg"));

    /**
     * Initializes scrolling and width behavior after FXML injection.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setFillWidth(true);
        scrollPane.setFitToWidth(true);
    }

    /** Injects the {@link Moon} chatbot instance. */
    public void setMoon(Moon moon) {
        this.moon = moon;
    }

    /** Shows the initial greeting from the bot and focuses the input field. */
    public void showGreeting() {
        addMoonMessage(UiMessages.showGreetingMessage());
        userInput.requestFocus();
    }

    /** Shows the storage load result (success/empty/failure). */
    public void showInitialStorage() {
        addMoonMessage(moon.initiateStorage());
        userInput.requestFocus();
    }

    /**
     * Handles a user submission: renders the user message, obtains the bot response,
     * renders it, and optionally exits after the farewell.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.moon.getResponse(input);

        if (moon.isExitCommand(input)) {
            addUserMessage(input);
            addMoonMessage(UiMessages.showExitMessage());
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(e -> Platform.exit());
            pause.play();
            return;
        }

        addUserMessage(input);
        addMoonMessage(response);
        addMoonMessage(UiMessages.showAskingMessage());
        userInput.clear();
    }

    /** Appends a bot message bubble to the dialog container. */
    public void addMoonMessage(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getMoonDialog(message, moonImage));
    }

    /** Appends a user message bubble to the dialog container. */
    public void addUserMessage(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(message, userImage));
    }
}
