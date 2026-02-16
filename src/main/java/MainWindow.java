import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import core.TronaldDump;
import core.Ui;

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

    private TronaldDump tronaldDump;

    private Image userImage;
    private Image tronaldDumpImage;

    @FXML
    public void initialize() {
        // Auto-scroll to bottom when dialog container height changes
        dialogContainer.heightProperty().addListener((observable) -> {
            scrollPane.setVvalue(1.0);
        });
        
        // Load images safely with error handling
        try {
            userImage = new Image(getClass().getResourceAsStream("/user.png"));
            tronaldDumpImage = new Image(getClass().getResourceAsStream("/tronalddump.png"));
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
            // Use default placeholder images or handle gracefully
        }
    }
   

    /** Injects the TronaldDump instance */
    public void setTronaldDump(TronaldDump d) {
        if (d == null) {
            throw new IllegalArgumentException("TronaldDump instance cannot be null");
        }
        tronaldDump = d;
        // Show welcome message after TronaldDump is set
        showWelcomeMessage();
    }

    /**
     * Shows the welcome message when the GUI starts.
     */
    private void showWelcomeMessage() {
        String welcomeMessage = Ui.getWelcomeMessage();
    
        dialogContainer.getChildren().addAll(
                DialogBox.getTronaldDumpDialog(welcomeMessage, tronaldDumpImage)
        );
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing TronaldDump's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return; // Don't process empty input
        }
        
        // Validate that TronaldDump instance is available
        if (tronaldDump == null) {
            String errorMessage = "TronaldDump instance not initialized. Please restart the application.";
            dialogContainer.getChildren().addAll(
                    DialogBox.getTronaldDumpDialog(errorMessage, tronaldDumpImage)
            );
            userInput.clear();
            return;
        }
        
        // Show user input
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        
        try {
            // Process command and get response
            String response = tronaldDump.getResponse(input);
            
            // Show TronaldDump response
            dialogContainer.getChildren().addAll(
                    DialogBox.getTronaldDumpDialog(response, tronaldDumpImage)
            );
            
            // Check if this is an exit command
            if (isExitCommand(input)) {
                // Show goodbye message before exiting
                String goodbyeMessage = Ui.getGoodbyeMessage();
                // Add a small delay before exit to show goodbye message
                javafx.concurrent.Task<Void> exitTask = new javafx.concurrent.Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Thread.sleep(1000); // 1 second delay
                        javafx.application.Platform.exit();
                        return null;
                    }
                };
                Thread exitThread = new Thread(exitTask);
                exitThread.setDaemon(true);
                exitThread.start();
            }
        } catch (Exception e) {
            // Handle any unexpected errors
            String errorMessage = "An unexpected error occurred: " + e.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getTronaldDumpDialog(errorMessage, tronaldDumpImage)
            );
        }
        
        userInput.clear();
    }
    
    /**
     * Checks if the input is an exit command.
     * @param input The user input to check
     * @return true if the input is an exit command, false otherwise
     */
    private boolean isExitCommand(String input) {
        String trimmedInput = input.trim().toLowerCase();
        return trimmedInput.equals("bye");
    }
}