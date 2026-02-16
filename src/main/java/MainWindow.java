import bestie.Bestie;
import javafx.application.Platform;
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

    private Bestie bestie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image bestieImage = new Image(this.getClass().getResourceAsStream("/images/bestie.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Bestie instance */
    public void setBestie(Bestie d) {
        bestie = d;
        dialogContainer.getChildren().add(
                DialogBox.getBestieDialog(bestie.getGreeting(), bestieImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bestie's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bestie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBestieDialog(response, bestieImage)
        );
        userInput.clear();

        if (bestie.isExit()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            Platform.exit();
        }
    }
}

