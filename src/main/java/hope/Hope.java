package hope;

import java.io.File;
import java.io.IOException;

import hope.parser.Parser;
import hope.storage.TaskStorage;
import hope.storage.ToDoList;

/**
 * A chatbot application named Hope that manages a to-do list.
 * The {@code Hope} class initializes the chatbot's components, including the user interface,
 * task storage, and parser, and handles user interactions through a command-line interface.
 * It loads tasks from a file, processes user input, and persists tasks to storage.
 * The chatbot runs in a loop until an exit command is received.
 */
public class Hope {
    /** The file where tasks are stored. */
    private final File data;

    /** The storage system for managing task persistence. */
    private final TaskStorage taskStorage;

    /** Indicates whether the chatbot is running. */
    private boolean isOnline;

    /** The to-do list containing the user's tasks. */
    private ToDoList toDoList;

    /** The parser for interpreting user input and executing commands. */
    private Parser parser;

    /**
     * Constructs a {@code Hope} chatbot instance.
     * Initializes the user interface, task storage, to-do list, and parser.
     * Creates a data file (Hope.txt) in the ./data directory if it does not exist.
     * If file creation fails, an error message is displayed via the user interface.
     */
    public Hope() throws IOException {
        this.data = new File("./data/HopeData.txt");
        this.taskStorage = new TaskStorage(data);
        this.isOnline = true;
        this.toDoList = new ToDoList(taskStorage.toList());
        this.parser = new Parser(taskStorage, toDoList);

        try {
            File parentDir = data.getParentFile();
            if (parentDir.exists() && parentDir.isDirectory()) {
                if (!data.exists()) {
                    data.createNewFile();
                }
            } else {
                parentDir.mkdirs();
                data.createNewFile();
            }
        } catch (IOException e) {
            throw e;
        }
    }
    /**
     * Display startup message
     */
    public String showStartupMessage() {
        return "Greetings, fair wanderer!\n"
                + "I am Hope, your humble companion."
                + "How might I assist thee on this grand quest?";
    }

    public String getResponse(String input) {
        return parser.parse(input);
    }

}
