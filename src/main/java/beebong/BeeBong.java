package beebong;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

// Import Commands
import beebong.command.Command;
// Import Exceptions
import beebong.exception.BBongException;
// Import Parser
import beebong.parser.Parser;
// Import Storage
import beebong.storage.Storage;
// Import Tasks
import beebong.task.TaskList;
// Import UI
import beebong.ui.UI;

/**
 * Represents my chatbot
 */
public class BeeBong {
    private TaskList taskList;
    private final Storage storage;
    private final UI ui;
    private final Parser parser;

    /**
     * Constructs a new BeeBong chatbot.
     */
    public BeeBong() {
        this.ui = new UI();
        this.storage = new Storage("bbongSave.txt");
        this.parser = new Parser();
    }

    /**
     * Sets the dialog container, user image and bot image for the UI object.
     */
    public void setupDialogArea(VBox container, Image userImage, Image botImage) {
        this.ui.setDialogContainer(container);
        if (userImage != null) {
            this.ui.setUserImage(userImage);
        }
        if (botImage != null) {
            this.ui.setBotImage(botImage);
        }
    }

    /**
     * Sets the user input text field and send button for the UI object.
     */
    public void setupUserInputFields(TextField textField, Button button) {
        this.ui.setUserInputField(textField);
        this.ui.setSendButton(button);
    }

    /**
     * Initializes the user's session by displaying the initial messages (e.g. greeting message)
     */
    public void initSession() {
        this.ui.printGreetingMessage();

        // Check for Saved Data
        try {
            this.taskList = new TaskList(this.storage.readTasksFromFile());
            this.ui.printBotMessage("Bing! Saved Tasks found, loading saved tasks...");
        } catch (BBongException e) {
            this.ui.printBotErrorMessage(e.getMessage());
            this.taskList = new TaskList();
        }

        // Make sure taskList not null
        assert this.taskList != null : "TaskList is empty";
    }

    public void exitSession(String message) {
        this.ui.printBotExitMessage(message);
        // Disable User Input
        this.ui.disableUserInput();
        // Start shutdown timer
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * Parses a user's input using the chatbot's parser.
     *
     * @param input the user's input string.
     */
    public void parseUserInput(String input) {
        // Show user's input as message
        this.ui.printUserMessage(input);
        // Parse the user's input as a command
        try {
            Command command = parser.parseCommand(input);
            assert command != null : "Command is NULL";

            String res = command.execute(this.taskList, this.storage);
            if (command.isExit()) {
                exitSession(res);
            } else {
                this.ui.printBotMessage(res);
            }
        } catch (BBongException e) {
            this.ui.printBotErrorMessage(e.getMessage());
        }
    }
}
