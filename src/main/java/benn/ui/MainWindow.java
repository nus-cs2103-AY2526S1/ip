package benn.ui;

import benn.Benn;
import benn.ui.ChatBubble;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main chat window.
 * Manages layout and interaction logic between user and Benn.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane chatScrollPane;
    @FXML
    private VBox dialogParentContainer;
    @FXML
    private TextField inputField;
    @FXML
    private Button sendButton;

    private Benn benn;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/XinYang.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Benn.png"));

    @FXML
    public void initialize() {
        chatScrollPane.vvalueProperty().bind(dialogParentContainer.heightProperty());
    }

    public void attachBenn(Benn b) {
        benn = b;
        dialogParentContainer.getChildren().add(
                ChatBubble.getDukeDialog(benn.getWelcome(), dukeImage)
        );
    }

    /**
     * Handles sending user input, displaying both sides of the conversation,
     * and clearing the input box afterwards.
     */
    @FXML
    private void handleUserInput() {
        String input = inputField.getText();
        String response = benn.getResponse(input);

        dialogParentContainer.getChildren().addAll(
                ChatBubble.getUserDialog(input, userImage),
                ChatBubble.getDukeDialog(response, dukeImage)
        );
        inputField.clear();

        if (benn.isExit()) {
            javafx.animation.PauseTransition delay =
                    new javafx.animation.PauseTransition(javafx.util.Duration.millis(800));
            delay.setOnFinished(e -> javafx.application.Platform.exit());
            delay.play();
        }
    }
}