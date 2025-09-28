package alpaca.gui;

import alpaca.Alpaca;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for the main GUI window of the Alpaca application.
 * <p>
 * Handles user input, displays dialog boxes, and connects to the Alpaca backend.
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

    private Alpaca alpaca;

    private final Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/DaUser.png")
    );
    private final Image dukeImage = new Image(
            this.getClass().getResourceAsStream("/images/DaDuke.png")
    );

    /**
     * Initializes the GUI controller.
     * Binds the scroll pane to automatically scroll to the bottom as new messages appear.
     */
    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane cannot be null";
        assert dialogContainer != null : "DialogContainer VBox cannot be null";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Set title after scene is available
        sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                setWindowTitle("Alpaca Task Manager");
            }
        });
    }

    /**
     * Sets the title of the window.
     *
     * @param title The title to set for the window
     */
    private void setWindowTitle(String title) {
        // Get the stage from the scene and cast Window to Stage
        if (getScene() != null && getScene().getWindow() instanceof Stage) {
            ((Stage) getScene().getWindow()).setTitle(title);
        }
    }

    /**
     * Alternative method to set title - call this after the scene is set
     *
     * @param title The title to set for the window
     */
    public void setTitle(String title) {
        if (getScene() != null && getScene().getWindow() instanceof Stage) {
            ((Stage) getScene().getWindow()).setTitle(title);
        }
    }

    /**
     * Injects the Alpaca instance into the controller.
     *
     * @param alpaca The Alpaca instance; must not be null.
     */
    public void setAlpaca(Alpaca alpaca) {
        assert alpaca != null : "Alpaca instance cannot be null";
        this.alpaca = alpaca;

        // Show welcome message when alpaca is set
        showWelcome();
    }

    /**
     * Displays the welcome message when the application starts.
     */
    private void showWelcome() {
        String welcomeMessage = "Hello! I'm Alpaca! \nWhat can I do for you?";

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }

    /**
     * Handles user input from the text field.
     * <p>
     * Creates two dialog boxes: one echoing the user's input and another
     * containing Alpaca's response. Appends both to the dialog container
     * and clears the input field.
     */
    @FXML
    private void handleUserInput() {
        assert alpaca != null : "Alpaca instance must be set before handling input";

        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return; // Ignore empty input
        }

        String response = alpaca.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();
    }
}