
package rafayel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import rafayel.command.Command;
import rafayel.command.Parser;
import rafayel.storage.Storage;
import rafayel.task.Task;
import rafayel.task.TaskList;
import rafayel.ui.Ui;

/**
 * Chatbot named Rafayel that manages a task list.
 * Functions include to add, delete, mark, unmark, find and list tasks.
 * Supports different task types: Todo, Deadline, and Event.
 * Saves task data to local file storage.
 */
public class Rafayel {

    /* Storage object that saves the task to local file storage. */
    private final Storage storage;
    /* TaskList stores the list of tasks */
    private TaskList tasks;
    /* Manages the ui of Rafayel */
    private final Ui ui;

    // Code quality
    public static final String INVALID_TASK_NUM = "Invalid task number.";
    // private static final String INVALID_PROMPT = "Please enter a valid prompt! (i.e. todo/deadline/event)";
    public static final String DATE_FORMAT_ERROR = "Please use one of: MMM d yyyy HH:mm | yyyy/MM/dd HH:mm | dd-MM-yyyy HH:mm";

    /**
     * Constructs a new Rafayel chatbot instance with the specified file path for data storage.
     *
     * @param filePath path to the file where task data will be stored.
     * @throws RafayelException if there is an error initialising the storage.
     */
    public Rafayel(String filePath) throws RafayelException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RafayelException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the TaskList in ArrayList form.
     */
    public ArrayList<Task> getAll() {
        return tasks.getAll();
    }

    /**
     * Saves the current tasks into the storage location
     */
    public void save() throws RafayelException {
        storage.save(tasks.getAll());
    }

    /**
     * Parses a date string into a LocalDateTime object with three supported formats.
     *
     * @param input input of the date string to parse.
     * @return the parsed LocalDateTime object, null if no format matches.
     */
    public static LocalDateTime handleReadDate(String input) {
        // check if valid format
        DateTimeFormatter[] differentTimeFormatters = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm") };

        for (DateTimeFormatter formatter : differentTimeFormatters) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (Exception ignore) {
                // ignore
            }
        }

        return null;
    }

    public String getReminders() throws RafayelException {
        return getResponse("remind");
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @throws RafayelException if there's any error while executing user commands
     */
    public String getResponse(String input) throws RafayelException {

        assert input != null : "Input cannot be null";
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";

        try {
            Command command = Parser.parseCommand(input);
            String result = command.execute(tasks, storage);

            return result;
        } catch (RafayelException e) {
            // commandType = rafayel.command.CommandHandle.CommandType.UNKNOWN;
            // ui.showError(e.getMessage());
            return e.getMessage();
        } catch (Exception e) {
            return "Sorry, I can't understand :c";
        }
    }
}
