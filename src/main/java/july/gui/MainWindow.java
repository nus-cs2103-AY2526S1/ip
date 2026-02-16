package july.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import july.July;
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

    private July july;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/main.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/z2.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = "Hello I'm July, your helpful assistant";
        dialogContainer.getChildren().add(
            DialogBox.getJulyDialog(greeting, dukeImage)
        );
    }

    /** Injects the July instance */
    public void setJuly(July j) {
        july = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = july.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJulyDialog(response, dukeImage)
        );
        userInput.clear();

        if (july.shouldExit()) {
            javafx.application.Platform.exit();
        }
    }
}
