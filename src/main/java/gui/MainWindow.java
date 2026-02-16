package gui;

import nicholas.Nicholas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

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

    private Nicholas nicholas;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image nicholasImage = new Image(this.getClass().getResourceAsStream("/images/DaNicholas.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setNicholas(Nicholas nicholas) {
        this.nicholas = nicholas;

        String greeting = "Hello I'm Nicholas, how can I help you today";

        dialogContainer.getChildren().addAll(
                DialogBox.getNicholasDialog(greeting, nicholasImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nicholas.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNicholasDialog(response, nicholasImage)
        );
        if (input.trim().equals("bye")) {
            Platform.exit();
        }
        userInput.clear();
    }
}