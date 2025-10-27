package JavaX;

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

    private Luka luka;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/im.png"));
    private Image lukaImage = new Image(this.getClass().getResourceAsStream("/images/im1.png"));

    @FXML
    public void initialize() {

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        showWelcomMessage();
    }

    /** Injects the Duke instance */
    public void setLuka(Luka d) {
        luka = d;
    }

    private void showWelcomMessage() {
        String welcomeMessage = "Hello! I'm Luka\n What can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getLukaDialog(welcomeMessage, lukaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = luka.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLukaDialog(response, lukaImage)
        );
        userInput.clear();
    }
}