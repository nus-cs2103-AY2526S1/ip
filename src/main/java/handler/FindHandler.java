package handler;

import hachi.CommandCode;
import hachi.TaskList;
import hachi.Ui;

/**
 * Handles the {@link CommandCode#FIND} command.
 *
 * <p>This handler expects user input of the form:
 * <pre>
 *     find &lt;keyword&gt;
 * </pre>
 * Example:
 * <pre>
 *     find report
 * </pre>
 *
 * <p>If a keyword is provided, the handler delegates to
 * {@link TaskList#findTasksByKeyword(String)} to search for matching tasks.
 * Otherwise, a failure message is returned.</p>
 */
public class FindHandler implements Manager {
    private final String input;
    private final Ui ui;

    /**
     * Constructs a {@code FindHandler}.
     *
     * @param input the raw user input string
     * @param ui    the UI utility for failure messages
     */
    public FindHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    /**
     * Executes the find command by searching for tasks whose descriptions
     * contain the given keyword.
     *
     * @param tasks the task list to search
     * @return a formatted string of matching tasks if a keyword was provided,
     *         or a failure message if none was given
     */
    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.split(" ", 2);
        if (parts.length > 1) {
            String desc = parts[1].trim();
            return tasks.findTasksByKeyword(desc);
        } else {
            return Ui.failure(CommandCode.FIND);
        }
    }
}
