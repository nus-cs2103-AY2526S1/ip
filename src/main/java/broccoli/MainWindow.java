package broccoli;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
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

    private Broccoli broccoli;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image broccoliImage = new Image(this.getClass().getResourceAsStream("/images/broccoli.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setBroccoli(Broccoli d) {
        this.broccoli = d;
        String greeting = broccoli.getGreeting();
        dialogContainer.getChildren().add(
                DialogBox.getBroccoliDialog(greeting, broccoliImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = broccoli.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBroccoliDialog(response, broccoliImage)
        );

        userInput.clear();

        if (input.trim().toLowerCase().equals("bye")) {
            javafx.application.Platform.runLater(() -> {
                javafx.application.Platform.exit();
            });
        }
    }
}
