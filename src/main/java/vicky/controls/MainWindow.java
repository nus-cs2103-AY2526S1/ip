package vicky.controls;

import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import vicky.main.Vicky;

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

    private Vicky vicky;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image vickyImage = new Image(this.getClass().getResourceAsStream("/images/DaVicky.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Vicky instance */
    public void setVicky(Vicky v) {
        this.vicky = v;
        assert this.vicky != null : "Vicky should be set.";
        this.handleGreet(); // shows greeting message after vicky has been initialised
    }

    /** Creates a dialog box containing Vicky's greeting to the user.
     *
     */
    @FXML
    private void handleGreet() {
        String greeting = this.vicky.getGreeting();
        dialogContainer.getChildren().addAll(
                DialogBox.getVickyDialog(greeting, vickyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = vicky.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getVickyDialog(response, vickyImage)
        );
        userInput.clear();
        if (this.vicky.isExit()) {
            this.handleExit();
        }
    }

    @FXML
    private void handleExit() {

        // Create a PauseTransition to wait for 2 seconds before closing
        PauseTransition pause = new PauseTransition(Duration.seconds(2));

        pause.setOnFinished(event -> {
            Platform.exit();
        });

        pause.play();
    }

}
