package paul.gui;

import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import paul.Paul;

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

    private Paul paul;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image paulImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Paul.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Paul instance */
    public void setPaul(Paul p) {
        paul = p;
        dialogContainer.getChildren().add(
                DialogBox.getPaulDialog(paul.getUi().greetUser(), paulImage, "Greeting")
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = paul.getResponse(input);
        String commandType = paul.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPaulDialog(response, paulImage, commandType)
        );
        userInput.clear();

        // Exit when user says bye
        if (paul.getCommandType().equals("BYE")) {
            Platform.exit();
            System.exit(0);
        }
    }
}
