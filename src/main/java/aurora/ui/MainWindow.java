package aurora.ui;

import aurora.Aurora;
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

    private Aurora aurora;

    private final Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/npc.jpg"));
    private final Image dukeImage = new Image(
            this.getClass().getResourceAsStream("/images/Aurora.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Aurora instance */
    public void setAurora(Aurora a) {
        aurora = a;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = aurora.getResponse(input);
        String commandType = aurora.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAuroraDialog(response, dukeImage, commandType)
        );
        userInput.clear();
    }
}
