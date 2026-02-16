package LunarBot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    private LunarBot lunarbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/LunarBot/images/user.png"));
    private Image lunarImage = new Image(this.getClass().getResourceAsStream("/LunarBot/images/lunar.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the LunarBot instance */
    public void setLunar(LunarBot l) {
        lunarbot = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lunarbot.getResponse(input);
        if (response.equals("Hope to see you again!")) {
            lunarbot.quit();
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLunarDialog(response, lunarImage)
        );
        userInput.clear();
    }
}