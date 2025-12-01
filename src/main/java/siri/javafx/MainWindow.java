package siri.javafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import siri.Siri;
import siri.util.Parser;


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

    private Siri siri;
    private Parser parser;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Siri.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    /** Injects the siri instance */
    public void setSiri(Siri s) {
        siri = s;
        String welcome = siri.getWelcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getSiriDialog(welcome, dukeImage)
        );
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Siri's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = siri.getResponse(input);
        if (input.equals("bye")) {
            Platform.exit();
            System.exit(0);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSiriDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

