package junny.Ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import junny.Junny;

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

    private Junny junny;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/PotatoUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/XinxinJunny.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setJunny(Junny j) {
        junny = j;

        // show greeting first
        j.getUi().printHi();
        String greeting = j.getUi().currectMsg();
        dialogContainer.getChildren().add(
                DialogBox.getJunnyDialog(greeting, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = junny.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        if (!junny.getLastResponseState()) {
            dialogContainer.getChildren().add(DialogBox.getErrorDialog(response, dukeImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getJunnyDialog(response, dukeImage));
        }
        userInput.clear();
    }
}
