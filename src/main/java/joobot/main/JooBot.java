package joobot.main;

import joobot.command.Command;
import joobot.task.TaskList;

/**
 * The main class for running the JooBot application.
 * <p>
 * JooBot is a simple task chatbot that supports commands such as
 * adding tasks, marking them as done, deleting them, and persisting them
 * to a file.
 */
public class JooBot {
    private final Storage storage;
    private final TaskList tasks;
    /**
     * Creates a JooBot instance with the given file path for storage.
     *
     * @param filePath the file path where tasks are stored
     */
    public JooBot(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Generates a response for the GUI.
     *
     * @param input user input string
     * @return JooBot’s response
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, storage);
        } catch (JooException e) {
            return e.getMessage();
        }
    }
}
