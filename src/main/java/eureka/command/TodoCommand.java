package eureka.command;

import eureka.Storage;
import eureka.task.TaskList;
import eureka.task.Todo;
import eureka.ui.Ui;

/**
 * Represents a command that adds a {@link Todo} task to the {@link TaskList}.
 * <p>
 * A todo task contains only a description, without any specific date or time.
 * When executed, this command updates the task list, saves it to storage,
 * and notifies the user through the {@link Ui}.
 */
public class TodoCommand extends Command {

    private String taskName;

    /**
     * Constructs a TodoCommand with the specified task name.
     *
     * @param taskName the description of the todo task
     */
    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Executes the todo command by creating a new {@link Todo} task,
     * adding it to the {@link TaskList}, saving it to {@link Storage},
     * and displaying a confirmation message through the {@link Ui}.
     *
     * @param tasks   the task list to which the todo will be added
     * @param ui      the user interface used to display messages
     * @param storage the storage used to persist the updated task list
     * @return a confirmation message containing the added todo
     *         and the updated number of tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Todo(taskName, false));
        storage.updateFile();
        ui.addTaskMessage(tasks);

        int totalTasks = tasks.getCount();

        assert tasks.getTask(totalTasks - 1) != null : "last task should exist";

        String addedTask = tasks.getTask(totalTasks - 1).toString();
        String isPlural = totalTasks > 1 ? "s" : "";

        return "Got it. I've added this task:\n"
                + addedTask + "\n"
                + "Now you have " + totalTasks + " task"
                + isPlural + " in the list.";
    }

    /**
     * Indicates whether this command will terminate the program.
     *
     * @return false, since adding a todo does not exit the application
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
