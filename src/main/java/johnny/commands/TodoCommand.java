package johnny.commands;

import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.tasks.Task;
import johnny.tasks.TodoTask;
import johnny.ui.Ui;

/**
 * A command that adds a Todo task to the TaskList.
 */
public class TodoCommand extends Command {
    protected String name;

    /**
     * Creates a new TodoCommand with the specified task name.
     * 
     * @param name Name of task to be added.
     */
    public TodoCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new TodoTask(this.name);
        tasks.addTask(newTask);
        return ui.printAddTaskMessage(tasks, newTask);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
