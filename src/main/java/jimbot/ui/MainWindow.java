package jimbot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jimbot.Jimbot;

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

    private Jimbot jimbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image jimbotImage = new Image(this.getClass().getResourceAsStream("/images/jimbot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setJimbot(Jimbot j) {
        jimbot = j;

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(UI.hello("Jimbot"), jimbotImage)
        );

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jimbot.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, jimbotImage)
        );
        userInput.clear();

        // Close GUI if special exit signal is received
        if (response.contains("\\(^O^)")) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
        }
    }
}
