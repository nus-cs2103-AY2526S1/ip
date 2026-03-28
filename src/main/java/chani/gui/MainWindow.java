package chani.gui;

import chani.Chani;
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

    private Chani chani;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaChani.jpeg"));
    private Image chaniImage = new Image(this.getClass().getResourceAsStream("/images/DaDerp.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Chani instance */
    public void setChani(Chani c) {
        this.chani = c;
        String initMessage = chani.getInitMessage();
        String greeting = chani.getGreeting();
        dialogContainer.getChildren().add(
                DialogBox.getChaniDialog(String.format(initMessage + "%n" + greeting), chaniImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Chani's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chani.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChaniDialog(response, chaniImage)
        );
        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            handleExit();
        }
    }

    /**
     * Disables user input and send button, pausing for 1.5 seconds before exiting
     */
    private void handleExit() {
        userInput.setDisable(true);
        sendButton.setDisable(true);

        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(e -> Platform.exit());
        delay.play();
    }

}
