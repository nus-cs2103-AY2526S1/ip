package fatty.gui;

import fatty.Fatty;
import javafx.application.Platform;
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

    private Fatty fatty;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Fatty.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Fatty instance */
    public void setFatty(Fatty fatty) {
        this.fatty = fatty;

        //Display Welcome Message or Error Message for loading Task
        dialogContainer.getChildren().add(
                DialogBox.getFattyDialog(fatty.getStartupMessage(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = fatty.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFattyDialog(response, dukeImage)
        );

        userInput.clear();

        if (fatty.shouldExit()) {
            Platform.runLater(() -> {
                new Thread(() -> {
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException ignored) { }
                    Platform.exit();
                }).start();
            });
        }
    }
}
