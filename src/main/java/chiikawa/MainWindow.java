package chiikawa;

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

    private Chiikawa chiikawa;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image chiikawaImage = new Image(this.getClass().getResourceAsStream("/images/chiikawa.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Chiikawa instance */
    public void setChiikawa(Chiikawa c) {
        chiikawa = c;
        String response = chiikawa.getResponse("");
        dialogContainer.getChildren().addAll(
                DialogBox.getChiikawaDialog(response, chiikawaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Chiikawa's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chiikawa.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChiikawaDialog(response, chiikawaImage)
        );
        userInput.clear();
    }
}
