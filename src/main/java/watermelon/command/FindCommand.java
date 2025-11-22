package watermelon.command;

import java.util.ArrayList;

import watermelon.TaskList;
import watermelon.Ui;
import watermelon.exception.StorageOperationException;
import watermelon.task.Task;

/**
 * Represents a find command that contains a keyword, ui, storage, tasklist and message.
 * Upon execution, tasks with description matching the keyword are saved into the message.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand object with given details.
     */
    public FindCommand(TaskList taskList, String keyword, Ui ui) {
        super.taskList = taskList;
        super.ui = ui;
        this.keyword = keyword;
    }

    @Override
    public void execute() throws StorageOperationException {
        ArrayList<Task> matchingTasks = taskList.findTask(keyword);
        assert matchingTasks != null : "matchingTasks should not be null";

        if (matchingTasks.isEmpty()) {
            message = ui.showNoTasksFoundMessage();
            return;
        }

        message = ui.showTasksFoundMessage(matchingTasks);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
