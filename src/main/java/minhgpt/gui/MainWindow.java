package minhgpt.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import minhgpt.MinhGpt;
import minhgpt.ui.Ui;

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

    private MinhGpt minhgpt;

    /** Image for user dialog */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.gif"));
    /** Image for program dialog */
    private Image programImage = new Image(this.getClass().getResourceAsStream("/images/program.gif"));

    /**
     * Initialise the window.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(DialogBox.getProgramDialog(Ui.welcomeMessage(), programImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the MinhGpt instance */
    public void setMinhGpt(MinhGpt minhgpt) {
        this.minhgpt = minhgpt;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * MinhGpt's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = minhgpt.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getProgramDialog(response, programImage));
        userInput.clear();
    }
}
