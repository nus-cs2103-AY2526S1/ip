package khat.command;

import khat.exception.KhatException;
import khat.storage.Storage;
import khat.task.Deadline;
import khat.task.Event;
import khat.task.Task;
import khat.task.TaskList;
import khat.task.Todo;
import khat.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    private String type;
    private String description;
    private String by; //deadline
    private String from; //event
    private String to; //event

    /**
     * Constructs an AddCommand for a Todo task.
     *
     * @param description Description of Todo task.
     * @param type Type of task (todo).
     */
    public AddCommand(String description, String type) {
        this.type = type;
        this.description = description;
        this.by = null;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructs an AddCommand for a Deadline task.
     *
     * @param description Description of Deadline task.
     * @param type Type of task (deadline).
     * @param by Deadline date/time of the Deadline task.
     */
    public AddCommand(String description, String type, String by) {
        this.type = type;
        this.description = description;
        this.by = by;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructs an AddCommand for an Event task
     *
     * @param description Description of Event task
     * @param type Type of task (event)
     * @param from Event start date/time string.
     * @param to Event end date/time string.
     */
    public AddCommand(String description, String type, String from, String to) {
        this.type = type;
        this.description = description;
        this.by = null;
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     *
     * Creates a new task based on type and adds it to the task list and
     * displays task added with updated number of tasks in task list.
     *
     * @throws KhatException If task type is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KhatException {
        //CHECKSTYLE.OFF: Indentation
        Task t = switch (type) {
            case "todo" -> new Todo(description, false);
            case "deadline" -> new Deadline(description, false, by);
            case "event" -> new Event(description, false, from, to);
            default -> throw new KhatException("Invalid task!");
        };
        //CHECKSTYLE.ON: Indentation
        if (tasks.isDuplicate(t)) {
            ui.showMessage("This task appears to be a duplicate!\n I'll skip adding this task.");
        } else {
            tasks.addTask(t);
            ui.showMessage("Got it. I've added this task:\n" + t);
            ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
        }
        storage.saveTasks(tasks);
    }
}
