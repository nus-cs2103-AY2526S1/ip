package john.core.command;

import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

/**
 * Command that lists all tasks in a numbered, 1-based format.
 * <p>
 * Responsibilities:
 * - Read the current TaskList.
 * - Produce a user-facing, newline-separated list like:
 * "1. <task>\n2. <task>\n..."
 * - If there are no tasks, return "No tasks yet."
 * <p>
 * Side effects:
 * - None. This command does not mutate tasks or call storage.
 */
public class ListCommand implements Command {

    /**
     * Builds a numbered listing of all tasks and returns it as a CommandResult.
     *
     * @param tasks   the task list to display
     * @param storage persistence backend (unused)
     * @param ui      user interface (unused)
     * @return a CommandResult containing either the numbered list or "No tasks yet."
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append('\n');
        }
        String body = (sb.length() == 0) ? "No tasks yet." : sb.toString().trim();
        return CommandResult.ok(body);
    }
}