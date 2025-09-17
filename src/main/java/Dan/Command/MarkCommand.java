package Dan.Command;

import Dan.Task.TaskList;
import Dan.Task.Task;

public class MarkCommand extends Command {
    int index;

    public MarkCommand(int i) {
        this.index = i;
    }

    public CommandType getType() {
        return CommandType.MARK;
    }

    public String execute(TaskList tasks) {
        if (index < 0) {
            return "Please key in a valid item number that is more than zero";
        } else if(index > tasks.size()) {
            return "This item number exceeds the tasklist size";
        }

        assert index > 0;
        tasks.mark(index);
        Task task = tasks.getTask(index);

        if (task.isDone()) {
            return "Got it. I've marked this task as done: \n " + task + "\n";
        } else {
            return "Got it. I've marked this task as undone: \n " + task + "\n";
        }
    }
}
