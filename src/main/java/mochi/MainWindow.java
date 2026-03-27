package mochi;

import javafx.application.Platform;
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

    private Mochi mochi;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserIcon.png"));
    private final Image mochiImage = new Image(this.getClass().getResourceAsStream("/images/MochiIcon.png"));

    /**
     * Initialize the scrollPane with the welcome message node.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getMochiDialog("""
             Hello! I'm your personalized assistant,
             __  __            _     _
            |  \\/  | ___   ___| |__ (_)
            | |\\/| |/ _ \\ / __| '_ \\| |
            | |  | | (_) | (__| | | | |
            |_|  |_|\\___/ \\___|_| |_|_| \n
             Type 'help' to begin!
             """, mochiImage)
        );
    }

    /** Injects the Mochi instance */
    public void setMochi(Mochi d) {
        this.mochi = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Mochi's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mochi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMochiDialog(response, mochiImage)
        );
        if (response.contains("Bye. Hope to see you again soon!")) {
            Platform.exit();
        }
        userInput.clear();
    }
}
