package jinbot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jinbot.JinBot;

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

    private JinBot jinbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image jinbotImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the JinBot instance */
    public void setJinBot(JinBot j) {
        jinbot = j;

        // Show greeting when chatbot starts
        String greeting = "Hello! I'm JinBot\nWhat can I do for you?";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greeting, jinbotImage)
        );
    }

    /**
     * Creates two dialog boxes, one is user input and the other containing JinBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jinbot.getResponse(input);
        boolean isError = response.startsWith("OOPS") || response.contains("Error");

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                isError
                        ? DialogBox.getErrorDialog(response, jinbotImage)
                        : DialogBox.getDukeDialog(response, jinbotImage)
        );
        userInput.clear();
    }
}

