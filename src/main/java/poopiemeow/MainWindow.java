package poopiemeow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private PoopieMeow poopieMeow;

    private Image userImage;
    private Image dukeImage;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        loadImages();

        String welcomeMessage = "Hello! I'm PoopieMeow\nWhat can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage));
    }

    private void loadImages() {
        try {
            userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        } catch (Exception e) {
            // Create a simple placeholder image if the file doesn't exist
            userImage = createPlaceholderImage();
        }

        try {
            dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
        } catch (Exception e) {
            // Create a simple placeholder image if the file doesn't exist
            dukeImage = createPlaceholderImage();
        }
    }

    private Image createPlaceholderImage() {
        // Create a simple 1x1 pixel image as placeholder
        return new Image(
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==");
    }

    public void setPoopieMeow(PoopieMeow pm) {
        poopieMeow = pm;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = poopieMeow.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }
}
