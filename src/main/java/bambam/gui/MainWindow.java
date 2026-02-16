package bambam.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import bambam.Bambam;

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

    private Bambam bambam;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/shinchan.png"));
    private Image bambamImage = new Image(this.getClass().getResourceAsStream("/images/hangyudon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Bambam instance */
    public void setBambam(Bambam b) {
        bambam = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bambam's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bambam.getResponse(input);
        String commandType = bambam.getCommandType();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBambamDialog(response, bambamImage, commandType)
        );

        userInput.clear();
    }
}

