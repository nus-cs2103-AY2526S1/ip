package luna.ui;

import java.io.IOException;

import luna.command.Command;
import luna.storage.Storage;
import luna.task.TaskList;

/**
 * Represents the chatbot.
 */
public class Luna {
    private final Storage<TaskList> storage;
    private TaskList taskList;

    public Luna(String filePath) {
        storage = new Storage<>(filePath);
        try {
            taskList = storage.load();
        } catch (IOException | ClassNotFoundException e) {
            taskList = new TaskList();
        }
        assert taskList != null;
    }

    public String getWelcomeMessage() {
        return "Hello! I'm Luna. What can I do for you?";
    }

    /**
     * Returns a response for the user's chat message.
     * Executes the command.
     */
    public String getResponse(Command command) {
        return command.execute(taskList, storage);
    }
}
