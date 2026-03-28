package jerry.command;

import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;
import jerry.storage.Storage;
import jerry.task.Task;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * Helps in finding the task that matches with the key word entered by the user.
 * It parses the user input, and display one or list of matching task if found.
 */
public class FindCommand extends Command {
    private static final String MATCHING_TASKS_MESSAGES = "Here are the matching tasks in your list:\n";
    private static final String NO_MATCHING_TASKS_MESSAGES = "No matching tasks found for: ";
    private final String findWord;

    /**
     * It takes in user input and parse them into two components:
     * 'find' command and keyword that the user enter to find the task.
     * Exception is thrown when the command is invalid and if the user
     * does not enter the keyword to find the matching tasks.
     *
     * @param input input string entered by the user to be parsed.
     * @throws JerryException if user input is invalid.
     */
    public FindCommand(String input) throws JerryException {
        String[] entries = input.trim().split(" ", 2);
        assert entries.length > 0 : "Input should not be empty";
        assert !entries[0].isEmpty() : "Input should start with 'find' command";
        if (entries.length < 2 || entries[1].trim().isEmpty()) {
            throw new InvalidCommandFormatException("Enter the task that you want to find");
        }
        this.findWord = entries[1].trim();
    }


    /**
     * Searches through the TaskList for tasks whose descriptions contain the specified keyword.
     * Tasks are displayed in order if they are found.
     * Otherwise, a message indicating that no tasks were found is shown.
     *
     * @param taskList The list of tasks to search through.
     * @param ui The UI handler for displaying output to the user.
     * @param storage The storage handler (not modified by this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder(MATCHING_TASKS_MESSAGES);
        int index = 1;
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().toLowerCase().contains(this.findWord.toLowerCase())) {
                result.append(index).append(". ").append(task).append("\n");
                index++;
            }
        }
        this.response = (index == 1) ? String.format(NO_MATCHING_TASKS_MESSAGES, findWord) : result.toString().trim();
        ui.displayOutput(this.response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return this.response;
    }
}
