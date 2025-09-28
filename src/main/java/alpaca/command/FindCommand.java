package alpaca.command;

import alpaca.Storage;
import alpaca.task.TaskList;
import alpaca.Ui;
import alpaca.task.Task;

/**
 * Command to find and list all tasks containing a given keyword.
 * If the keyword is missing, it provides guidance on the correct syntax.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for; must not be null or empty.
     */
    public FindCommand(String keyword) {
        assert keyword != null : "Keyword cannot be null";
        this.keyword = keyword.trim();
    }

    /**
     * Executes the find command: searches for tasks whose descriptions contain the keyword.
     *
     * @param tasks   The task list to search; must not be null.
     * @param ui      The UI for displaying messages; must not be null.
     * @param storage The storage handler (not used in this command, can be null).
     * @return A string listing matching tasks or guidance if no keyword is provided.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";

        // Handle empty keyword input
        if (keyword.isEmpty()) {
            String msg = "Invalid usage: You must provide a keyword to search.\n" +
                    "Correct syntax: find <keyword>";
            ui.showMessage(msg);
            return msg;
        }

        StringBuilder response = new StringBuilder();
        ui.showLine();
        response.append("Here are the matching tasks in your list:\n");

        int count = 0;
        for (Task t : tasks.getTasks()) {
            if (t.getDescription().contains(keyword)) {
                count++;
                String line = count + ". " + t;
                response.append(line).append("\n");
                System.out.println(line);
            }
        }

        if (count == 0) {
            String msg = "No matching tasks found.";
            response.append(msg).append("\n");
            System.out.println(msg);
        }

        ui.showLine();
        return response.toString().trim();
    }
}
