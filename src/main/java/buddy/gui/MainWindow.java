package buddy.gui;

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

import static buddy.gui.DialogBox.getBuddyDialog;

/* Re-use policy
 * Code was modified from JavaFX tutorial.
 */

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

    private BuddyLogic logic;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image buddyImage = new Image(this.getClass().getResourceAsStream("/images/DaBuddy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the BuddyLogic instance */
    public void setBuddyLogic(BuddyLogic logic) {
        this.logic = logic;
        dialogContainer.getChildren().add(
                DialogBox.getBuddyDialog("Hello! I'm Buddy\nWhat can I do for you?", buddyImage)
        );    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = logic.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                getBuddyDialog(response, buddyImage)
        );
        userInput.clear();

        if (logic.shouldExit()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);

            PauseTransition delay = new PauseTransition(Duration.millis(1000));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }
}