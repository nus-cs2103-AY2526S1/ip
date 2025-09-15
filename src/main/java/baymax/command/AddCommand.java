package baymax.command;

import java.time.LocalDate;

import baymax.task.Deadline;
import baymax.task.Event;
import baymax.task.Task;
import baymax.task.TaskList;
import baymax.task.ToDo;

/**
 * Represents a command that adds a new task to the task list.
 * <p>
 * The task can be a {@link ToDo}, {@link Deadline}, or {@link Event}.
 * </p>
 */
public class AddCommand extends Command {

    private Task task;

    private AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Creates an {@code AddCommand} for a {@link ToDo} task.
     *
     * @param description The description of the to-do task.
     * @return A new {@code AddCommand} that will add the to-do task.
     */
    public static AddCommand todo(String description) {
        return new AddCommand(new ToDo(false, description));
    }

    /**
     * Creates an {@code AddCommand} for a {@link Deadline} task.
     *
     * @param description The description of the deadline task.
     * @param deadline The due date of the deadline task.
     * @return A new {@code AddCommand} that will add the deadline task.
     */
    public static AddCommand deadline(String description, LocalDate deadline) {
        return new AddCommand(new Deadline(false, description, deadline));
    }

    /**
     * Creates an {@code AddCommand} for an {@link Event} task.
     *
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     * @return A new {@code AddCommand} that will add the event task.
     */
    public static AddCommand event(String description, String start, String end) {
        return new AddCommand(new Event(false, description, start, end));
    }

    /**
     * Executes the add command by inserting the specified task into the given task list.
     *
     * @param tasks The {@link TaskList} to which the task will be added.
     * @return A confirmation message indicating that the task has been
     *         added, along with the updated number of tasks in the list.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.addTask(this.task);
    }
}
