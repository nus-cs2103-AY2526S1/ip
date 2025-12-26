package Baozii;

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

    private Baozii baozii;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image baoziiImage = new Image(this.getClass().getResourceAsStream("/images/Baozii.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Baozii instance */
    public void setBaozii(Baozii b) {
        baozii = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Baozii's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = baozii.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBaoziiDialog(response, baoziiImage)
        );
        userInput.clear();
    }
}