package marcus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

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

    private Marcus marcus;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/right.png"));
    private Image marcusImage = new Image(this.getClass().getResourceAsStream("/images/left.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Marcus instance.
     *  Creates two dialog boxes, one for a welcome message, one to request input from user.
     */
    public void setMarcus(Marcus m) {
        marcus = m;

        // Marcus sends a greeting at startup and requests for user input
        dialogContainer.getChildren().addAll(
                DialogBox.getMarcusDialog(marcus.getWelcome(), marcusImage),
                DialogBox.getMarcusDialog(marcus.requestAction(), marcusImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Marcus's reply and then appends them to
     * the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = marcus.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMarcusDialog(response, marcusImage)
        );

        userInput.clear();

        // Closes the window if user types "bye"
        if (marcus.getIsExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }
    }
}

