package marvin.command;

/**
 * Encapsulates printable CLI output and raw GUI string output for use in methods
 */
public class CommandResult {
    private final Runnable printCommand;
    private final String message;

    /**
     * Instantiates a CommandResult
     * @param printCommand A runnable function that prints to UI.
     * @param message A string output suitable for GUI display.
     */
    public CommandResult(Runnable printCommand, String message) {
        this.printCommand = printCommand;
        this.message = message;
    }

    /**
     * Execute the supplied printCommand to print out a value to the CLI
     */
    public void printResponse() {
        this.printCommand.run();
    }

    public String getMessage() {
        return this.message;
    }
}
