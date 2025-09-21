package itachi.command;

import java.util.Stack;

/**
 * Stores the commands inputted by the user into a {@link #history} such that
 * the manipulation of the history of commands is possible.
 */
public class CommandHistory {
    private static final CommandHistory instance = new CommandHistory();
    private final Stack<Command> history;

    public CommandHistory() {
        this.history = new Stack<>();
    }

    public static CommandHistory getInstance() {
        return instance;
    }

    /**
     * Removes the most recent command from the {@link #history}
     * @return the most recent command
     */
    public Command pop() {
        if (history.isEmpty()) {
            return null;
        }
        return history.pop();
    }

    public void push(Command command) {
        history.push(command);
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }
}
