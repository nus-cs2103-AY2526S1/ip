package beebong.command;

import beebong.task.Task;
import beebong.task.ToDoTask;

public class AddToDoTaskCommand extends AddTaskCommand {
    public AddToDoTaskCommand(String name) {
        super(name);
    }

    @Override
    protected Task createTask() {
        return new ToDoTask(this.name);
    }
}
