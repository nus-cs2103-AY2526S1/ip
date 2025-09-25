package heimerdinger.command;

import heimerdinger.HeimerdingerException;
import heimerdinger.Storage;
import heimerdinger.Ui;
import heimerdinger.task.TaskList;


/**
 * Base type for all user-invoked commands.
 *
 * <p>A {@code Command} encapsulates a single action parsed from user input,
 * and executes against the current {@link TaskList}, displays messages via {@link Ui},
 * and persists changes through {@link Storage}.</p>
 *
 * <h2>Lifecycle</h2>
 * <ol>
 *   <li>A user enters a command string.</li>
 *   <li>The parser creates a concrete {@code Command} with its arguments.</li>
 *   <li>{@link #execute(TaskList, Ui, Storage)} is invoked to perform the action.</li>
 * </ol>
 *
 * <p>Subclasses must implement {@link #execute(TaskList, Ui, Storage)} to define behavior.</p>
 */
public abstract class Command {

    /**
     * Executes this command.
     *
     * <p>Concrete implementations typically mutate {@code tasks}, show a message via {@code ui},
     * and persist changes through {@code storage}. Some commands (e.g., {@link ExitCommand})
     * may instead just signal application termination without modifying tasks.</p>
     *
     * @param tasks   the live task list to read and modify; must not be {@code null}
     * @param ui      the UI sink used to display messages; must not be {@code null}
     * @param storage the persistence layer used to save any changes; must not be {@code null}
     * @throws HeimerdingerException if validation fails, persistence fails, or a domain error occurs
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws HeimerdingerException;

    /**
     * Indicates whether this command signals application exit.
     *
     * @return {@code true} if this command should terminate the program;
     *         {@code false} otherwise
     */
    public boolean isExit() {
        return false;
    }


    /**
     * Checks whether a given string represents a valid number.
     *
     * <p>This utility is commonly used by commands like {@code mark}, {@code unmark},
     * and {@code delete} to validate that the user has provided a numeric index.</p>
     *
     * @param maybeNumber the string to test; may be {@code null}
     * @return {@code true} if the string can be parsed as a number; {@code false} otherwise
     */
    public static boolean isNumeric(String maybeNumber) {
        if (maybeNumber == null) {
            return false;
        }
        try {
            Double.parseDouble(maybeNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
