package zell.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import zell.Parser;
import zell.exception.ZellException;
import zell.storage.Storage;
import zell.task.TaskList;

/**
 * Handles user inputs and also the outputs to the user using JavaFX
 */
public class Ui extends AnchorPane {
    // The dialog container
    @FXML
    private VBox dialogContainer;
    // The scroll pane
    @FXML
    private ScrollPane scrollPane;
    // The text field where user inputs
    @FXML
    private TextField userInput;
    // The send button for user to send inputs
    @FXML
    private Button sendButton;

    // The TaskList object
    private TaskList taskList;
    // The Storage object
    private Storage storage;
    // The Parser object
    private Parser parser;

    // The image for the Zell chatbot
    private final Image zellImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    // The image for the user
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the fields
     *
     * @param taskList The TaskList object.
     * @param storage The Storage object.
     * @param parser The Parser object.
     */
    public void setFields(TaskList taskList, Storage storage, Parser parser) {
        this.taskList = taskList;
        this.storage = storage;
        this.parser = parser;
    }

    /**
     * Display Zell messages that cannot be caught by the handler
     *
     * @param message The message for Zell.
     */
    public void showMessage(String message) {
        dialogContainer.getChildren().addAll(
                DialogBox.getZellDialog(message, zellImage)
        );
        userInput.clear();
    }

    /**
     * <p>
     * Creates two dialog boxes, one echoing user input and the other containing Zell's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     * </p>
     * <p>
     * If the user inputs the bye command we also close the application
     * </p>
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String response;
        try {
            response = parser.parseInput(input, taskList, storage);
        } catch (ZellException ze) {
            response = ze.toString();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZellDialog(response, zellImage)
        );

        userInput.clear();

        if (input.equals("bye")) {
            Platform.exit();
        }
    }
}
