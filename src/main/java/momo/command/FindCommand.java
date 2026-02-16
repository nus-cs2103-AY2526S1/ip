package momo.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import momo.storage.Storage;
import momo.task.Task;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Represents a command that finds all tasks containing a specified keyword.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Creates a new {@code FindCommand}.
     *
     * @param keyword the keyword to find in task descriptions.
     */
    public FindCommand(String keyword) {
        assert keyword != null : "Keyword must not be null";
        assert !keyword.trim().isEmpty() : "Keyword must not be empty";

        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching the given {@link TaskList} for tasks
     * that contain the keyword and returns the matching tasks as a string.
     *
     * @param tasks the task list to search through.
     * @param ui the user interface used to generate messages (not used in this command).
     * @param storage the storage component (not modified by this command).
     * @return the matching tasks as a string.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";

        List<Task> foundTasks = tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();

        String foundTasksMessage = IntStream.range(0, foundTasks.size())
                .mapToObj(x -> String.format("%d.%s", x + 1, foundTasks.get(x).toString()))
                .collect(Collectors.joining("\n"));

        return "Here are the matching tasks in your list:\n" + foundTasksMessage;
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code false}, as finding tasks does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
