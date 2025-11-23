package conversal.ui;

import conversal.Conversal;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window
 *
 * Handles user interactions such as typing a message and clicking the Send button.
 * Displays user and {@link Conversal}  messages in the dialog container, using {@link DialogBox}.
 *
 */
public class MainWindowController {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;

    private Conversal conversal;

    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image conversalImage =
            new Image(this.getClass().getResourceAsStream("/images/DaConversal.png"));

    /**
     * Inserts the instance into this controller.
     * Also configures the scroll pane to auto-scroll to the bottom when new content is added.
     *
     * @param conversal the core Conversal instance
     */
    public void setConversal(Conversal conversal) {
        this.conversal = conversal;
        dialogContainer.heightProperty()
                .addListener((obs, oldV, newV)
                        -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles user input when the Send button is clicked
     *
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.isBlank()) {
            return;
        }

        dialogContainer.getChildren().add(DialogBox.userDialog(input, userImage));

        String response = conversal.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.conversalDialog(response, conversalImage));

        if ("bye".equalsIgnoreCase(input.trim())) {
            userInput.setDisable(true);
        }
        userInput.clear();
    }

    /**
     * Displays an initial greeting or message from {@link Conversal} when the app starts.
     *
     * @param message the message text to display
     */
    public void showConversal(String message) {
        dialogContainer.getChildren().add(DialogBox.conversalDialog(message, conversalImage));
    }
}
