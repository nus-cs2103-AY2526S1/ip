package javafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import watermelon.Watermelon;

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

    private Watermelon watermelon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image watermelonImage = new Image(this.getClass().getResourceAsStream("/images/watermelon.png"));

    /**
     * Initializes the controller. Sets up auto-scrolling for the dialog container
     * and creates circular avatar patterns for user and watermelon profile pictures.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.setSpacing(0);
    }

    /** Injects the Watermelon instance */
    public void setWatermelon(Watermelon w) {
        watermelon = w;
        String welcomeMessage = watermelon.getWelcomeMessage();
        dialogContainer.getChildren().addAll(DialogBox.getWatermelonDialog(welcomeMessage, watermelonImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Watermelon's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "input should not be null";
        String response = watermelon.getResponse(input);
        assert response != null : "response should not be null";

        if (response.startsWith("Error:") || response.startsWith("Invalid")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getErrorDialog(response, watermelonImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getWatermelonDialog(response, watermelonImage)
            );
        }

        userInput.clear();

        // Used Claude to generate following code to close the program
        // Close program after showing goodbye message
        if (input.equals("bye")) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> Platform.exit()));
            timeline.play(); // Wait 1 second then close
        }
    }
}
