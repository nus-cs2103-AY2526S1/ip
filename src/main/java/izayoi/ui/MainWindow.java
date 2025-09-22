package izayoi.ui;

import izayoi.Izayoi;
import izayoi.logger.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Main container class for Izayoi
 */
public class MainWindow extends AnchorPane implements Logger {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Izayoi izayoi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/reimuhakurei.png"));
    private Image izayoiImage = new Image(this.getClass().getResourceAsStream("/images/sakuyaizayoi.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Izayoi instance
     * @param i the Izayoi instance
     */
    public void setIzayoi(Izayoi i) {
        izayoi = i;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        var dialog = DialogBox.getUserDialog(input, userImage);
        dialogContainer.getChildren().add(dialog);
        AnimationUtil.animateMessage(dialog);
        izayoi.runCommand(input);
        userInput.clear();
    }

    @Override
    public void log(String s) {
        var dialog = DialogBox.getDukeDialog(s, izayoiImage);
        dialogContainer.getChildren().add(dialog);
        AnimationUtil.animateMessage(dialog);
    }
}
