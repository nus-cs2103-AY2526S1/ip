package yappy.ui;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private Button sendButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Yappy yappy;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/DaUser.jpeg")));
    private final Image yappyImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/DaYappy.jpeg")));

    /**
     * Initializes the main window, binds the scroll pane to the dialog container height,
     * and adds a welcome message from Yappy.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getYappyDialog("Hello! I'm Yappy. How can I help you today?", yappyImage)
        );
    }

    /** Injects the Duke instance */
    public void setYappy(Yappy d) {
        yappy = d;
    }

    /**
     * Exits the application after a 2-second delay.
     */
    private void exitAfterDelay() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        AudioClip clickSound = new AudioClip(Objects.requireNonNull(getClass().getResource(
                "/sounds/sent.mp3")).toExternalForm());
        clickSound.play();
        String input = userInput.getText();

        String response = yappy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYappyDialog(response, yappyImage)
        );
        // System.out.println(response);
        userInput.clear();
        if (input.equals("bye")) {
            exitAfterDelay();
        }
    }
}
