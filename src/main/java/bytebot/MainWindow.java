package bytebot;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ByteBot byteBot;
    private Image userImage;
    private Image byteImage;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setByteBot(ByteBot byteBot) {
        this.byteBot = byteBot;
        if (this.byteBot != null && this.byteImage != null) {
            String greeting = this.byteBot.getGreeting();
            dialogContainer.getChildren().add(DialogBox.getByteDialog(greeting, this.byteImage));
        }
    }

    public void setAvatarImages(Image userImage, Image byteImage) {
        this.userImage = userImage;
        this.byteImage = byteImage;
        if (this.byteBot != null && this.byteImage != null) {
            String greeting = this.byteBot.getGreeting();
            dialogContainer.getChildren().add(DialogBox.getByteDialog(greeting, this.byteImage));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ByteBot's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = byteBot.getResponse(input);
        boolean isError = response != null && response.startsWith("Error: ");
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        if (isError) {
            dialogContainer.getChildren().add(DialogBox.getByteErrorDialog(response, byteImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getByteDialog(response, byteImage));
        }
        userInput.clear();
        if (input.trim().equals("bye")) {
            Platform.exit();
        }
    }
}


