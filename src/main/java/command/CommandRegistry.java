package command;

/**
 * A utility class responsible for providing a list of all available commands in
 * the Lmbd application. This acts as a centralized registry for commands. This
 * solution was suggested by ChatGPT.
 */

public class CommandRegistry {
    /** Get all commands */
    public static Command[] getCommands() {
        return new Command[]{new ByeCommand(), new DeadlineCommand(), new EventCommand(), new FindCommand(),
            new HelpCommand(), new ListCommand(), new MarkCommand(), new RemoveCommand(), new TodoCommand(),
            new UnmarkCommand()};
    }
}
