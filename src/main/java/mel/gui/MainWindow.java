package mel.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import mel.Mel;
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

    private Mel mel;
    private boolean isExit;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image melImage = new Image(this.getClass().getResourceAsStream("/images/mel.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setMel(Mel m) {
        mel = m;

        sendMessage(mel.initialise());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mel.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMelDialog(response, melImage)
        );
        userInput.clear();

        if (mel.isExit()) {
            Stage stage = (Stage) sendButton.getScene().getWindow();
            stage.close();

        }
    }

    @FXML
    private void sendMessage(String msg) {
        dialogContainer.getChildren().add(
                DialogBox.getMelDialog(msg, melImage)
        );
    }
}
