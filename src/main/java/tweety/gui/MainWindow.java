package tweety.gui;

import javafx.animation.PauseTransition;

import javafx.application.Platform;

import javafx.util.Duration;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import tweety.Tweety;

import tweety.ui.GuiUi;

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

    private Tweety tweety;

    private GuiUi guiUi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/sylvester.png"));
    private Image tweetyImage = new Image(this.getClass().getResourceAsStream("/images/tweety.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Tweety instance */
    public void setTweety(Tweety t) {
        tweety = t;

        guiUi = new GuiUi();  // Initialize GuiUi instance
        guiUi.printWelcomeMessage();
        String welcomeMessage = guiUi.getResponse();

        dialogContainer.getChildren().add(
                DialogBox.getTweetyDialog(welcomeMessage, tweetyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tweety's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tweety.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTweetyDialog(response, tweetyImage)
        );
        userInput.clear();

        if (tweety.shouldExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
