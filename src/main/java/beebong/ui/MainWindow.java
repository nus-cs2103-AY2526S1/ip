package beebong.ui;

import beebong.BeeBong;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private BeeBong bot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/Beebong.jpg"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /** Injects the BeeBong instance */
    public void setBot(BeeBong b) {
        this.bot = b;
        // Pass the UI references to the UI class
        this.bot.setupDialogArea(this.dialogContainer, userImage, botImage);
        this.bot.setupUserInputFields(this.userInput, this.sendButton);
        // Display initial messages
        this.bot.initSession();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        // Get input from TextBox
        String input = this.userInput.getText().trim();;
        // Make sure input is not null
        if (input.isEmpty()) {
            return;
        }
        // Pass input into chatbot
        this.bot.parseUserInput(input);
        this.userInput.clear();
    }
}
