package pepe.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pepe.Pepe;

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

    private Pepe pepe;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pepe.png"));
    private Image pepeImage = new Image(this.getClass().getResourceAsStream("/images/pepe_sad.png"));

    /**
     * Initializes the controller.
     * Binds the scroll pane to the height of the dialog container and
     * asserts that user and Pepe images are loaded correctly.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        assert userImage != null : "User image not found at /images/pepe.png";
        assert pepeImage != null : "Pepe image not found at /images/pepe_sad.png";
    }

    /** Injects the Pepe instance */
    public void setPepe(Pepe pepe) {
        assert pepe != null : "Pepe instance should not be null";
        this.pepe = pepe;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userInput != null : "User input TextField should not be null";
        assert dialogContainer != null : "Dialog container VBox should not be null";
        assert pepe != null : "Pepe instance must be set before handling user input";
        String input = userInput.getText();

        if (input.isEmpty()) {
            // Optionally show a warning or just ignore
            return; // do nothing if input is empty
        } else {
            String response = pepe.getResponse(input);
            String commandType = pepe.getCommandType();
            assert response != null : "Pepe response should not be null";
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getPepeDialog(response, pepeImage, commandType)
            );
            userInput.clear();

            // If the user types "bye", close the window after 3 seconds
            if (input.equalsIgnoreCase("bye")) {
                javafx.animation.PauseTransition delay = new javafx
                        .animation.PauseTransition(javafx.util.Duration.seconds(3));
                delay.setOnFinished(event -> {
                    // Get the current stage and close it
                    javafx.stage.Stage stage = (javafx.stage.Stage) dialogContainer.getScene().getWindow();
                    stage.close();
                });
                delay.play();
            }
        }
    }
}

