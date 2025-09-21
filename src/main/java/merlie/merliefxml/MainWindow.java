package merlie.merliefxml;

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
import merlie.Merlie;

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

    private Merlie merlie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Singaporean.png"));
    private Image merlieImage = new Image(this.getClass().getResourceAsStream("/images/Merlie.png"));

    /**
     * Controller for the main GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Merlie instance
     */
    public void setMerlie(Merlie m) {
        merlie = m;
        String startMessage = merlie.getStartMessage();
        dialogContainer.getChildren().add(DialogBox.getMerlieDialog(startMessage, merlieImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input
     * and the other containing Merlie's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     * Closes the Merlie GUI Window when user sends Exit command
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = merlie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMerlieDialog(response, merlieImage)
        );
        userInput.clear();

        if (merlie.isExited()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);

            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
