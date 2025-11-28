package penguin.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import penguin.app.Penguin;

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

    private Penguin penguin;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaPenguin.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Penguin instance */
    public void setPenguin(Penguin p) {
        penguin = p;
        penguinSays(penguin.setUp());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.equals("bye")) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
            return;
        }

        String response = penguin.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getPenguinDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Creates one dialog box, containing Penguin's reply
     * @param response Penguin's response
     */
    @FXML
    private void penguinSays(String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getPenguinDialog(response, dukeImage)
        );
    }
}

