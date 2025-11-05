import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import sunoo.ui.Sunoo;
import sunoo.ui.Ui;

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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/heeseung.jpg"));
    private final Image sunooImage = new Image(this.getClass().getResourceAsStream("/images/Sunoo.png"));

    /**
     * Initializes the starting window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = Ui.getGreetingMessage();
        dialogContainer.getChildren().add(
                DialogBox.getSunooDialog(greeting, sunooImage, "Welcome")
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = Sunoo.getResponse(input);
        String commandType = Sunoo.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSunooDialog(response, sunooImage, commandType)
        );
        userInput.clear();

        if (Sunoo.getShouldExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(0.5)); // delay to show exit message
            delay.setOnFinished(event -> {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
            });
            delay.play();
        }
    }
}

