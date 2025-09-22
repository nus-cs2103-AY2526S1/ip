package jerome.gui;

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
import jerome.Jerome;
import jerome.ui.Ui;

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

    private Jerome jerome;
    private Ui ui = new Ui();
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/MonkeSponge.jpg"));
    private Image jeromeImage = new Image(this.getClass().getResourceAsStream("/images/funnyMonke.png"));

    @FXML
    public void initialize() {
        scrollPane.setFitToWidth(true);
    }

    /** Injects the Duke instance */
    public void setJerome(Jerome j) {
        jerome = j;
        dialogContainer.getChildren().add(
                DialogBox.getJeromeDialog(ui.welcomeText(), jeromeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jerome.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        if (response.startsWith("Error!")) {
            dialogContainer.getChildren().add(DialogBox.getErrorDialog(response, jeromeImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getJeromeDialog(response, jeromeImage));
        }

        userInput.clear();

        Platform.runLater(() -> scrollPane.setVvalue(1.0));

        // If the user typed "bye", exit the application
        if (response.contains("Bye")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> Platform.exit()); // Exit the app after 2 seconds
            pause.play();
        }
    }
}

