package tinkerton.gui;

import tinkerton.core.Tinkerton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    /** The Tinkerton application instance. */
    private Tinkerton tinkerton;

    /** The image representing the user. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    /** The image representing Tinkerton. */
    private Image dukeImage =
            new Image(this.getClass().getResourceAsStream("/images/DaTinkerton.png"));

    /**
     * Initializes the main window, setting up the welcome messages and scroll binding.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello! I'm Tinkerton", dukeImage),
                DialogBox.getDukeDialog("What can I do for you?", dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Tinkerton instance into the controller.
     *
     * @param t The Tinkerton application instance.
     */
    public void setTinkerton(Tinkerton t) {
        tinkerton = t;
    }

    /**
     * Handles user input by creating dialog boxes for the user's message and Tinkerton's response.
     * Splits the response if needed and appends dialog boxes to the container. Clears the user
     * input after processing. Exits the application if the farewell message is received.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tinkerton.getResponse(input);
        if (response == "Bye. Hope to see you again soon!") {
            handleExit();
            return;
        }
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));

        String[] parts = response.split("<SPLIT>");
        for (String part : parts) {
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(part.trim(), dukeImage));
        }
        userInput.clear();
    }

    private void handleExit() {
        Platform.exit();
    }
}
