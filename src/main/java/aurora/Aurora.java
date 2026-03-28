package aurora;

import aurora.command.Command;
import aurora.command.CommandReader;
import aurora.storage.Storage;
import aurora.task.TaskList;

/**
 * Aurora is a simple task management chatbot that runs on the command line.
 * <p>
 * It allows user to add, list, mark as done, and delete tasks.
 * Saving and loading between sessions is supported and tasks are stored in a text file on disk.
 * </p>
 */
public class Aurora {
    private final Storage storage;
    private final TaskList list;
    private String commandType;

    /**
     * Constructs a new Aurora chatbot.
     *
     * @param filePath path to the file used for saving and loading tasks
     */
    public Aurora(String filePath) {
        this.storage = new Storage(filePath);
        this.list = storage.load();
    }

    /**
     * Constructs a new Aurora chatbot.
     */
    public Aurora() {
        this("./data/aurora.txt");
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        assert input != null && !input.isBlank() : "Input string cannot be null or empty";
        Command c = CommandReader.read(input);
        commandType = c.getClass().getSimpleName();
        String result = c.execute(list);
        storage.save(list);
        return result;
    }

    public String getCommandType() {
        return commandType;
    }
}
