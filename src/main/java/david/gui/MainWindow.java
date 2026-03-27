package david.gui;

import david.David;
import david.command.Command;
import david.exception.DavidException;
import david.parser.Parser;
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

    private David david;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image davidImage = new Image(this.getClass().getResourceAsStream("/images/DaDavid.png"));

    @FXML
    public void initialize() {
        assert userImage != null : "User Image should never be null";
        assert davidImage != null : "David Image should never be null";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("    Eh David here\n    What you want me do for you?", davidImage)
        );
    }

    /** Injects the David instance */
    public void setDavid(David d) {
        david = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            Command c = Parser.parse(input, david.getHistory());
            String response = c.execute(david.getTaskList(), david.getStorage());
            david.getHistory().add(c);

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, davidImage)
            );
            userInput.clear();

            if (c.isExit()) {
                javafx.application.Platform.exit();
            }
        } catch (DavidException e) {
            String input = userInput.getText();

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(e.getMessage(), davidImage)
            );
            userInput.clear();
        }

    }
}
