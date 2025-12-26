package gene.command;

import gene.TaskList;
import gene.tasks.Task;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public String execute(TaskList tasksList) {
        assert tasksList != null;
        return tasksList.addTask(task);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AddCommand)) return false;
        AddCommand other = (AddCommand) o;
        return this.task.toString().equals(other.task.toString());
    }
}
