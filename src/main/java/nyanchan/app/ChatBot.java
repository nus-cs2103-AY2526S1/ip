package nyanchan.app;

import nyanchan.exceptions.NyanException;

/**
 * Main controller for the Nyanchan chatbot.
 * Handles user input, storage, and task management.
 */
public class ChatBot {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Creates a {@code ChatBot} using the given file path.
     * Loads tasks from storage, or starts fresh if loading fails.
     *
     * @param filePath path to the task data file
     */
    public ChatBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loaded;
        try {
            loaded = new TaskList(storage.load());
        } catch (NyanException e) {
            // return error string if needed
            loaded = new TaskList();
        }
        taskList = loaded;
    }

    /**
     * Processes a single user input and returns Nyanchan's response.
     */
    public String getResponse(String userInput) {
        try {
            if (userInput.equals("bye")) {
                return ui.showGoodbye();
            }
            return Parser.handleCommand(userInput, taskList, ui, storage);
        } catch (NyanException e) {
            return ui.showError(e.getMessage());
        } catch (Exception e) {
            return ui.showError("Something went wrong: " + e.getMessage());
        }
    }
}
