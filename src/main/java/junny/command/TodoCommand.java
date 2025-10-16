package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;
import junny.tasks.Todo;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the list-on-date command by showing tasks scheduled
     * on the specified date.
     *
     * @param tasks   List of current tasks.
     * @param ui      The UI used to interact with the user.
     * @param storage The storage handler (not used here).
     */
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.addTask(todo, tasks.size());
        storage.saveAllTasks(tasks);
    }
}
