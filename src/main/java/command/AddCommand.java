package command;

import task.Task;

import java.util.List;

public class AddCommand implements Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(List<Task> list) {
        list.add(task);
        return "Added " + task.getDescription() + " as a task into your list.";
    }
}
