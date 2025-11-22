package apunable.ui;

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

    private ApunableBot apunable;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaDave.jpg"));
    private Image apunableImage = new Image(this.getClass().getResourceAsStream("/images/DaApunable.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Apunable instance */
    public void setApunable(ApunableBot d) {
        apunable = d;

        dialogContainer.getChildren().add(
                DialogBox.getApunableDialog(apunable.getWelcomeMessage(), apunableImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Apuanble's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = apunable.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getApunableDialog(response, apunableImage)
        );
        userInput.clear();
    }
}
