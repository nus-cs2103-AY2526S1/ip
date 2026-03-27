package fish;

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
    private static final Duration EXIT_DELAY = Duration.seconds(1);
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Fish fish;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/xhs_azgc.jpeg"));
    private Image fishImage = new Image(this.getClass().getResourceAsStream("/xhs_blush.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Fish instance */
    public void setFish(Fish f) {
        fish = f;
        dialogContainer.getChildren().add(
                DialogBox.getFishDialog(fish.getGreeting(), fishImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = fish.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFishDialog(response, fishImage)
        );
        userInput.clear();
        if (fish.isExitRequested()) {
            disableInput();
            PauseTransition delay = new PauseTransition(EXIT_DELAY);
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

    private void disableInput() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
    }
}
