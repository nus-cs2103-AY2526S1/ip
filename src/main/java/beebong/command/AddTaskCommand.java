package beebong.command;

import beebong.exception.BBongException;
import beebong.storage.Storage;
import beebong.task.Task;
import beebong.task.TaskList;
import beebong.ui.UI;

public abstract class AddTaskCommand extends Command {
    protected String name;

    public AddTaskCommand(String name) {
        this.name = name;
    }

    protected abstract Task createTask();

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws BBongException {
        // Create new Task
        Task newTask = createTask();
        taskList.addTask(newTask);
        ui.botMessage("Bing! Task added to my list:\n" + newTask + "\nYou now have " + taskList.length() + " task(s) " +
                "buzzing around in the list.");
    }
}
