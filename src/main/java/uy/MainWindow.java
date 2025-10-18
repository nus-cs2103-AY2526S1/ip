package uy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI. Responsible for wiring UI controls to the
 * application's behavior and for inserting the initial welcome message.
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

    private static final String WELCOME_MESSAGE = "Welcome, I am Uy, your Zen Productivity Coach. Take a deep breath, and lets begin your mindful journey.";

    private Uy uy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaUy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Show welcome message when the gui controller is initialized
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(WELCOME_MESSAGE, dukeImage)
        );
    }

    /** Injects the Uy instance */
    public void setUy(Uy u) {
        uy = u;
    }

    /**
     * Handle the event when the user presses the send button or hits enter.
     * This method reads the text from the input field, obtains a response
     * from the `Uy` instance and appends both the user and application
     * dialog boxes to the dialog container. The input field is cleared
     * after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = uy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
