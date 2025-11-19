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
import mochibot.MochiBot;

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

    private MochiBot mochibot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image mochiBotImage = new Image(this.getClass().getResourceAsStream("/images/MochiBot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the mochibot instance */
    public void setMochiBot(MochiBot m) {
        mochibot = m;
        dialogContainer.getChildren().add(
                DialogBox.getMochiBotDialog(mochibot.displayGreeting(), mochiBotImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        String response = mochibot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMochiBotDialog(response, mochiBotImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }
}
