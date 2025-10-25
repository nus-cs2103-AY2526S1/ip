package dwight;

import dwight.command.Command;
import dwight.command.CommandFactory;
import dwight.command.CommandResponse;
import dwight.command.ResponseType;
import dwight.command.UnknownCommandException;
import dwight.storage.Storage;
import dwight.task.TaskList;

import java.io.IOException;

/**
 * Entry point class for the Dwight application. Initializes the user interface, storage, and task
 * management system, and processes user commands until the application is terminated.
 */
public class Dwight {
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a new {@code Dwight} instance and initializes the storage and task list. Loads
     * existing tasks from the storage file if available.
     */
    public Dwight() {
        this.storage = new Storage("./dwight-2103-ip.txt");
        try {
            this.taskList = this.storage.load();
        } catch (IOException e) {
            System.out.println("OH DEAR! " + e.getMessage());
            return;
        }
        assert this.taskList != null
                : "TaskList should not be null after successful initialization.";
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user's input command string.
     * @return A {@code CommandResponse} containing the result of executing the command.
     */
    public CommandResponse getResponse(String input) {
        assert this.taskList != null
                : "TaskList must be initialized before commands can be processed.";
        String trimmedInput = input.trim();
        CommandResponse response;
        try {
            String[] parts = trimmedInput.split(" ", 2);
            String cmdStr = parts[0];
            String description = parts.length > 1 ? parts[1] : "";
            Command cmd = CommandFactory.getCommand(cmdStr);
            response = cmd.execute(taskList, description);
            storage.save(taskList);
        } catch (UnknownCommandException | IOException e) {
            response = new CommandResponse("OH DEAR! " + e.getMessage(), ResponseType.ERROR);
        }
        assert response != null : "Command execution resulted in a null response.";
        return response;
    }
}
