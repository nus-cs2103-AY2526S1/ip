package mon;

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

    private Mon mon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image monImage = new Image(this.getClass().getResourceAsStream("/images/DaMon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        
        // AI-created code: Add welcome message when the application starts
        showWelcomeMessage();
        
        // AI-created code: Focus on the input field for better UX
        userInput.requestFocus();
    }

    /** Injects the Mon instance */
    public void setMon(Mon d) {
        mon = d;
    }

    /**
     * Shows a welcome message when the application starts.
     * AI-created code: Added to create a welcoming chat-like experience
     */
    private void showWelcomeMessage() {
        String welcomeMessage = "Hi there! I'm Mon, your personal task manager. 🎯\n\n" +
                "I can help you:\n" +
                "• Add tasks: 'todo buy groceries'\n" +
                "• Set deadlines: 'deadline assignment /by 2025-09-20'\n" +
                "• Schedule events: 'event meeting /from 2025-09-20 /to 2025-09-20'\n" +
                "• View your tasks: 'list'\n" +
                "• Mark tasks as done: 'mark 1'\n" +
                "• Search tasks: 'find groceries'\n\n" +
                "What would you like to do today?";
        
        dialogContainer.getChildren().add(
                DialogBox.getMonDialog(welcomeMessage, monImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Mon's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        
        if (input.isEmpty()) {
            return;
        }
        
        String response = mon.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMonDialog(response, monImage)
        );
        userInput.clear();
    }
}