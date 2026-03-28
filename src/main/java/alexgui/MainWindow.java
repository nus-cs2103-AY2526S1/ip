package alexgui;

import alex.Alex;
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

    private Alex alex;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Sanjai.jpg"));
    private Image alexImage = new Image(this.getClass().getResourceAsStream("/images/Ale.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String greeting = "Hello, I'm Alex. What do you want from me";
        dialogContainer.getChildren().add(DialogBox.getAlexDialog(greeting, alexImage));
    }

    /** Injects the Alex instance */
    public void setAlex(Alex a) {
        alex = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = alex.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAlexDialog(response, alexImage)
        );
        userInput.clear();
    }
}
