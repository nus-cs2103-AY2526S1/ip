package taro.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.corecomp.TaskList;
import taro.task.Task;
import taro.ui.Ui;

/**
 * Represents the {@code find} command which searches for tasks in the current task list
 * whose string representation contains the specified keyword.
 * Example:
 *     find book
 * lists all tasks whose string representation contains "book".
 */
public class FindCommand implements Command {
    /** The raw user input string containing the find command. */
    private final String input;

    /**
     * Constructs a {@code FindCommand} with the specified input string.
     *
     * @param input the raw user input that starts with {@code find}
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the {@code find} command.
     * Extracts the keyword from the input string, iterates through the {@link TaskList},
     * and displays all tasks that contain the keyword in their string representation.
     * If no tasks match, a message is displayed instead.
     *
     * @param tasks   the current list of tasks
     * @param ui      the user interface used to display messages
     * @param storage the storage handler (not used in this command)
     * @return {@code false} as the program should not terminate after execution
     * @throws TaroException if the keyword is missing or empty
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new TaroException("Opps!: Usage: find <keyword>");
        }

        ui.showLine();
        ui.show("  Here are the matching tasks in your list:");

        // Stream pipeline: filter tasks that match keyword, using the index to get
        List<Task> matchedTasks = IntStream.range(0, tasks.size())
                .mapToObj(tasks::get)
                .filter(t -> t.toString().contains(keyword))
                .toList();

        if (matchedTasks.isEmpty()) {
            ui.show("  (nothing)");
        } else {
            String output = matchedTasks.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("\n  "));
            ui.show("  " + output);
        }
        ui.showLine();
        return false;
    }
}
