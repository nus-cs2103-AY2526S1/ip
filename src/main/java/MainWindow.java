import dwight.Dwight;
import dwight.command.CommandResponse;
import dwight.personality.PersonalityResponses;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/** Controller for the main GUI. */
public class MainWindow extends AnchorPane {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;
    private Dwight dwight;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/jim.jpeg"));
    private Image dwightImage =
            new Image(this.getClass().getResourceAsStream("/images/dwight.jpg"));

    /**
     * Initializes the controller after its root element has been completely processed. Sets up
     * scroll pane binding and displays the welcome message.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        String welcomeMsg = PersonalityResponses.WELCOME.getRandomResponse();
        DialogBox welcomeDialog = DialogBox.getDwightDialog(welcomeMsg, this.dwightImage);
        this.dialogContainer.getChildren().addAll(welcomeDialog);
    }

    /**
     * Injects the Dwight instance.
     *
     * @param d The Dwight instance to be used by this controller.
     */
    public void setDwight(Dwight d) {
        this.dwight = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Dwight's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CommandResponse response = this.dwight.getResponse(input);
        DialogBox userDialog = DialogBox.getUserDialog(input, this.userImage);
        DialogBox dwightDialog = DialogBox.getDwightDialog(response.getMessage(), this.dwightImage);
        this.dialogContainer.getChildren().addAll(userDialog, dwightDialog);
        userInput.clear();
    }
}
