package jimbot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jimbot.Jimbot;
import jimbot.command.Command;

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

    private Jimbot jimbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image jimbotImage = new Image(this.getClass().getResourceAsStream("/images/jimbot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setJimbot(Jimbot j) {
        jimbot = j;
        String jimbotHi = jimbot.getResponse("hi");
        dialogContainer.getChildren().add(
                DialogBox.getJimbotDialog(jimbotHi, jimbotImage, "hi")
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jimbot.getResponse(input);
        String cmdString = input.split(" ")[0];
        Command cmd = jimbot.getCommandType();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJimbotDialog(response, jimbotImage, cmdString)
        );

        userInput.clear();

        if (cmd.isExit()) {
            javafx.animation.PauseTransition delay = new javafx.animation.PauseTransition(
                    javafx.util.Duration.millis(500));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        }
    }
}
