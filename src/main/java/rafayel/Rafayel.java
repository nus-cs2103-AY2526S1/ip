
package rafayel;

import java.io.IOException;
import java.util.ArrayList;

import rafayel.command.Command;
import rafayel.command.CommandHandle;
import rafayel.command.Parser;
import rafayel.storage.Storage;
import rafayel.task.Task;
import rafayel.task.TaskList;
import rafayel.ui.Ui;

/**
 * Main chatbot class for Rafayel.
 * Rafayel is a task management chatbot that allows users to add,
 * delete, mark, unmark, find, and list tasks.
 * It supports different task types:
 * Todo, Deadline and Event, and stores data persistently.
 */
public class Rafayel {

    // Constants
    /** Error message when task number provided is invalid. */
    public static final String INVALID_TASK_NUM = "Invalid task number. This is why I prefer communicating with paint.";

    /** Error message when date format does not match supported formats. */
    public static final String DATE_FORMAT_ERROR = "If you're going to let me remind you, at least present the date "
            + "elegantly .-. \nUse a format like `MMM d yyyy HH:mm` | `yyyy/MM/dd HH:mm` | `dd-MM-yyyy HH:mm`.";

    /** Error message when command is not found. */
    public static final String UNKNOWN_COMMAND_ERROR = "Did a seashell whisper that to you? Because I didn't catch "
            + "a word. \nUse a command I actually know, little assistant :<";

    /** Storage object that saves the task to local file storage. */
    private final Storage storage;

    /** TaskList stores the list of tasks */
    private TaskList tasks;

    /** Whether the user exit the program or not */
    private boolean isExit;

    /**
     * Constructs a new Rafayel chatbot instance with the specified file path for data storage.
     *
     * @param filePath path to the file where task data will be stored.
     * @throws RafayelException if there is an error initialising the storage.
     */
    public Rafayel(String filePath) throws RafayelException {
        this.storage = new Storage(filePath);
        this.isExit = false;
        Ui ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (RafayelException e) {
            ui.showError(e.getMessage());
            // ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns all tasks in the task list.
     *
     * @return the TaskList in ArrayList form.
     */
    public ArrayList<Task> getAll() {
        return tasks.getAll();
    }

    /**
     * Saves the current tasks into the storage location
     *
     * @throws RafayelException if there is an error writing to storage.
     */
    public void save() throws RafayelException {
        storage.save(tasks.getAll());
    }

    /**
     * Retrieves upcoming reminders or overdue from the task list.
     *
     * @return a string of reminders, or an empty string if none.
     */
    public String getReminders() {
        try {
            return getResponse("remind");
        } catch (RafayelException e) {
            // Should not have exception
            // Ignore
            return "";
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input the user’s raw command
     * @return chatbot’s response as a string
     * @throws RafayelException if there's any error while executing user commands
     */
    public String getResponse(String input) throws RafayelException {

        assert input != null : "Input cannot be null";
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";

        if (isExit) {
            System.exit(0);
        }

        try {
            Command command = Parser.parseCommand(input);
            String result = command.execute(tasks, storage);
            this.isExit = command.getCommand() == CommandHandle.CommandType.BYE;

            return result;

        } catch (RafayelException e) {
            return e.getMessage();
        } catch (Exception e) {
            return UNKNOWN_COMMAND_ERROR;
        }
    }

    /**
     * Returns whether the chatbot should exit
     */
    public boolean shouldExit() {
        return isExit;
    }
}
