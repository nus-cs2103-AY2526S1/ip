
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import rafayel.Rafayel;
import rafayel.RafayelException;


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

    private Rafayel rafayel;

    private final Image userIcon = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/UserIcon.png")));
    private final Image rafayelIcon = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/RafayelIcon.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Rafayel instance */
    public void setRafayel(Rafayel r) {
        rafayel = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws RafayelException {
        String input = userInput.getText();
        String response = rafayel.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userIcon),
                DialogBox.getRafayelDialog(response, rafayelIcon));
        userInput.clear();
    }
}
