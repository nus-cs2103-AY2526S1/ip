package karl.command;

import karl.exception.KarlException;
import karl.storage.Storage;
import karl.task.Task;
import karl.task.TaskList;
import karl.ui.Ui;

import java.util.List;

/**
 * Command to find tasks containing a keyword in their description.
 * Searches case-insensitively and displays matching tasks.
 */
public class FindCommand implements Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the keyword extracted from user input.
     *
     * @param input the raw user input string starting with "find"
     * @throws KarlException if no keyword is provided
     */
    public FindCommand(String input) throws KarlException {
        String userInput = input.trim();
        if (userInput.length() <= 4) { // "find" + space + keyword
            throw new KarlException("The find command requires a keyword.");
        }
        this.keyword = userInput.substring(5).trim(); // extract keyword after "find "
        if (this.keyword.isEmpty()) {
            throw new KarlException("The find command requires a keyword.");
        }
    }

    /**
     * Executes the find command by searching tasks for the keyword
     * and displaying matched tasks or a no-match message.
     *
     * @param tasks the TaskList to search
     * @param ui the Ui object for user interaction
     * @param storage the Storage object (not used here)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchedTasks = tasks.find(keyword);
        if (matchedTasks.isEmpty()) {
            ui.showMessage("No matching tasks found for keyword: " + keyword);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchedTasks.size(); i++) {
                sb.append((i + 1)).append(".").append(matchedTasks.get(i)).append("\n");
            }
            ui.showMessage(sb.toString());
        }
    }


    /**
     * Indicates this command does not terminate the program.
     *
     * @return false as this command does not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
