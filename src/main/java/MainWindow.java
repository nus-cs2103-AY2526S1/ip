import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nina.Nina;

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

    private Nina nina;

    private static String LINE = "___________________________________\n";
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image ninaImage = new Image(this.getClass().getResourceAsStream("/images/Nina.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Nina instance */
    public void setNina(Nina n) {
        nina = n;

        String greet = nina.getResponse("greet");
        dialogContainer.getChildren().add(DialogBox.getNinaDialog(greet, ninaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Nina's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = LINE + nina.getResponse(input) + "\n" + LINE;

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNinaDialog(response, ninaImage)
        );
        userInput.clear();
    }
}
