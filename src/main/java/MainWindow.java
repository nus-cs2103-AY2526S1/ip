import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Clare clare;
    private Stage stage;

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png")));
    private final Image clareImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Clare.png")));

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getClareDialog(
                "Hello dear, I am Clare!\nSo happy to see you today.\nWhat can I help?", clareImage));
    }

    /**
     * Injects the Stage instance
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /** Injects the Clare instance */
    public void setClare(Clare clare) {
        this.clare = clare;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Clare's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.split(" ")[0].equals("bye")) {
            stage.close();
            return;
        }
        String response = clare.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getClareDialog(response, clareImage)
        );
        userInput.clear();
    }
}
