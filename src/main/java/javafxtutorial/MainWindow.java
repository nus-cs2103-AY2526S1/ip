package javafxtutorial;

import dukeychatbot.Dukey;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Dukey dukey;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/TonyStark.png"));
    private Image dukeyImage = new Image(this.getClass().getResourceAsStream("/images/Pikachu.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDukey(Dukey d) {
        dukey = d;

        dialogContainer.getChildren().add(
                DialogBox.getDukeyDialog(dukey.welcome(), dukeyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dukey.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeyDialog(response, dukeyImage)
        );

        if (input.trim().equalsIgnoreCase("bye")) {
            // Close the entire stage after 5 seconds
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(event -> {
                javafx.application.Platform.exit();
            });
            delay.play();
        }

        userInput.clear();
    }
}
