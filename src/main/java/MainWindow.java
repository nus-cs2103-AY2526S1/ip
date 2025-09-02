import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rafayel.Rafayel;

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

    private Rafayel rafayel;

    // private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image rafayelImage = new Image(this.getClass().getResourceAsStream("/images/RafayelIcon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Rafayel instance */
    public void setRafayel(Rafayel r) {
        rafayel = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = rafayel.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, rafayelImage),
                DialogBox.getRafayelDialog(response, rafayelImage));
        userInput.clear();
    }
}
