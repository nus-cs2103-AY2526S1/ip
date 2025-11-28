package nailongbot.application;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nailongbot.NaiLong;

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

    private NaiLong jkbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/image/user_face.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/image/bot_face.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setJkbot(NaiLong d) {
        jkbot = d;
        String openingMsg = jkbot.getUi().getOpening();
        dialogContainer.getChildren().add(
                                DialogBox.getBotDialog(openingMsg, botImage));
    }

    /**
    * Creates two dialog boxes, one echoing user input and the other containing
    * Duke's reply and then appends them to* the dialog container. Clears the user input after processing.
    * */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }

        String response = jkbot.getResponse(input);

        // Display in GUI
        dialogContainer.getChildren().addAll(
                                DialogBox.getUserDialog(input, userImage),
                                DialogBox.getBotDialog(response, botImage));

        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }
}
