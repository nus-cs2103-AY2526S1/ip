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

    private Sam sam;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserPhoto.png"));
    private Image samImage = new Image(this.getClass().getResourceAsStream("/images/SamPhoto.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Sam instance */
    public void setSam(Sam s) {
        sam = s;
        // Show welcome message
        dialogContainer.getChildren().add(
            DialogBox.getSamDialog(sam.getWelcomeMessage(), samImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Sam's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sam.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSamDialog(response, samImage)
        );
        userInput.clear();
        
        // Check if user wants to exit
        if (response.equals("🙏 May peace accompany you on your journey. Until we meet again in mindful productivity!")) {
            // Close the application after a short delay
            javafx.application.Platform.runLater(() -> {
                javafx.application.Platform.exit();
            });
        }
    }
}
