package john.core.command;

import john.model.Task;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

/**
 * Command that searches for tasks whose description contains a given substring.
 *
 * Matching:
 * - Case-insensitive substring match against each task's description/title.
 * - If you expose a Task#getTitle(), prefer matching on that; otherwise matching
 *   against Task#toString() is acceptable but may include status/type prefixes.
 *
 * Output:
 * - Returns a numbered list (1-based) of matching tasks, one per line.
 * - Returns "No matching tasks." when nothing matches.
 */
public class FindCommand implements Command {
    private final String desc;
    /**
     * Creates a FindCommand.
     *
     * @param desc substring to search for (case-insensitive); must not be blank
     */
    public FindCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the search over the provided task list.
     *
     * @param tasks   the task list to search
     * @param storage persistence backend (unused)
     * @param ui      user interface (unused)
     * @return a CommandResult containing either the numbered matches or
     *         "No matching tasks."
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) {
        String needle = desc.toLowerCase();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.toString().toLowerCase().contains(needle)) {
                sb.append(i + 1).append(". ").append(t).append('\n');
            }
        }

        String msg = (sb.length() == 0) ? "No matching tasks." : sb.toString().trim();
        return CommandResult.ok(msg);
    }
}
