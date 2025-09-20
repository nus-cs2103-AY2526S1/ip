package pickle.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pickle.chat.Pickle;

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

    private Pickle pickle;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image pickleImage = new Image(this.getClass().getResourceAsStream("/images/Pickle.png"));

    /**
     * Initialize Pickle.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getPickleDialog("Good Morning! I'm Pickle\n" + "What can I do for you?", pickleImage)
        );
    }

    /** Injects the Pickle instance */
    public void setPickle(Pickle d) {
        pickle = d;
        assert pickle != null : "Pickle not successfully loaded.";
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Pickle's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String response = pickle.getResponse(input);
        String[] seperated = response.split("!", 2);
        if (seperated[0].equals("Yikes")) {
            DialogBox errorBox = DialogBox.getPickleDialog(response,
                    pickleImage
            );
            // Add the "error" CSS class so it picks up your red style
            errorBox.lookup(".label").getStyleClass().add("error");

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    errorBox
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getPickleDialog(response, pickleImage) // normal bot reply
            );
        }
        userInput.clear();
    }
}
