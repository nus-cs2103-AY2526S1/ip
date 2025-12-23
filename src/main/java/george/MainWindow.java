package george;

import george.ui.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private George george;
    private Stage stage;
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    /** Injects the Duke instance */
    public void setGeorge(George g) {
        george = g;
    }

    /** Sets the stage for closing the application */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = george.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                isErrorMessage(response) ? DialogBox.getErrorDialog(response, dukeImage)
                        : DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        // Check if the user entered the exit command
        if (input.trim().equalsIgnoreCase("bye")) {
            // Add a slight delay before closing to show the response
            new Thread(() -> {
                try {
                    Thread.sleep(1000); // Wait 1 second to show the response
                    Platform.runLater(() -> {
                        if (stage != null) {
                            stage.close();
                        }
                        Platform.exit();
                    });
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }

    /**
     * Detects if a response is an error message.
     * You can customize this logic based on how your George class returns errors.
     */
    private boolean isErrorMessage(String response) {
        // Check for common error indicators
        return response.contains("Error:")
                || response.contains("Unable to")
                || response.contains("Invalid")
                || response.toLowerCase().contains("error");
    }

    private void showWelcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.getWelcomeMessage(), dukeImage));
    }
}
