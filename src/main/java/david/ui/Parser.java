package david.ui;

import david.command.AddCommand;
import david.command.Command;
import david.command.DeleteCommand;
import david.command.ExitCommand;
import david.command.FindCommand;
import david.command.ListCommand;
import david.command.MarkCommand;
import david.command.UndoCommand;
import david.command.UnmarkCommand;
import david.exception.DavidException;

/**
 * Makes sense of the command line.
 */
public class Parser {

    /**
     * @param command The whole command.
     * @return A command object for subsequent execution.
     * @throws DavidException If parsing the string fails.
     */
    public static Command parse(String command) throws DavidException {
        String[] strArr = command.split(" ", 2);
        assert strArr.length > 0 : "String array should never be empty";
        String op = strArr[0];

        switch (op) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "undo":
            return new UndoCommand();

        case "mark":
            return new MarkCommand(command);

        case "unmark":
            return new UnmarkCommand(command);

        case "delete":
            return new DeleteCommand(command);

        case "find":
            return new FindCommand(command);

        default:
            return new AddCommand(command);
        }
    }
}
