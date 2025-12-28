package meeseeks;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Controller for the main GUI with improved UX and error handling.
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

    private Meeseeks meeseeks;

    private Image userImage;
    private Image dukeImage;

    /**
     * Initializes the main window components and sets up the user interface.
     * This method is called automatically by JavaFX when the FXML is loaded.
     */
    @FXML
    public void initialize() {
        // Auto-scroll to bottom when new messages are added
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        
        // Load images for user and application avatars
        userImage = new Image(this.getClass().getResourceAsStream("/image/user.png"));
        dukeImage = new Image(this.getClass().getResourceAsStream("/image/meeseeks.png"));
        
        // Add keyboard shortcuts for better user experience
        userInput.setOnKeyPressed(this::handleKeyPress);
        
        // Display welcome message with available commands
        String welcomeMessage = "Hello! I'm Mr. Meeseeks!\nLook at me!\n\n" +
                "I can help you manage your tasks. Try these commands:\n" +
                "• todo <task> - Add a todo\n" +
                "• deadline <task> /by <date: dd/mm/yyyy hhmm> - Add a deadline\n" +
                "• event <task> /from <start> /to <end> - Add an event\n" +
                "• recurring <task> /every <frequency> /due <date: dd/mm/yyyy hhmm> - Add recurring task\n" +
                "• list - Show all tasks\n" +
                "• find <keyword> - Search tasks\n" +
                "• overdue - Show overdue recurring tasks\n" +
                "• stats - Show task statistics\n" +
                "• delete <task number> - delete a task\n" +
                "• bye - Exit";
        
        dialogContainer.getChildren().add(
                DialogBox.getAppDialog(welcomeMessage, dukeImage)
        );
    }

    /**
     * Handles keyboard input events, specifically the Enter key for sending messages.
     * 
     * @param event the key event that occurred
     */
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleUserInput();
        }
    }

    /**
     * Injects the Meeseeks application instance into this controller.
     * This allows the controller to communicate with the main application logic.
     * 
     * @param meeseeks the Meeseeks application instance
     */
    public void setDuke(Meeseeks meeseeks) {
        this.meeseeks = meeseeks;
    }

    /**
     * Creates dialog boxes for user input and app response, with error highlighting.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return; // Don't process empty input
        }
        
        String response = meeseeks.getResponse(input);
        
        // Add user message
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        
        // Determine if response is an error and add appropriate dialog
        boolean isError = response.startsWith("Oh Geez!") || 
                         response.contains("Error") || 
                         response.contains("Invalid") ||
                         response.contains("Unknown");
        
        if (isError) {
            dialogContainer.getChildren().add(
                    DialogBox.getErrorDialog(response, dukeImage)
            );
        } else {
            dialogContainer.getChildren().add(
                    DialogBox.getAppDialog(response, dukeImage)
            );
        }
        
        userInput.clear();
        
        // Handle exit command
        if (input.equals("bye")) {
            javafx.application.Platform.exit();
        }
    }
}
