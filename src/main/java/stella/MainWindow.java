package stella;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controls the main GUI.
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

    private Stella stella;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/moon.png"));
    private Image stellaImage = new Image(this.getClass().getResourceAsStream("/images/star.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Stella instance.
     *
     * @param s A Stella object.
     */
    public void setStella(Stella s) {
        stella = s;
        dialogContainer.getChildren().addAll(
                DialogBox.getStellaDialog("Hello! I am Stella. How can I help you? ", stellaImage)
        );
    }

    /**
     * Creates two dialog boxes,
     * one echoing user input and the other containing Stella's reply,
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = stella.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getStellaDialog(response, stellaImage)
        );
        userInput.clear();
    }
}
