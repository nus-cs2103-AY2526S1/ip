package com.oadultradeepfield.smartotter.component;

import java.util.Objects;

import com.oadultradeepfield.smartotter.SmartOtter;
import com.oadultradeepfield.smartotter.SmartOtterConstant;
import com.oadultradeepfield.smartotter.util.CustomIO;

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
    /**
     * Image displayed for user messages in dialog boxes
     */
    private final Image userImage =
        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));

    /**
     * Image displayed for SmartOtter messages in dialog boxes
     */
    private final Image smartOtterImage =
        new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/smart_otter.png")));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    /**
     * The SmartOtter instance that handles user input processing
     */
    private SmartOtter smartOtter;

    /**
     * Injects the SmartOtter instance to be used for processing user input.
     *
     * @param otter the SmartOtter instance to use
     */
    public void setSmartOtter(SmartOtter otter) {
        smartOtter = otter;
    }

    /**
     * Initializes the controller after FXML loading. Sets the scroll pane to start at the top and
     * adds Enter key support.
     */
    @FXML
    public void initialize() {
        scrollPane.setVvalue(0.0);
        userInput.setOnKeyPressed(this::handleKeyPressed);

        dialogContainer
            .getChildren()
            .addAll(
                DialogBox.getSmartOtterDialog(
                    SmartOtterConstant.GREETING_MESSAGE_TEMPLATE, smartOtterImage));
    }

    /**
     * Handles key press events on the user input field. Submits input when Enter key is pressed.
     *
     * @param event the key event
     */
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleUserInput();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing SmartOtter's reply
     * and then appends them to the dialog container. Clears the user input after processing. Does
     * nothing if the input is empty or contains only whitespace.
     */
    @FXML
    private void handleUserInput() {
        String input = CustomIO.sanitizeInput(userInput.getText());

        if (input.isEmpty()) {
            return;
        }

        String response = smartOtter.getResponse(input);
        dialogContainer
            .getChildren()
            .addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSmartOtterDialog(response, smartOtterImage));
        userInput.clear();

        scrollPane.layout();
        Platform.runLater(() -> scrollPane.setVvalue(1.0));

        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
