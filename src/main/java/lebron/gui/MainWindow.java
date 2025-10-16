package lebron.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lebron.common.Constants;
import lebron.main.LeBron;

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

    private LeBron lebron;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image leBronImage = new Image(this.getClass().getResourceAsStream("/images/LeBron.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the LeBron instance */
    public void setLeBron(LeBron d) {
        lebron = d;

        // Show welcome message when GUI opens
        dialogContainer.getChildren().add(
                DialogBox.getLeBronDialog(Constants.UI_WELCOME, leBronImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing LeBron's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lebron.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLeBronDialog(response, leBronImage)
        );
        userInput.clear();
    }
}
