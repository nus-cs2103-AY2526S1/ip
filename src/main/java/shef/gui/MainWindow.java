package shef.gui;

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
import shef.Shef;
import shef.command.Command;
import shef.command.ExitCommand;
import shef.parser.Parser;

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

    private Shef shef;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gigachad.png"));
    private Image shefImage = new Image(this.getClass().getResourceAsStream("/images/shef.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDudeDialog(Shef.getHelloMessage(), shefImage)
        );
    }

    /** Injects the Shef instance */
    public void setShef(Shef s) {
        shef = s;
    }

    /**
     * Creates two dialog boxes, one for user input and the other for Shef's reply.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command cmd = Parser.parse(input);
        String response = shef.getResponse(cmd);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDudeDialog(response, shefImage)
        );
        userInput.clear();

        if (cmd.isExit()) {
            assert cmd instanceof ExitCommand : "Command is instance of ExitCommand when exiting.";

            // Let user read message before closing
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
