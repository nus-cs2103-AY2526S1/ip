package shef;

import shef.command.Command;
import shef.exception.ShefException;
import shef.storage.Storage;
import shef.tasklist.TaskList;

/**
 * Class that encapsulates the chatbot.
 */
public class Shef {
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Returns a Shef object.
     * @param filePath file path for the data file
     */
    public Shef(String filePath) {
        this.tasks = new TaskList();
        this.storage = new Storage(filePath);
        storage.read(tasks);
    }

    /**
     * Returns response to user input.
     * @param cmd Command to execute.
     * @return chatbot response.
     */
    public String getResponse(Command cmd) {
        try {
            return cmd.execute(tasks, storage);
        } catch (ShefException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets initial hello message.
     * @return hello message.
     */
    public static String getHelloMessage() {
        return "Hello! I'm Shef.\n"
                + "What can I do for you?\n"
                + "Type \"help\" to see a list of commands.";
    }
}
