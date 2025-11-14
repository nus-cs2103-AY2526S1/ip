import baymax.Baymax;
import exception.BaymaxException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller class for the main GUI window of the Baymax application.
 * <p>
 * Manages user input, dialog display, and interaction with the Baymax logic.
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

    /**
     * The Baymax instance providing the backend logic
     */
    private Baymax baymax;

    /**
     * Images for user and Baymax avatars
     */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image baymaxImage = new Image(this.getClass().getResourceAsStream("/images/DaBaymax.png"));

    /**
     * Initializes the controller after the FXML elements are loaded.
     * Sets the scrollPane to follow the height of the dialog container.
     */
    @FXML
    public void initialize() {

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Baymax instance into the controller and shows the welcome message.
     *
     * @param d the Baymax instance
     */
    public void setBaymax(Baymax d) {
        baymax = d;
        showWelcome();
    }

    /**
     * Handles the user's input: creates dialog boxes for user input and Baymax's response,
     * then appends them to the dialog container. Clears the user input field afterward.
     *
     * @throws BaymaxException if user input is invalid
     */
    @FXML
    private void handleUserInput() throws BaymaxException {
        String input = userInput.getText().trim();

        try {
            if (input.isEmpty()) {
                throw new BaymaxException("I'm confused... please type something!");
            }
            String response = baymax.getResponse(input);
            String commandType = baymax.getCommandType();

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBaymaxDialog(response, baymaxImage, commandType)
            );
        } catch (BaymaxException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getBaymaxDialog(e.getMessage(), baymaxImage, ""));
        } finally {
            userInput.clear();
        }
    }

    /**
     * Shows the welcome message from Baymax at application startup.
     */
    private void showWelcome() {
        dialogContainer.getChildren().add(
                DialogBox.getBaymaxDialog(baymax.getUi().showWelcome(), baymaxImage, "")
        );
    }
}