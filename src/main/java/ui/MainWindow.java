package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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

    private Penny penny;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image pennyImage = new Image(this.getClass().getResourceAsStream("/images/DaPenny.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // CHANGE: Add styling classes and enhanced visual properties
        dialogContainer.getStyleClass().add("dialog-container");
        scrollPane.getStyleClass().add("chat-scroll-pane");
        userInput.getStyleClass().add("chat-input");
        sendButton.getStyleClass().add("send-button");

        // CHANGE: Force dialog container to fill width for proper alignment
        dialogContainer.setFillWidth(true);

        // CHANGE: Add placeholder text for better UX
        userInput.setPromptText("Type your message here...");
    }

    /** Injects the Penny instance */
    public void setPenny(Penny p) {
        penny = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Penny's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = penny.getResponse(input);

        // CHANGE: Create dialog boxes and set their alignment within the container
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox pennyDialog = DialogBox.getPennyDialog(response, pennyImage);

        // CHANGE: Force user dialog to align right within the VBox
        VBox.setVgrow(userDialog, javafx.scene.layout.Priority.NEVER);
        userDialog.setMaxWidth(Region.USE_COMPUTED_SIZE);

        // CHANGE: Force penny dialog to align left within the VBox
        VBox.setVgrow(pennyDialog, javafx.scene.layout.Priority.NEVER);
        pennyDialog.setMaxWidth(Region.USE_COMPUTED_SIZE);

        dialogContainer.getChildren().addAll(userDialog, pennyDialog);
        userInput.clear();
    }
}
