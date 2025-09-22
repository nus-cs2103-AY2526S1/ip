package Clam;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ui.DialogBox;

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

    private Clam clam;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image clamImage = new Image(this.getClass().getResourceAsStream("/images/clam.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Clam instance */
    public void setClam(Clam c) {
        clam = c;
    }

    /**
     * Displays the user's chat with Clam via {@link DialogBox}s. The user input is retrieved from the text box
     * and a response is obtained from the {@link Clam} object, which is then displayed in the GUI.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = clam.getResponse(input);
        if(response.startsWith("Bye.")) {
            Platform.exit();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getClamDialog(response, clamImage)
        );
        userInput.clear();
    }

    /**
     * Gets the relevant message from initialising the {@link Clam} object and displays it in the GUI dialog.
     */
    public void startup() {
        String init = clam.init();

        dialogContainer.getChildren().add(
                DialogBox.getClamDialog(init, clamImage)
        );
    }
}
