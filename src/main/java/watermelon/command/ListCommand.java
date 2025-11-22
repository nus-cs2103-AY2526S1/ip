package watermelon.command;

import watermelon.TaskList;
import watermelon.Ui;

/**
 * Represents a list command that contains a tasklist, ui, storage and message.
 * Upon execution, tasks in tasklist are saved into the message.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand object with given details.
     *
     * @param taskList Tasklist to be listed.
     */
    public ListCommand(TaskList taskList, Ui ui) {
        super.taskList = taskList;
        super.ui = ui;
    }

    /**
     * Prints out tasks in tasklist.
     */
    @Override
    public void execute() {
        message = ui.showTasksListedMessage(taskList);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
