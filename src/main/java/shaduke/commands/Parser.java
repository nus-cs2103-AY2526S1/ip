package shaduke.commands;

import shaduke.ShadukeException;
import shaduke.tasks.Deadline;
import shaduke.tasks.Event;
import shaduke.tasks.Todo;

/**
 * Represents the conversion of an input into its respective command.
 */
public class Parser {

    /**
     * Convert the user input into the command to be executed.
     *
     * @param command the user input.
     * @return the command to be executed.
     * @throws ShadukeException the exception unique to this application.
     */
    public static Command parse(String command) throws ShadukeException {
        String[] words = command.split(" ", 2);
        assert words.length >= 1: "Parser error: command.split should always produce at least one word";
        String keyword = words[0];
        String rest = (words.length > 1) ? words[1] : null;

        switch (keyword) {
        case "bye": return new ExitCommand();
        case "list": return new ListCommand();
        case "todo": return parseTodo(rest);
        case "deadline": return parseDeadline(rest);
        case "event": return parseEvent(rest);
        case "mark": return new MarkCommand(Integer.parseInt(rest));
        case "unmark": return new UnmarkCommand(Integer.parseInt(rest));
        case "delete": return new DeleteCommand(Integer.parseInt(rest));
        case "find": return new FindCommand(rest);
        default: throw new ShadukeException("I don't understand like when Shohib talks to me");
        }
    }

    private static Command parseTodo(String rest) {
        if (rest == null || rest.trim().isEmpty()) {
            throw new ShadukeException("You need to add a description!");
        }
        return new AddCommand(new Todo(rest));
    }

    private static Command parseDeadline(String rest) {
        if (rest == null) {
            throw new ShadukeException("Need '/by!'");
        }
        String[] deadParts = rest.split(" /by ");
        if (deadParts.length < 2) {
            throw new ShadukeException("Missing description or date!");
        }
        return new AddCommand(new Deadline(deadParts[0], deadParts[1]));
    }

    private static Command parseEvent(String rest) {
        String[] halves = rest.split(" /to ");
        assert halves.length == 2: "Halves means split in 2";
        String name = halves[0].split(" /from ")[0];
        String from = halves[0].split(" /from ")[1];
        String to = halves[1];
        return new AddCommand(new Event(name, from, to));
    }
}
