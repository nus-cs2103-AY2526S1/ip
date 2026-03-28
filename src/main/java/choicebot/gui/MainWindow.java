package choicebot.gui;

import choicebot.ChoiceBot;
import choicebot.ui.UI;
import javafx.fxml.FXML;
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
    private ChoiceBot choiceBot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image choiceBotImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the ChoiceBot instance */
    public void setChoiceBot(ChoiceBot choiceBot) {
        this.choiceBot = choiceBot;
        dialogContainer.getChildren().add(
                DialogBox.getChoiceBotDialog(UI.welcome(), choiceBotImage)
        );
    }

    /**
     * Handles user input, adds user and bot dialog boxes to the container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }
        String response = choiceBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChoiceBotDialog(response, choiceBotImage)
        );
        userInput.clear();
    }
}
