package phuc.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import phuc.Phuc;

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

    private Phuc phuc;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private Image phucImage = new Image(this.getClass().getResourceAsStream("/images/PhucImage.png"));

    /**
     * Initialize the scroll pane and also the greeting message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = "Hello! I'm Phuc \uD83D\uDE03\uD83D\uDD90\uFE0F\nWhat can I do for you?";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeMessage, phucImage, "greeting")
        );
    }

    /**
     * Injects the Phuc instance
     */
    public void setPhuc(Phuc phuc) {
        assert phuc != null;
        this.phuc = phuc;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Phuc's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String command = "";
        try {
            String input = userInput.getText();
            String[] parts = input.split(" ");
            command = parts[0];
            Boolean isEnd = false;

            if (input.equals("bye")) {
                isEnd = true;
            }

            String response = phuc.runForGraphicalUserInterface(input);

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, phucImage, command)
            );

            if (isEnd) {
                userInput.setDisable(true);
                sendButton.setDisable(true);

                PauseTransition delay = new PauseTransition(Duration.millis(500));
                delay.setOnFinished(e -> Platform.exit());
                delay.play();
            }
        } catch (Exception e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(e.getMessage(), phucImage, command)
            );
        } finally {
            userInput.clear();
        }
    }
}
