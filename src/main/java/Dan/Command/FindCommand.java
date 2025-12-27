package Dan.Command;

import Dan.Task.Task;
import Dan.Task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    String searchStr;

    /**
     * Constructs a FindCommand that will search for tasks containing the specified search string.
     *
     * @param searchStr the string to search for within task descriptions
     */
    public FindCommand(String searchStr) {
        this.searchStr = searchStr;
    }

    /**
     * Returns the command type for this command.
     *
     * @return CommandType.EXIT indicating this is a find command
     */
    public CommandType getType() {
        return CommandType.EXIT;
    }

    /**
     * Executes the find command by searching for tasks that contain the search string
     * and formatting the results as a numbered list.
     *
     * @param tasks the task list to search through
     * @return a formatted string containing a numbered list of matching tasks,
     *         or an empty string if no tasks match the search criteria
     */
    @Override
    public String execute(TaskList tasks) {
        ArrayList<Task> searchResults = tasks.find(searchStr);
        String response = "";

        for(int i = 0; i < searchResults.size(); i++) {
            response += i + 1 + "." + searchResults.get(i) + "\n";
        }

        return  response;
    }
}
