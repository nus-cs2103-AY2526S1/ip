package bot.command;

import bot.exception.InvalidCommandException;
import bot.service.FileService;
import bot.task.Task;
import bot.task.TaskList;
import bot.ui.ResponseMessage;

import java.util.List;

/**
 * Represents a command to find tasks containing a specific keyword.
 * This command searches the current task list for tasks whose names contain the provided keyword.
 */
public class FindCommand extends Command {
    private final String[] commandInfo;

    /**
     * Constructs a {@code FindCommand} with the provided command information.
     * The command information is expected to be an array where the first element is the
     * command name ("find") and the second element is the search keyword.
     *
     * @param commandInfo The array containing the command name and the search keyword.
     */
    public FindCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    /**
     * Executes the find command.
     * This method validates the command format, searches for tasks matching the keyword,
     * and sets the response message to display the filtered list of tasks.
     *
     * @param taskList The {@link TaskList} to be searched.
     * @param fileService The {@link FileService} (not used in this command, but required by the parent class).
     */
    @Override
    public void execute(TaskList taskList, FileService fileService) {
        try {
            // Validate command format, re-prompt if incorrect command format
            boolean isValidCommandInfo = commandInfo.length == 2;
            if (!isValidCommandInfo) {
                throw new InvalidCommandException(
                        "Oh no, command format is not right, let me give you a hint: " +
                                "find <Search Keyword>");
            }

            String keyword = commandInfo[1];

            // Find all tasks that matches keyword
            List<Task> filteredList = taskList.searchTasksByName(keyword);

            // Set success message
            super.setResponse(ResponseMessage.getSearchTaskListMessage(filteredList));
        } catch (Exception e) {
            super.setResponse(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
