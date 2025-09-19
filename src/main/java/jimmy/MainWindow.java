package jimmy;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import jimmy.exception.JimmyException;

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

    private Jimmy jimmy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image jimmyImage = new Image(this.getClass().getResourceAsStream("/images/Jimmy.png"));

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Show the welcome message
        String welcome = "Hey! I'm Jimmy.\nWhat can I do for you?";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcome, jimmyImage)
        );
    }

    /** Injects the Jimmy instance */
    public void setJimmy(Jimmy j) {
        assert j != null;
        jimmy = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws JimmyException {
        String input = userInput.getText();

        if (input.equalsIgnoreCase("bye")) {
            // Create a pause transition for 2 seconds delay
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
            });
            delay.play();
        }
        String response = jimmy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, jimmyImage)
        );
        userInput.clear();
    }
}

