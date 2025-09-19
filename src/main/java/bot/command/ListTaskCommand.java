package bot.command;

import bot.service.FileService;
import bot.task.TaskList;
import bot.ui.ResponseMessage;

/**
 * Represents a command to display the list of all tasks.
 * This command retrieves and displays all tasks currently in the task list.
 */
public class ListTaskCommand extends Command {
    public ListTaskCommand() {}

    /**
     * Executes the list task command by displaying all tasks in the task list.
     *
     * @param taskList the task list containing tasks to be displayed
     * @param fileService the file services (not used in this command)
     */
    @Override
    public void execute(TaskList taskList, FileService fileService) {
        super.setResponse(ResponseMessage.getTaskListMessage(taskList.getTaskList()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
