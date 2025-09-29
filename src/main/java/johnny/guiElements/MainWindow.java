package johnny.guielements;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import johnny.bot.Johnny;

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

    private Johnny johnny;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/agni.png"));
    private Image johnnyImage = new Image(this.getClass().getResourceAsStream("/images/johnny.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the johnny instance */
    public void setJohnny(Johnny j) {
        johnny = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * johnny's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert johnny != null : "Johnny cannot be null";
        String input = userInput.getText();
        String response = johnny.generateResponse(input);
        assert input != null : "User input cannot be null";
        assert response != null : "Johnny's reponse cannot be null";
        assert !response.isEmpty() : "Johnny's response cannot be null";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJohnnyDialog(response, johnnyImage));
        userInput.clear();
    }

    @FXML
    public void greeting() {
        assert johnny != null : "Johnny cannot be null";
        String greeting = johnny.greeting();
        dialogContainer.getChildren().addAll(
                DialogBox.getJohnnyDialog(greeting, johnnyImage));
        userInput.clear();
    }

    @FXML
    public void showError(String msg) {
        dialogContainer.getChildren().add(
                DialogBox.getJohnnyDialog(msg, johnnyImage));
    }
}
