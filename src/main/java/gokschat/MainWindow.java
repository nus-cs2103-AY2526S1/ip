package gokschat;

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

    private GoksChat goksChat;

    private Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/6F52D14B-E85E-4746-AAA4-B31068596FC5.png"));
    private Image dukeImage = new Image(
            this.getClass().getResourceAsStream("/images/A4616380-3FD9-4567-9B1E-8E52C8C4D52C.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String welcomeMessage = "    ____________________________________________________________\n" +
                "    What's up, what's up! I'm GoksChat\n" +
                "    How can I assist you today?\n" +
                "    ____________________________________________________________";

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }

    /** Injects the GoksChat instance */
    public void setGoksChat(GoksChat goksChat) {
        this.goksChat = goksChat;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = goksChat.getResponse(input);

        if (input.equals("bye")) {
            response = "    ____________________________________________________________\n" +
                    "    Good Day! Hope to see you again\n" +
                    "    ____________________________________________________________";

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );

            // Close the application
            Platform.exit();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }

        userInput.clear();
    }
}
