package steven.gui;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import steven.Steven;

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

    private Steven steven;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/user.png")));
    private final Image stevenImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/steven.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the chatbot instance and greeting */
    public void setSteven(Steven steven) {
        this.steven = steven;
        dialogContainer.getChildren().add(DialogBox.getStevenDialog(steven.greet(), stevenImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = steven.getResponse(input);
        if (response == null) {
            return;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getStevenDialog(response, stevenImage)
        );
        userInput.clear();
        if (input.trim().equalsIgnoreCase("bye")) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
    }
}
