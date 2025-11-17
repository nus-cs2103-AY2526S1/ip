package gui;

//gui code was taken from the GUI tutorial on https://se-education.org/guides/tutorials/javaFx.html

import bobby.Bobby;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

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

    private Bobby bobby;

    private Stage parentStage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaKid.png"));
    private Image bobbyImage = new Image(this.getClass().getResourceAsStream("/images/DaBobby.png"));



    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setBobby(Bobby b) {
        bobby = b;

    }

    public void setParentStage(Stage stage) {
        this.parentStage = stage;
    }

    @FXML
    public void startText() {
        String greeting = "Hello Bobby";
        String response = bobby.run();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(greeting, userImage),
                DialogBox.getDukeDialog(response, bobbyImage)
        );
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bobby.run(input);
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox bobbyDialog = DialogBox.getDukeDialog(response, bobbyImage);
        if (Objects.equals(response, "Goodbye...")) {
            dialogContainer.getChildren().addAll(userDialog, bobbyDialog);
            userInput.clear();

            // Close the application after a brief delay to show the goodbye message
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(e -> Platform.exit());
            pause.play();
            return;
        }
        dialogContainer.getChildren().addAll(userDialog, bobbyDialog);
        userInput.clear();


    }

}
