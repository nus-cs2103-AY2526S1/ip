package paul.gui;

import java.util.Objects;

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
import paul.Paul;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static final int EXIT_DELAY = 1;
    private static final double SCROLL_SENSITIVITY = 0.003;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Paul paul;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image paulImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Paul.png")));

    /** Initialize the main window */
    @FXML
    public void initialize() {
        // Credit to @salmonkarp to fix scrollbar not working properly
        dialogContainer.heightProperty().addListener((obs, oldVal, newVal) -> {
            scrollPane.setVvalue(1.0);
        });

        // ChatGPT was used to help generate this section of code
        dialogContainer.setOnScroll(event -> {
            double deltaY = event.getDeltaY() * SCROLL_SENSITIVITY; // tweak for sensitivity
            scrollPane.setVvalue(scrollPane.getVvalue() - deltaY);
            event.consume(); // prevent double-handling
        });

        // Make ScrollPane focusable for keyboard/mouse wheel events
        scrollPane.setFocusTraversable(true);
    }

    /** Injects the Paul instance */
    public void setPaul(Paul p) {
        paul = p;
        dialogContainer.getChildren().add(
                DialogBox.getPaulDialog(paul.getUi().greetUser(), paulImage, "Greeting")
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Paul's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = paul.getResponse(input);
        String commandType = paul.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPaulDialog(response, paulImage, commandType)
        );
        userInput.clear();

        // Exit when user says bye
        // ChatGPT was used to help with this section of code
        if (paul.getCommandType().equals("BYE")) {
            // Create a delay when exiting
            PauseTransition delay = new PauseTransition(Duration.seconds(EXIT_DELAY));
            delay.setOnFinished(event -> {
                Platform.exit(); // closes the application
            });
            delay.play();
        }
    }
}
