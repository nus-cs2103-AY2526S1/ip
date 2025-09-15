package baymax;

import java.io.IOException;

import baymax.ui.DialogBox;
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

    private Baymax baymax;

    private Image baymaxImage = new Image(this.getClass().getResourceAsStream("/images/baymax.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/hiro.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().add(
                DialogBox.getBaymaxDialog("Hello! I am Baymax, your personal chatbot companion. "
                        + "\nI am here to help.", baymaxImage, false));
    }

    /** Injects the Baymax instance */
    public void setBaymax(Baymax baymax) {
        this.baymax = baymax;
        try {
            baymax.start();
        } catch (IOException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getBaymaxDialog("Error loading tasks: " + e.getMessage(), baymaxImage, baymax.isError)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Baymax's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = baymax.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBaymaxDialog(response, baymaxImage, baymax.isError)
        );
        baymax.isError = false;
        userInput.clear();
    }
}
