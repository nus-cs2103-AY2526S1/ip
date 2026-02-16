package guibot;

import java.io.IOException;

import guibot.exception.DataFileCorruptedException;
import guibot.exception.GuibotException;

/**
 * Represents a Guibot.
 */
public class Guibot {
    private static final String DEFAULT_FILE_PATH = "src/main/data/tasks.txt";

    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Guibot with specified filePath.
     *
     * @param filePath Path to the data file.
     * @return A Guibot with its storage set to the file.
     */
    public Guibot(String filePath) throws DataFileCorruptedException, IOException {
        storage = new Storage(filePath);
        tasks = new TaskList();
        storage.loadTasks(tasks);
    }

    /**
     * Creates a Guibot with default filePath. To be used for GUI
     *
     * @return A Guibot with its storage set to the file in the default location.
     */
    public Guibot() throws IOException {
        storage = new Storage(DEFAULT_FILE_PATH);
        tasks = new TaskList();
        try {
            storage.loadTasks(tasks);
        } catch (DataFileCorruptedException e) {
            tasks = new TaskList();
            System.out.println("Data file corrupted, starting from an empty tasklist");
        }
    }

    /**
     * Gets the Guibot's response to an input string.
     *
     * @param input The input string.
     * @return A string response
     */
    public String getResponse(String input) throws GuibotException, IOException {
        return Parser.parse(input).execute(tasks, storage);
    }
}
