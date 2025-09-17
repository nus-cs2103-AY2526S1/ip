package bob.ui;

import java.util.Objects;

import bob.Bob;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * MainWindow that handles interaction in the main window.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image bobImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/chatBob.jpg")));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Bob bob;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Bob instance
     */
    public void setBob(Bob b) {
        bob = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Bob's reply and then appends them to the dialog container. Clears the
     * user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            Stage stage = (Stage) sendButton.getScene().getWindow();
            stage.close();
        }
        String response = bob.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getBobDialog(response, bobImage));
        userInput.clear();

    }
}
