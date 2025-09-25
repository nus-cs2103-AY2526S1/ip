import heimerdinger.Heimerdinger;
import heimerdinger.HeimerdingerException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Heimerdinger heimerdinger;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image heimerdingerImage = new Image(this.getClass().getResourceAsStream("/images/heimerdinger.png"));

    /**
     * Initializes UI bindings.
     *
     * <p>Binds the scroll pane to always stay at the bottom
     * as new dialog boxes are added, and ensures dialog
     * boxes expand to fill available width when resized.</p>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        dialogContainer.setFillWidth(true);
    }

    /** Injects the Duke instance */
    public void setHeimerdinger(Heimerdinger h) {
        heimerdinger = h;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        try {
            String response = heimerdinger.getResponse(input);
            dialogContainer.getChildren().add(
                    DialogBox.getHeimerdingerDialog(response, heimerdingerImage)
            );
            if (input.trim().equals("bye")) {
                PauseTransition delay = new PauseTransition(Duration.seconds(2)); // wait 2 seconds
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
            }
        } catch (HeimerdingerException ex) {
            dialogContainer.getChildren().add(
                    DialogBox.getErrorDialog(ex.getMessage(), heimerdingerImage)
            );
        }
        userInput.clear();
    }
}
