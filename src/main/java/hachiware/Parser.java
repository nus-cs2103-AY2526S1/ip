package hachiware;

/**
 * Parses user input into available Hachiware command objects
 * which would execute the specific command.
 */
public class Parser {

    /**
     * Parses a full command string entered by the user
     * and returns the corresponding Command object.
     *
     * @param fullCommand the raw input entered by the user
     * @return a Command object corresponding to the parsed command
     * @throws HachiwareException if the command is not recognized
     */
    public static Command parse(String fullCommand) throws HachiwareException {

        //Splits the user input into at most 2 parts:
        //1.The first token is treated as the command.
        //2.The remainder (if any) is treated as the arguments.

        assert fullCommand != null : "fullCommand should never be null";
        String[] words = fullCommand.split(" ", 2);
        String command = words[0];


        //Retrieves the rest of the input after the command.
        //If no additional arguments are provided, assigns an empty string instead.
        String args = words.length > 1 ? words[1] : "";

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(args);
        case "unmark":
            return new UnmarkCommand(args);
        case "todo":
            return new TodoCommand(args);
        case "deadline":
            return new DeadlineCommand(args);
        case "event":
            return new EventCommand(args);
        case "delete":
            return new DeleteCommand(args);
        case "find":
            return new FindCommand(args);
        case "priority":
            return new PriorityCommand(args);
        default:
            throw new HachiwareException("MEOW! Hachiware.Hachiware.Command doesn't exist.");
        }
    }
}
