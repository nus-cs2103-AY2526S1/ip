package dyke.ui;

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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Dyke dyke;

    private Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dykeImage =
            new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Duke instance
     */
    public void setDyke(Dyke d) {
        dyke = d;

        // Show welcome message immediately
        dialogContainer.getChildren().add(DialogBox.getDykeDialog(
                dyke.welcome(), dykeImage));

        // Load tasks and show info
        String loadMessage = dyke.init();
        dialogContainer.getChildren().add(DialogBox.getDykeDialog(loadMessage, dykeImage));

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dyke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDykeDialog(response, dykeImage)
        );
        userInput.clear();

        if (!dyke.isRunning()) { // Closes Stage when exit command is given
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
            pauseTransition.setOnFinished(event -> Platform.exit());
            pauseTransition.play();
        }
    }
}
