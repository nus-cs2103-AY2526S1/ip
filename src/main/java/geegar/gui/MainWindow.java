package geegar.gui;

import geegar.Geegar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * This code was extracted from the example given during the guide
 * for building a dialogBox from the cs2103t website
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

    private Geegar geegar;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image geegarImage = new Image(this.getClass().getResourceAsStream("/images/DaGeegar.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setGeegar(Geegar g) {
        geegar = g;
        showWelcomeMessage();
    }

    /**
     * Shows the welcome message
     */
    public void showWelcomeMessage() {
        String welcomeMessage = geegar.getWelcomeMessage();
        dialogContainer.getChildren().add(
                DialogBox.getGeegarDialog(welcomeMessage, geegarImage)
        );
    }

    /**
     * Creates two dialog boxes.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = geegar.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGeegarDialog(response, geegarImage)
        );
        userInput.clear();
    }
}
