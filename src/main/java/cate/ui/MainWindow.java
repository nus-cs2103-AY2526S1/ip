package cate.ui;

import cate.exception.CateException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the Cate application.
 * Handles user input, displays messages, and manages the chat dialog container.
 */
public class MainWindow extends AnchorPane {
    private static final String STARTUP_MESSAGE =
            "Hello! I'm Cate\n"
                    + "What can I do for you?\n";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Cate cate;
    private Image cateImage = new Image(this.getClass().getResourceAsStream("/images/Cate.jpeg"));

    /**
     * Initializes the GUI after FXML loading.
     * Binds the scroll pane to the height of the dialog container and
     * displays the initial startup message from Cate.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getCateDialog(STARTUP_MESSAGE, cateImage)
        );
    }

    /**
     * Injects the Cate instance for processing user commands.
     *
     * @param d the Cate instance to use
     */
    public void setCate(Cate d) {
        cate = d;
    }

    /**
     * Handles user input when the send button is pressed or Enter is hit.
     * Creates dialog boxes for the user's message and Cate's response (or error),
     * appends them to the dialog container, and clears the input field.
     * Exits the application if Cate signals termination.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = cate.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input),
                    DialogBox.getCateDialog(response, cateImage)
            );
        } catch (CateException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input),
                    DialogBox.getErrorDialog(e.getMessage(), cateImage)
            );
        }
        userInput.clear();

        if (cate.isExit()) {
            javafx.application.Platform.exit();
        }
    }
}
