package bytebot;

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
    }

    public void setImages(Image userImage, Image byteImage) {
        this.userImage = userImage;
        this.byteImage = byteImage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ByteBot's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = byteBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getByteDialog(response, byteImage)
        );
        userInput.clear();
    }
}


