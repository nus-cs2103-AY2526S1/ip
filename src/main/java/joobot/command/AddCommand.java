package joobot.command;
import joobot.main.Storage;
import joobot.task.Task;
import joobot.task.TaskList;

/**
 * Represents a command that adds a task to the program
 */
public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(task);
        storage.save(tasks.getAllTasks());
        return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
