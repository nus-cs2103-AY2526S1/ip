package sage.command;

import sage.storage.Storage;
import sage.task.TaskList;
import sage.task.Ui;
import sage.exception.SageException;
import java.util.ArrayList;
import sage.task.Task;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String executeAndReturn(TaskList tasks, Ui ui, Storage storage) throws SageException {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.isEmpty()) {
            return ui.showMessageAndReturn("No matching tasks found.");
        } else {
            return ui.showMatchingTasksAndReturn(matchingTasks);
        }
    }
}