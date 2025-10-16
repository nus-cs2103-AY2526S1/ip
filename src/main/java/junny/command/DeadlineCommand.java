package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Deadline;
import junny.tasks.Task;

/**
 * Represents a command that creates and adds a {@link Deadline} task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a DeadlineCommand.
     *
     * @param description the description of the deadline task
     * @param by          the due date in yyyy-MM-dd format
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the deadline command by creating a new deadline task,
     * adding it to the task list, updating the UI, and saving to storage.
     *
     * @param tasks   the current list of tasks
     * @param ui      the UI handler to display messages
     * @param storage the storage handler to save/load tasks
     */
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        Deadline ddl = new Deadline(this.description, this.by);
        tasks.add(ddl);
        ui.addTask(ddl, tasks.size());
        storage.saveAllTasks(tasks);
    }
}
