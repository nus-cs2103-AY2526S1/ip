package Note.GUI;

import Note.ui.Note;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * Controller for MainWindow.fxml.
 * Handles user input and displays dialog messages using DialogBox.
 */
public class MainWindow {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Note note; // Note logic

    private Image userImage;
    private Image botImage;

    /**
     * Initializes the controller.
     * Automatically called after FXML is loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setOnAction(event -> handleUserInput());
        sendButton.setOnAction(event -> handleUserInput());

        userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
        botImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

        if (userImage == null) {
            userImage = new Image("https://via.placeholder.com/50");
        }
        if (botImage == null) {
            botImage = new Image("https://via.placeholder.com/50");
        }

    }

    /**
     * Sets the Note logic instance.
     */
    public void setNote(Note note) {
        this.note = note;
    }

    /**
     * Handles user input, displays it, gets response from Note,
     * and adds both messages to the dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.isBlank()) return;

        // show user dialog
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        try {
            // process input via Note
            String response = note.processInput(input);
            dialogContainer.getChildren().add(DialogBox.getBotDialog(response, botImage));
        } catch (IllegalArgumentException e) {
            // show bot response for invalid date/time or other bad input
            dialogContainer.getChildren().add(DialogBox.getBotDialog(
                    "Oops! Invalid date and time format. Use d/M/yyyy HHmm, e.g., 2/12/2019 1800.", botImage));
        } catch (Exception e) {
            // generic fallback for unexpected exceptions
            dialogContainer.getChildren().add(DialogBox.getBotDialog(
                    "Something went wrong: " + e.getMessage(), botImage));
        }

        userInput.clear();
    }

}
