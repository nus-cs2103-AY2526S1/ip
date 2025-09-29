package floydai.ui;

import java.io.IOException;

import floydai.FloydAI;
import floydai.FloydException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the FloydAI application.
 * <p>
 * Responsible for handling user interactions, managing the dialog container,
 * and coordinating between the {@link FloydAI} backend and the user interface.
 * </p>
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    /**
     * The core FloydAI instance used to generate responses.
     */
    private FloydAI floyd;

    /**
     * Image representing the user in dialog boxes.
     */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/floyd.png"));

    /**
     * Image representing FloydAI in dialog boxes.
     */
    private Image floydImage = new Image(this.getClass().getResourceAsStream("/images/police.png"));

    /**
     * Initializes the main window after the FXML components are loaded.
     * <p>
     * - Binds the vertical scroll value of the scroll pane to the height of the dialog container,
     *   ensuring the view scrolls automatically to the latest dialog.
     * - Adds an initial greeting dialog from FloydAI.
     * </p>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the {@link FloydAI} instance into the controller.
     *
     * @param f the FloydAI instance to use
     */
    public void setFloyd(FloydAI f) {
        floyd = f;
    }

    /**
     * Handles user input events.
     * <p>
     * - Retrieves text from the input field. <br>
     * - Generates FloydAI's response using {@link FloydAI#handleInput(String)}. <br>
     * - Creates dialog boxes for both user input and FloydAI's reply,
     *   and appends them to the dialog container. <br>
     * - Clears the user input field afterward.
     * </p>
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        try {
            floyd.handleInput(input);
        } catch (FloydException | IOException e) {
            dialogContainer.getChildren().addAll(
                DialogBox.getErrorDialog(e.getMessage(), floydImage)
            );
        }
        userInput.clear();
    }

    /**
     * Appends a dialog box with FloydAI's response to the dialog container.
     *
     * @param message The message to display
     */
    public void addFloydDialog(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getFloydDialog(message, floydImage)
        );
    }
}
