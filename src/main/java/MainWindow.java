import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tarawrr.Tarawrr;

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

    private Tarawrr tarawrr;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image tarawrrImage = new Image(this.getClass().getResourceAsStream("/images/DaTarawrr.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void showWelcome() {
        String welcome = tarawrr.getUi().showWelcomeMessage();
        dialogContainer.getChildren().add(
                DialogBox.getTarawrrDialog(welcome, tarawrrImage)
        );
    }

    /** Injects the Duke instance */
    public void setTarawrr(Tarawrr d) {
        tarawrr = d;
        showWelcome();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tarawrr.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTarawrrDialog(response, tarawrrImage)
        );
        userInput.clear();
    }
}

