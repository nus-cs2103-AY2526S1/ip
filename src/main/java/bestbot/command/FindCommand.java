package bestbot.command;

import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.Task;
import bestbot.task.TaskList;
import bestbot.exception.BestbotException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command that finds tasks whose descriptions contain a given keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a {@code FindCommand}.
     *
     * @param keyword Keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by finding tasks containing the keyword and displaying them.
     *
     * @param tasks   The task list to search.
     * @param ui      The UI used to display messages to the user.
     * @param storage The storage (unused in this command).
     * @throws BestbotException If the keyword is empty or blank.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException {
        if (keyword.isBlank()) {
            throw new BestbotException("The keyword for find cannot be empty.");
        }

        List<Task> matchingTasks = tasks.getTasks().stream()
                .filter(task -> task.toString().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        ui.showFoundTasks(matchingTasks);
    }

    /**
     * Returns {@code false} as this command does not terminate the program.
     *
     * @return {@code false}.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

