package lynx.command;

/**
 * Represents a user command that accepts modifiers.
 * Stores the input and messages for different types of execution.
 */
public abstract class LynxCommand {

    private String input;

    public LynxCommand(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    // Header message for case "/all"
    public abstract String getMessageForAll();

    // Header message for case "/on".
    public abstract String getMessageByDate();

    // Header message for case "/id".
    public abstract String getMessageById();

    // Header message for case /status.
    public abstract String getMessageByStatus();

    // Header message for default case (by keyword).
    public abstract String getMessageByKeyword();

}
