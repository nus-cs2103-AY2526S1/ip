package khat;

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

    private Khat khat;

    private Image cinaImage = new Image(this.getClass().getResourceAsStream("/images/cina.png"));
    private Image lbxxImage = new Image(this.getClass().getResourceAsStream("/images/lbxx.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getKhatDialog("Hello! I'm Khat.\n"
                        + "Start keeping track of all your tasks by sending a short command!", lbxxImage)
        );
    }

    /** Injects the Duke instance */
    public void setKhat(Khat k) {
        assert k != null : "Khat instance should not be null";
        khat = k;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = khat.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, cinaImage),
                DialogBox.getKhatDialog(response, lbxxImage)
        );
        userInput.clear();
    }
}
