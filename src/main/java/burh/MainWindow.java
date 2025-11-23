package burh;

import javafx.fxml.FXML;
import javafx.application.Platform;
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

    private Burh burh;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/i.png"));
    private Image burhImage = new Image(this.getClass().getResourceAsStream("/images/burh.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Burh instance */
    public void setBurh(Burh b) {
        burh = b;
        startup();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Burh's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = burh.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBurhDialog(response, burhImage)
        );
        userInput.clear();
        if (input.toUpperCase().equals("BYE")) {
            Platform.exit();
        }
    }

    private void startup() {
        dialogContainer.getChildren().addAll(
                DialogBox.getBurhDialog(burh.getWelcomeString(), burhImage)
        );
    }
}
