package whchenyicn;

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

    private Whchenyicn whchenyicn;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/whchenyicn.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDuke(Whchenyicn d) {
        whchenyicn = d;
        String str = d.start();
        dialogContainer.getChildren().add(DialogBox.getWhchenyicnDialog(str, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = whchenyicn.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        //AI-assisted to differentiate error msg from normal msg
        boolean isError = response.contains("[ERROR]");
        dialogContainer.getChildren().add(
                isError
                        ? DialogBox.getErrorDialog(response, dukeImage)
                        : DialogBox.getWhchenyicnDialog(response, dukeImage)
        );

        userInput.clear();
        if (whchenyicn.isExit()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
    }
}