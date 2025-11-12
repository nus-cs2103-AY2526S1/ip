package gui;

import chatbot.Chatbot9000;
import chatbot.response.Response;
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

    private Chatbot9000 chatbot9000;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        dialogContainer.setFillWidth(true);
    }

    /** Injects the Duke instance */
    public void setChatbot9000(Chatbot9000 c) {
        chatbot9000 = c;

        // Show greeting message once at startup
        Response greet = chatbot9000.getGreeting();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greet.getResponse(), dukeImage)
        );
    }



    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText();
        Response response = chatbot9000.getResponse(input);
        String responseMessage = response.getResponse();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(responseMessage, dukeImage)
        );

        userInput.clear();

        if (response.isExit()) {
            // Close the GUI after a short delay to show the last message
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
