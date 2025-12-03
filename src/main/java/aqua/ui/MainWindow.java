package aqua.ui;

import aqua.Aqua;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Aqua aqua;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image aquaImage = new Image(this.getClass().getResourceAsStream("/images/Aqua.png"));

    /**
     * Initializes the main window, setting up the scroll pane and displaying the greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getAquaDialog(Aqua.greet(), aquaImage)
        );
        scrollPane.viewportBoundsProperty().addListener((obs, oldVal, newVal) -> {
            dialogContainer.setPrefWidth(newVal.getWidth());
        });
    }

    /**
     * Injects the Aqua instance
     * @param s The Aqua instance
     */
    public void setAqua(Aqua s) {
        aqua = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Aqua's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = aqua.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAquaDialog(response, aquaImage)
        );
        userInput.clear();
    }
}
