package winnie.command;

import winnie.storage.Storage;
import winnie.task.Todo;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Todo todo = new Todo(description);
            tasks.addTask(todo);
            ui.showTaskAdded(todo, tasks.getTaskCount());
            storage.saveTasksToFile(tasks);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
