package jinwoo.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jinwoo.Jinwoo;

/**
 * Main window of the GUI.
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

    private Jinwoo jinwoo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/chiikawa.png"));
    private Image jinwooImage = new Image(this.getClass().getResourceAsStream("/images/Sungjinwoo.png"));

    /**
     * Auto scroll down
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setJinwoo(Jinwoo j) {
        jinwoo = j;
    }

    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String jinwooText = jinwoo.getResponse(userText);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText, userImage),
                DialogBox.getJinwooDialog(jinwooText, jinwooImage));
        userInput.clear();
    }
}
