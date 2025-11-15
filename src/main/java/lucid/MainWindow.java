package lucid;

// Reused from JavaFX tutorial

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Class that controls layout and handles user inputs in the GUI
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

    private Lucid lucid;

    // User image by Streamline, https://www.streamlinehq.com/emojis/download/smiling-face-with-sunglasses--31896
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));

    // Image by Nexon, retrieved from Maplestory Wiki, https://maplestorywiki.net/w/Lucid
    private Image lucidImage = new Image(this.getClass().getResourceAsStream("/images/Lucid.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects lucid instance
     * @param l lucid instance
     */
    public void setLucid(Lucid l) {
        lucid = l;
        DialogBox greeting = DialogBox.getLucidDialog(Ui.introduction(), lucidImage);
        dialogContainer.getChildren().addAll(greeting);
    }

    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String lucidText = lucid.getResponseForGui(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getLucidDialog(lucidText, lucidImage)
        );
        if (userText.equals("bye")) {
            // Pause for 1 second after displaying farewell message before closing application
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
        userInput.clear();
    }
}
