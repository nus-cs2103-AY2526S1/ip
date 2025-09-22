package moon.logic;

import java.io.IOException;
import java.util.NoSuchElementException;

import moon.commands.BaseCommand;
import moon.logic.exceptions.MoonException;
import moon.models.TaskList;
import moon.parser.usercommand.UserInputParser;
import moon.storage.Storage;
import moon.ui.UiMessages;

/**
 * Main logic class of the Moon chatbot.
 * <p>
 * Passes data between the UI, storage, and the commands.
 */
public class Moon {
    private static final String DEFAULT_FILEPATH = "src/data/storage.txt";
    private final String filepath;
    private Storage storage;
    private TaskList taskList;

    /**
     * Class constructor. Creates a new Moon chatbot with the given storage file path.
     *
     * @param filepath Path to the storage file
     */
    public Moon(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Creates a new Moon chatbot using the default storage file path.
     */
    public Moon() {
        this(DEFAULT_FILEPATH);
    }

    /**
     * Generates a chatbot response for the user's message.
     *
     * @param input the raw text entered by the user
     * @return the chatbot's reply as a string
     */
    public String getResponse(String input) {
        return processUserInput(input);
    }

    /**
     * Initialises the storage and loads tasks from file.
     * <p>
     * If loading fails, a new empty task list is created instead.
     *
     * @return a message indicating whether the storage was loaded successfully
     */
    public String initiateStorage() {
        try {
            this.storage = new Storage(filepath);
            taskList = storage.load();

            if (taskList.isEmpty()) {
                return UiMessages.showEmptyInitialStorageMessage();
            } else {
                return UiMessages.showLoadStorageSuccessfulMessage(this.taskList);
            }
        } catch (IOException | MoonException e) {
            taskList = new TaskList();
            return UiMessages.showLoadStorageUnsuccessfulMessage();
        }
    }

    /**
     * Processes a user input string by parsing it into a command,
     * executing the command, and updating the storage.
     *
     * @param userInput the text input entered by the user
     * @return the response message to display to the user
     */
    public String processUserInput(String userInput) {
        try {
            BaseCommand c = UserInputParser.parse(userInput);
            assert c != null : "Parser must return a non-null command.";

            c.setMetaData(this.taskList);
            String response = c.execute();
            this.storage.rewrite(this.taskList);

            return response;
        } catch (MoonException e) {
            // exceptions returned by parser/commands
            return UiMessages.showExceptionMessage(e.getMessage());

        } catch (NoSuchElementException | IOException e) {
            // exceptions returned by scanner or storage
            return UiMessages.showGeneralErrorMessage();
        }
    }

    /**
     * Checks whether the given message is an exit command.
     *
     * @param message the user input to check
     * @return true if the message equals "bye" (case-insensitive), false otherwise
     */
    public boolean isExitCommand(String message) {
        return message.equalsIgnoreCase("bye");
    }
}
