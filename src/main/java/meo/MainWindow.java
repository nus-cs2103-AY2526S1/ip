package meo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import meo.ui.Ui;
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

    private Meo meo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image meoImage = new Image(this.getClass().getResourceAsStream("/images/meo.png"));


    @FXML
    public void initialize() {
        Ui ui = new Ui();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = ui.showWelcomeMessage();
        String prompt = ui.showCommandPrompt();
        dialogContainer.getChildren().addAll(
                DialogBox.getMeoDialog(welcomeMessage, meoImage),
                DialogBox.getMeoDialog(prompt, meoImage)
        );
    }

    /** Injects the Meo instance */
    public void setMeo(Meo m) {
        meo = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Meo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        boolean isSent = false;
        String input = userInput.getText();
        String response = meo.getResponse(input);
        isSent = dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMeoDialog(response, meoImage)
        );
        
        if (isSent && input.equals("bye")) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
        }

        userInput.clear();
    }
}
