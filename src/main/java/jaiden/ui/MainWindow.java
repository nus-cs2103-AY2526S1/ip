package jaiden.ui;

import jaiden.command.CommandType;
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

    private Jaiden jaiden;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image jaidenImage = new Image(this.getClass().getResourceAsStream("/images/DaJaiden.png"));

    /**
     * Initializes the controller after its root element has been completely loaded.
     * Ensures auto-scroll to bottom when new messages are added.
     */
    @FXML
    private void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /**
     * Injects the Jaiden instance.
     */
    public void setJaiden(Jaiden jaiden) {
        this.jaiden = jaiden;
    }

    /**
     * Shows welcome message.
     */
    public void showWelcome() {
        dialogContainer.getChildren().add(
                DialogBox.getJaidenDialog(
                        "Hello! I'm Jaiden 👋\nWhat can I do for you?",
                        this.jaidenImage,
                        CommandType.NULLCOMMAND
                )
        );
    }

    /**
     * Shows loading error.
     */
    public void showLoadingError(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getJaidenDialog(
                        message,
                        this.jaidenImage,
                        CommandType.ERRORCOMMAND
                )
        );
    }

    /**
     * Handles user input, generates response, and updates dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText().trim();

        if (input.isEmpty()) {
            dialogContainer.getChildren().add(
                    DialogBox.getJaidenDialog(
                            "⚠ Please enter a command.",
                            this.jaidenImage,
                            CommandType.ERRORCOMMAND
                    )
            );
            userInput.clear();
            return;
        }

        try {
            String response = this.jaiden.getResponse(input);
            CommandType commandType = this.jaiden.getCommandType();

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, this.userImage),
                    DialogBox.getJaidenDialog(response, this.jaidenImage, commandType)
            );

            userInput.clear();

            if (commandType.equals(CommandType.EXITCOMMAND)) {
                Platform.exit();
            }
        } catch (Exception e) {
            dialogContainer.getChildren().add(
                    DialogBox.getJaidenDialog(
                            "⚠ Oops, something went wrong: " + e.getMessage(),
                            this.jaidenImage,
                            CommandType.ERRORCOMMAND
                    )
            );
        }
    }
}
