package eureka.command;

import java.time.LocalDateTime;

import eureka.Storage;
import eureka.task.Event;
import eureka.task.TaskList;
import eureka.ui.Ui;

/**
 * Represents a command that adds an {@link Event} task to the {@link TaskList}.
 * <p>
 * An event task includes a description and a time range (start and end).
 * When executed, the command updates the task list, saves it to storage,
 * and notifies the user through the {@link Ui}.
 */
public class EventCommand extends Command {

    private String taskName;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an EventCommand with the specified task name and event duration.
     *
     * @param taskName the description of the event
     * @param from     the starting date and time of the event
     * @param to       the ending date and time of the event
     */
    public EventCommand(String taskName, LocalDateTime from, LocalDateTime to) {
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date and time of the event.
     * <p>
     * This method is primarily used for testing.
     *
     * @return the start {@link LocalDateTime} of the event
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Executes the event command by creating a new {@link Event} task,
     * adding it to the {@link TaskList}, saving it to {@link Storage},
     * and displaying a confirmation message through the {@link Ui}.
     *
     * @param tasks   the task list to which the event will be added
     * @param ui      the user interface used to show messages
     * @param storage the storage used to persist the updated task list
     * @return a confirmation message containing the added event
     *         and the updated number of tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Event(from, to, taskName, false));
        storage.updateFile();
        ui.addTaskMessage(tasks);

        int totalTasks = tasks.getCount();

        assert tasks.getTask(totalTasks - 1) != null : "last task should exist";

        String addedTask = tasks.getTask(totalTasks - 1).toString();

        return "Got it. I've added this task:\n"
                + addedTask + "\n"
                + "Now you have " + totalTasks + " task"
                + (totalTasks > 1 ? "s" : "") + " in the list.";
    }

    /**
     * Indicates whether this command will terminate the program.
     *
     * @return false, since adding an event does not exit the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
