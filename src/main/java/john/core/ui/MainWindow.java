package john.core.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import javafx.stage.Stage;

import john.core.John;

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

    private John john;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/you.png"));
    private Image johnImage = new Image(this.getClass().getResourceAsStream("/john.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setJohn(John d) {
        assert d != null : "backend must not be null";
        john = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing John's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.isBlank()) {
            return;
        }

        John.Reply reply = john.getReply(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJohnDialog(reply.message(), johnImage)
        );
        userInput.clear();

        if (reply.exit()) {
            PauseTransition delay = new PauseTransition(Duration.millis(3000));
            delay.setOnFinished(ev -> {
                ((Stage) sendButton.getScene().getWindow()).close();
            });
            delay.play();
        }
    }
}