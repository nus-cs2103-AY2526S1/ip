package command;

import task.TaskList;

public interface CommandBuilder {
    public Command build(String arg, TaskList taskList);
}
