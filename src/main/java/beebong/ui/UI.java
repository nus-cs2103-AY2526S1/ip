package beebong.ui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Handles the display of messages and UI-related output for the BeeBong chatbot.
 */
public class UI {
    public static String commands = """
                    List - lists out all tasks
                    Find [keyword] - lists out all tasks whose name contains the keyword
                    Mark [task no.] - mark the task with the given number as completed
                    Unmark [task no.] - mark the task with the given number as incomplete
                    Delete [task no.] - removes a task from the list
                    Help - shows full command list
                    Bye - exit
                    (Enter Dates using this format: DD/MM/YYYY hh:mm, time is optional)
                    Enter a new Task name or Command""";
    private VBox dialogContainer;
    private Image userImage;
    private Image botImage;
    private TextField userInput;
    private Button sendButton;

    public void setDialogContainer(VBox container) {
        this.dialogContainer = container;
    }

    public void setUserImage(Image image) {
        this.userImage = image;
    }

    public void setBotImage(Image image) {
        this.botImage = image;
    }

    public void setUserInputField(TextField textField) {
        this.userInput = textField;
    }

    public void setSendButton(Button button) {
        this.sendButton = button;
    }

    /**
     * Creates a new {@link DialogBox} with the input text as a user message
     * and adds it to the dialog container.
     *
     * @param message the text to display.
     */
    public void printUserMessage(String message) {
        this.dialogContainer.getChildren().add(DialogBox.getUserDialog(message, this.userImage));
    }

    /**
     * Creates a new {@link DialogBox} with the input text as a chatbot message
     * and adds it to the dialog container.
     *
     * @param messages the text(s) to display.
     */
    public void printBotMessage(String... messages) {
        StringBuilder res = new StringBuilder();
        for (String s : messages) {
            res.append(s).append("\n");
        }
        this.dialogContainer.getChildren().add(DialogBox.getBotDialog(res.toString(), this.botImage, "BotResponse"));
    }

    /**
     * Creates a new {@link DialogBox} with the input text as a chatbot error message
     * and adds it to the dialog container.
     *
     * @param message the text to display.
     */
    public void printBotErrorMessage(String message) {
        this.dialogContainer.getChildren().add(DialogBox.getBotDialog("Bong Alert! - " + message, this.botImage, "ErrorResponse"));
    }

    /**
     * Creates a new {@link DialogBox} with the input text as a chatbot's exit message
     * and adds it to the dialog container.
     *
     * @param message the text to display.
     */
    public void printBotExitMessage(String message) {
        this.dialogContainer.getChildren().add(DialogBox.getBotDialog(message, this.botImage, "ExitResponse"));
    }

    /**
     * Displays the chatbot's greeting message.
     */
    public void printGreetingMessage() {
        String res = "Ding Dong! Guess who? It’s B. Bong! How can I bong your day brighter?\n\n";
        this.printBotMessage(res + UI.commands);
    }

    /**
     * Disables the user input fields.
     */
    public void disableUserInput() {
        this.userInput.setDisable(true);
        this.sendButton.setDisable(true);
    }

    /**
     * Enables the user input fields.
     */
    public void enableUserInput() {
        this.userInput.setDisable(false);
        this.sendButton.setDisable(false);
    }
}
