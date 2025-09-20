package balloon.command;

import java.util.ArrayList;

import balloon.logic.Balloon;
import balloon.logic.Storage;
import balloon.logic.TaskList;
import balloon.task.Task;

/**
 * Represents a command that shows all the tasks that contain a certain keyword
 * in their description.
 */
public class FindCommand extends Command {
    private String keyword;
    private ArrayList<Task> tasksFound;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Balloon balloon) {
        tasksFound = tasks.getTasksWithKeyword(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        String out = "Here are the matching tasks in your list:";
        for (int i = 1; i <= tasksFound.size(); i++) {
            out += String.format("\n%d.%s", i, tasksFound.get(i - 1));
        }
        return out;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
