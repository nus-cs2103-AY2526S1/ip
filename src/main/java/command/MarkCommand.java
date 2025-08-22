package command;

import task.Task;

import java.util.List;

public class MarkCommand implements Command{
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(List<Task> list) {
        if (index == 0 || index > list.size()) {
            return "There is no task numbered " + index + ".";
        }

        Task task = list.get(index - 1);
        task.complete();
        return "Nice! I've marked this task as completed.\n" + task;
    }
}
