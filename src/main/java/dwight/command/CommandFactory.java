package dwight.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides a factory for creating and retrieving command instances based on user input keywords.
 */
public class CommandFactory {

    /** Mapping of command keywords to their corresponding command instances. */
    private static final Map<String, Command> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("list", new ListCommand());
        COMMANDS.put("mark", new MarkCommand());
        COMMANDS.put("unmark", new UnmarkCommand());
        COMMANDS.put("delete", new DeleteCommand());
        COMMANDS.put("new", new NewCommand());
        COMMANDS.put("find", new FindCommand());

        assert COMMANDS.containsKey("list") : "Static map init error: Missing 'list' command.";
        assert COMMANDS.containsKey("mark") : "Static map init error: Missing 'mark' command.";
        assert COMMANDS.containsKey("unmark") : "Static map init error: Missing 'unmark' command.";
        assert COMMANDS.containsKey("delete") : "Static map init error: Missing 'delete' command.";
        assert COMMANDS.containsKey("new") : "Static map init error: Missing 'new' command.";
        assert COMMANDS.containsKey("find") : "Static map init error: Missing 'find' command.";
    }

    /**
     * Returns the command associated with the specified keyword.
     *
     * @param type The keyword representing the command to retrieve.
     * @return The corresponding {@code Command} instance.
     * @throws UnknownCommandException If the keyword does not match any known command.
     */
    public static Command getCommand(String type) throws UnknownCommandException {
        Command cmd = COMMANDS.get(type);
        if (cmd == null) {
            throw new UnknownCommandException(type);
        }
        return cmd;
    }
}
