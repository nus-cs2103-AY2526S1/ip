package sunoo.command;

import java.util.ArrayList;

import sunoo.task.Task;
import sunoo.task.TaskList;
import sunoo.ui.Ui;

/**
 * Represents an executable command to find tasks that has a description containing a keyword.
 */
public class FindCommand extends Command {

    /** Keyword to search */
    private final String keyword;

    /**
     * Creates a FindCommand with the keyword.
     *
     * @param keyword Keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * {@inheritDoc}
     * <p>Instructs Ui to show tasks that has a description containing the keyword.</p>
     */
    @Override
    public String execute(TaskList tasks) {
        ArrayList<String> matchedTasksToShow = new ArrayList<>();
        int i = 1;
        for (Task task : tasks.getTasks()) {
            if (task.descriptionContainsKeyword(keyword)) {
                matchedTasksToShow.add(i + ". " + task);
                i++;
            }
        }
        String response = Ui.joinLines(matchedTasksToShow);
        response = Ui.joinLines(
                "ENGENE, here are the matching tasks for " + keyword + ":",
                response);
        return Ui.wrapWithHorizontalLines(response);
    }

    /**
     * {@inheritDoc}
     *
     * @return false.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
