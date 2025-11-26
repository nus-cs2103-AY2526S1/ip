package aries.command;

/**
 * Represents the result of executing a command.
 */
public class CommandResult {
    private final String response;
    private final boolean isChanged;
    private final boolean isExit;

    /**
     * Constructs a CommandResult with the specified response, change status, and exit status.
     *
     * @param response  The response message to be shown to the user.
     * @param isChanged A boolean indicating whether the task list was changed.
     * @param isExit    A boolean indicating whether the application should exit.
     */
    public CommandResult(String response, boolean isChanged, boolean isExit) {
        this.response = response;
        this.isChanged = isChanged;
        this.isExit = isExit;
    }

    public String getResponse() {
        return response;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public boolean isExit() {
        return isExit;
    }
}
