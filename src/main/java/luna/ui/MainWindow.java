package luna.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import luna.command.Command;
import luna.exception.LunaException;

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

    private Luna luna;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image lunaImage = new Image(this.getClass().getResourceAsStream("/images/DaLuna.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Luna instance
     */
    public void setLuna(Luna l) {
        luna = l;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(luna.getWelcomeMessage(), lunaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Luna's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            Command command = Parser.parse(input);
            response = luna.getResponse(command);
            if (command.isExit()) {
                Platform.exit();
            }
        } catch (LunaException e) {
            response = "OOPS!!! " + e.getMessage();
        }

        assert response != null;
        assert userImage != null;
        assert lunaImage != null;
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, lunaImage)
        );
        userInput.clear();
    }
}
