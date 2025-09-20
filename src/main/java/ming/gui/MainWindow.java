package ming.gui;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ming.app.Ming;

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

    private Ming ming;

    private String commandType = "";

    private Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/Screenshot 2025-09-02 at 12.41.30 AM.png"));
    private Image mingImage =
            new Image(this.getClass().getResourceAsStream("/images/Screenshot 2025-09-02 at 12.42.34 AM.png"));
    //Import images

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcome = "Hello! I'm Ming! What can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getMingDialog(welcome, mingImage, commandType)
        );
    }

    /**
     * Injects the Ming instance
     */
    public void setMing(Ming ming) {
        this.ming = ming;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ming's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ming.getResponse(input);
        commandType = ming.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMingDialog(response, mingImage, commandType)
        );
        userInput.clear();
    }
}