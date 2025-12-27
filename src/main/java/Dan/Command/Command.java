package Dan.Command;

import Dan.Task.TaskList;

public abstract class Command {
    public abstract CommandType getType();

    public abstract String execute(TaskList tasks);
}
