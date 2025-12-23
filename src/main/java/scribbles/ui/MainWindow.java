package scribbles.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import scribbles.Scribbles;

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

    private Scribbles scribbles;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/User.png")));
    private final Image scribblesImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Scribbles.png")));

    /**
     * Plays the initial sequence of events when loading MainWindow
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getScribblesDialog(Ui.getWelcomeMsg(), scribblesImage)
        );
    }

    /**
     * Injects the Scribbles instance
     */
    public void setScribbles(Scribbles scribbles) {
        this.scribbles = scribbles;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Scribbles's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = scribbles.getResponse(input);
        assert(!response.isEmpty());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getScribblesDialog(response, scribblesImage)
        );
        userInput.clear();
    }
}
