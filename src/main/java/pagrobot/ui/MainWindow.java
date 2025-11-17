package pagrobot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pagrobot.PagroBot;
/**
 * Acts as the Controller for the main GUI.
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

    private PagroBot pagroBot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Bot instance */
    public void setBot(PagroBot b) {
        this.pagroBot = b;
        String greet = this.pagroBot.greet();
        dialogContainer.getChildren().addAll(
            DialogBox.getBotDialog(greet, botImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing bot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pagroBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();
    }
}
