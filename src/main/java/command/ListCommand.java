package command;

import task.TaskList;

public class ListCommand extends Command {

    public ListCommand(String arg, TaskList tasklist) {
        super(arg, tasklist);
    }

    /**
     * Lists all the task in the <code>TaskList</code>
     */
    public void execute() {
        assert(taskList != null);
        taskList.list();
    }
}
