package cortana.command;

import java.io.IOException;
import java.time.LocalDateTime;

import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.Deadline;
import cortana.task.Event;
import cortana.task.Task;
import cortana.task.TaskList;
import cortana.task.ToDo;

/**
 * Handles adding of new tasks such as cortana.task.ToDo, cortana.task.Deadline, and
 * cortana.task.Event. Supports different constructors for each task type.
 */
public class AddCommand implements Command {
    private final CommandType type;
    private final String taskName;
    private final LocalDateTime deadline; // nullable, only for deadline
    private final LocalDateTime from; // nullable, only for event
    private final LocalDateTime to; // nullable, only for event

    /**
     * Constructs an cortana.command.AddCommand for a cortana.task.ToDo task.
     *
     * @param taskName The name of the cortana.task.ToDo task
     */
    public AddCommand(String taskName) {
        this.type = CommandType.TODO;
        this.taskName = taskName;
        this.deadline = null;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructs a cortana.command.AddCommand for a cortana.task.Deadline task.
     *
     * @param taskName The name of the cortana.task.Deadline task
     * @param deadline The deadline date and time
     */
    public AddCommand(String taskName, LocalDateTime deadline) {
        this.type = CommandType.DEADLINE;
        this.taskName = taskName;
        this.deadline = deadline;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructs a cortana.command.AddCommand for an cortana.task.Event task.
     *
     * @param taskName The name of the cortana.task.Event task
     * @param from     The event start date and time
     * @param to       The event end date and time
     */
    public AddCommand(String taskName, LocalDateTime from, LocalDateTime to) {
        this.type = CommandType.EVENT;
        this.taskName = taskName;
        this.deadline = null;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the add command, adding the task to the task list, displaying output, and saving the
     * task to persistent storage.
     */
    @Override
    public String execute(TaskList tasks, FileHandler fileHandler)
            throws CortanaException, IOException {
        Task task;
        switch (type) {
        case TODO:
            task = new ToDo(taskName);
            break;
        case DEADLINE:
            task = new Deadline(taskName, deadline);
            break;
        case EVENT:
            task = new Event(taskName, from, to);
            break;
        default:
            throw new CortanaException("Invalid task type in cortana.command.AddCommand");
        }
        String output = tasks.add(task);
        // call appropriate save method
        switch (type) {
        case TODO:
            fileHandler.saveToDo(task.toString());
            break;
        case DEADLINE:
            fileHandler.saveDeadline(task.toString());
            break;
        case EVENT:
            fileHandler.saveEvent(task.toString());
            break;
        default:
            throw new CortanaException("Something went wrong, your task could not be saved");
        }
        return output;
    }
}
