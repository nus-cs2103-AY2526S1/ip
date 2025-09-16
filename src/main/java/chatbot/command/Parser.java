package chatbot.command;

import java.util.Map;
import java.util.function.Function;

/**
 * Parses user input and delegates execution to the appropriate command.
 * The {@code Parser} uses a mapping of command keywords to their
 * corresponding handler functions.
 */
public class Parser {
    private final Map<String, Function<String[], String>> commands;
    public Parser(Map<String, Function<String[], String>> commands) {
        this.commands = commands;
    }

    /**
     * Returns the command associated with the given input string.
     *
     * This method looks up the input in the command map given during construction.
     * If a command is found, it returns that command. Otherwise, it returns
     * the command mapped to the empty string ("").
     *
     * @param input the input string representing the command
     * @return the Consumer representing the command to be executed
     */

    public Function<String[], String> getCommand(String... input) {
        Function<String[], String> command = commands.get(input[0]);
        if (command != null) {
            return command;
        }
        return commands.get("");
    }
}
