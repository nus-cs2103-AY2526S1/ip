package apleasebot.ui;

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

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private APleaseBot aPleaseBot;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/APlease.png"));
    private final Image aPleaseBotImage = new Image(this.getClass().getResourceAsStream("/images/APlus.png"));

    @FXML
    private Button sendButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField userInput;
    @FXML
    private VBox dialogContainer;
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the APleaseBot instance */
    public void setBot(APleaseBot b) {
        aPleaseBot = b;
        assert aPleaseBot != null;
        String openingMsg = APleaseBot.LINE + APleaseBot.GREETING + APleaseBot.LINE;
        assert userImage.getClass().equals(Image.class);
        dialogContainer.getChildren().add(
                DialogBox.getBotDialog(openingMsg, aPleaseBotImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing APleaseBot's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = aPleaseBot.getResponse(input);
        if (response.equals(APleaseBot.LINE + APleaseBot.CLOSE + APleaseBot.LINE)) {
            userInput.setDisable(true);
            /* Intentional pause in seconds to let user read response */
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
        assert userImage.getClass().equals(Image.class);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, aPleaseBotImage)
        );
        userInput.clear();
    }
}
