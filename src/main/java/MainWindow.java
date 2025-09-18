import exception.RainyException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rainy.Rainy;

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

    private Rainy rainy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image rainyImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window of the GUI after all @FXML fields have been injected.
     * <p>
     * This method sets up the scroll behavior so that the scroll pane
     * automatically scrolls to the bottom as new messages are added.
     * It also displays the welcome message from the Rainy application
     * in the dialog container when the GUI is first loaded.
     * </p>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Rainy instance */
    public void setRainy(Rainy r) {
        rainy = r;
        dialogContainer.getChildren().add(
                DialogBox.getRainyDialog(rainy.getWelcomeMessage(), rainyImage, "")
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws RainyException {
        String input = userInput.getText();
        String response = rainy.getResponse(input);
        String commandType = rainy.getCommandType();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRainyDialog(response, rainyImage, commandType)
        );
        userInput.clear();
    }
}
