package gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import shrek.Shrek;

/**
 * Controller for the main GUI.
 * This class handles user interactions with the chat interface.
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

    private Shrek shrek;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private final Image shrekImage = new Image(this.getClass().getResourceAsStream("/images/DaShrek.png"));
    private Stage stage; // Reference to the main stage

    /**
     * Initializes the GUI components after FXML loading.
     * Sets up auto-scrolling but DOES NOT show welcome message here.
     */
    @FXML
    public void initialize() {
        //  Replace binding with listener for better scroll behavior
        // This maintains auto-scroll to bottom while allowing mouse wheel scrolling
        dialogContainer.heightProperty().addListener((obs, oldVal, newVal) -> {
            scrollPane.setVvalue(1.0);
        });
    }

    /**
     * Injects the Shrek instance and shows welcome message.
     */
    public void setShrek(Shrek s) {
        shrek = s;

        // Show welcome message AFTER shrek is set
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(shrek.getWelcomeMessage(), shrekImage)
        );
    }

    /**
     * Sets the stage reference for closing the window.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Handles user input - creates dialog boxes for user input and Shrek's response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = shrek.getResponse(input);

        // Add both user and Shrek dialog boxes to the container
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, shrekImage)
        );

        // Clear the input field
        userInput.clear();

        // Handle exit command
        if (input.equalsIgnoreCase("bye")) {
            handleExitCommand();
        }
    }

    /**
     * Handles the exit command with a 2-second delay before closing.
     */
    private void handleExitCommand() {
        // Disable input while waiting to close
        userInput.setDisable(true);
        sendButton.setDisable(true);

        // Create a pause transition for 5 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {
            // Close the application
            if (stage != null) {
                stage.close();
            } else {
                Platform.exit(); // Fallback if stage reference is not set
            }
        });
        delay.play();
    }
}
