package salah;

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
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private Salah salah;

    private final Image userImage  = new Image(this.getClass().getResourceAsStream("/images/MarcusImage.png"));
    private final Image salahImage = new Image(this.getClass().getResourceAsStream("/images/SalahImage.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Salah instance */
    public void setSalah(Salah s) {
        this.salah = s;
        dialogContainer.getChildren().add(
            DialogBox.getSalahDialog(salah.getGreeting(), salahImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Salah's reply; then clears input. Exits app if 'bye' was processed.
     */
    @FXML
    private void handleUserInput() {
        final String input = userInput.getText();
        if (input == null || input.isBlank()) {
            return;
        }
        final String response = salah.getResponse(input);

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getSalahDialog(response, salahImage)
        );
        userInput.clear();

        // Exit if bye was handled (Salah saved state internally)
        if (salah.isExitRequested()) {
            // 1) lock the UI so no more input
            userInput.setDisable(true);
            sendButton.setDisable(true);
            // 2) wait 2 seconds, then exit
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }
}
