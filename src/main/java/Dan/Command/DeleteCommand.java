package Dan.Command;

import Dan.Task.Task;
import Dan.Task.TaskList;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int i) {
        this.index = i;
    }

    public CommandType getType() {
        return  CommandType.DELETE;
    }

    public String execute(TaskList tasks) {

        if (index < 0) {
            return "Please key in a valid item number that is more than zero";
        } else if(index > tasks.size()) {
            return "This item number exceeds the tasklist size";
        }

        assert index > 0;
        Task task = tasks.getTask(index);
        tasks.delete(index);
        return "Noted. I've removed this task: \n " + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
