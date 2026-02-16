package jibjab;

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

    private JibJab jibjab;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/man.png"));
    private Image jibjabImage = new Image(this.getClass().getResourceAsStream("/images/ai.png"));

    /**
     * Initializes the main GUI components.
     *
     * This method performs the following:
     * 1. Binds the vertical scroll property of the scroll pane to the height
     *    property of the dialog container, ensuring the scroll bar automatically
     *    follows new messages.
     * 2. Displays a default welcome message in the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMsg = "Hello from JibJab\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getJibJabDialog(welcomeMsg, jibjabImage));
    }

    /** Injects the JibJab instance */
    public void setJibJab(JibJab j) {
        jibjab = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jibjab.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJibJabDialog(response, jibjabImage)
        );
        userInput.clear();
    }
}
