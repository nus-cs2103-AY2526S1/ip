package balloon.command;

import java.util.ArrayList;

import balloon.logic.Balloon;
import balloon.logic.Storage;
import balloon.logic.TaskList;
import balloon.task.Task;

/**
 * Represents a command that displays every task on the task list.
 */
public class ListCommand extends Command {
    private ArrayList<Task> tasks;

    @Override
    public void execute(TaskList tasks, Storage storage, Balloon balloon) {
        this.tasks = tasks.getTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        String out = "Here are the tasks in your list:";
        for (int i = 1; i <= tasks.size(); i++) {
            out += String.format("\n%d.%s", i, tasks.get(i - 1));
        }
        return out;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
