package david;

import david.ui.David;
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

    private David david;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image davidImage = new Image(this.getClass().getResourceAsStream("/images/David.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the David instance */
    public void setDavid(David d) {
        david = d;
        String welcome = "Hello! I'm David.\nWhat can I do for you?";
        dialogContainer.getChildren().add(
                DialogBox.getDavidDialog(welcome, davidImage)
        );
    }

    /**
     * Creates two dialog boxes, one for user input and the other for David's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = david.getResponse(input);
        if (response.equals("Bye. Hope to see you again soon!")) {
            javafx.application.Platform.exit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDavidDialog(response, davidImage)
        );
        userInput.clear();
    }
}
