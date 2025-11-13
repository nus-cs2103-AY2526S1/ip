package john.core.command;

/**
 * Immutable result of executing a command.
 * <p>
 * Purpose:
 * - Carries a user-facing feedback message.
 * - Indicates whether the application should exit after handling the command.
 * <p>
 * Usage:
 * - CommandResult.ok("message") for normal completion.
 * - CommandResult.exit("message") to request termination.
 * <p>
 * @param feedback user-facing message to display (should be non-null; may be empty)
 * @param exit     true if the application should terminate, false otherwise
 */
public record CommandResult(String feedback, boolean exit) {

    /**
     * Returns a non-exiting result with the given message.
     *
     * @param msg user-facing message
     * @return a CommandResult with exit set to false
     */
    public static CommandResult ok(String msg) {
        return new CommandResult(msg, false);
    }

    /**
     * Returns an exiting result with the given message.
     *
     * @param msg user-facing message
     * @return a CommandResult with exit set to true
     */
    public static CommandResult exit(String msg) {
        return new CommandResult(msg, true);
    }
}