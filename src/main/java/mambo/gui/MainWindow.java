package mambo.gui;

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
import mambo.Mambo;

/**
 * Controller for the main GUI.
 *
 * @author kentalim2
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

    private Mambo mambo;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image mamboImage = new Image(this.getClass().getResourceAsStream("/images/Mambo.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Mambo instance
     */
    public void setMambo(Mambo m) {
        mambo = m;
        dialogContainer.getChildren().add(DialogBox.getMamboDialog(mambo.getWelcome(), mamboImage, ""));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * If user input is equals to "bye" exit the application.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mambo.getResponse(input);
        String commandType = mambo.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMamboDialog(response, mamboImage, commandType)
        );
        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            Platform.runLater(() -> {
                // small delay to ensure message is rendered
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> Main.exitApplication());
                delay.play();
            });
        }
    }

}
