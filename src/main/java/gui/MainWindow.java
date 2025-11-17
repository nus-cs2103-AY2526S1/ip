package gui;

import java.util.Objects;

import bara.Bara;
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
import ui.UI;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    public static final String BYE_RESPONSE = "Bye. Hope to see you again soon! Stay chill like a capybara! \uD83E\uDDA6\n";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bara bara;

    private final Image USER_IMAGE = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.gif")));
    private final Image BARA_IMAGE = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/bara.gif")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greetUser();
    }

    /** Injects the Bara instance */
    public void setBara(Bara bara) {
        this.bara = bara;
    }

    public void greetUser() {
        String greetings = UI.greet();
        dialogContainer.getChildren().addAll(
                DialogBox.getBaraDialog(greetings, BARA_IMAGE)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bara's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bara.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getBaraDialog(response, BARA_IMAGE)
        );
        if(response.equals(BYE_RESPONSE)) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> handleExit());
            delay.play();
        }
        userInput.clear();
    }



    @FXML
    private void handleExit() {
        Platform.exit();
    }
}
