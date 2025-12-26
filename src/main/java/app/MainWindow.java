package app;

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

import java.util.Objects;

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

    private Friday friday;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/hulk.png"));
    private Image fridayImage = new Image(this.getClass().getResourceAsStream("/images/friday.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Friday instance */
    public void setFriday(Friday friday) {
        this.friday = friday;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert !Objects.equals(input, "") : "The input should not be empty";
        String response = friday.getResponse(input);
        assert !Objects.equals(input, "") : "The response should not be empty";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFridayDialog(response, fridayImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }

    /**
     * Sets the greeting message for Friday when the program starts.
     */
    public void setGreeting() {
        dialogContainer.getChildren().addAll(DialogBox.getFridayDialog(friday.getWelcome(), fridayImage));
    }
}
