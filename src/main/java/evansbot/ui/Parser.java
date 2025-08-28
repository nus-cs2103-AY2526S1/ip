package evansbot.ui;

import evansbot.Exceptions.EvansBotException;
import evansbot.Exceptions.InvalidCommandException;
import evansbot.Exceptions.InvalidDeadlineException;
import evansbot.Exceptions.InvalidEventException;
import evansbot.command.*;

public class Parser {
    public static Command parse(String input) throws EvansBotException {
        String trimmed = input.trim();

        if (trimmed.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (trimmed.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (trimmed.startsWith("mark ")) {
            int index = Integer.parseInt(trimmed.split(" ")[1]);
            return new MarkCommand(index);
        } else if (trimmed.startsWith("unmark ")) {
            int index = Integer.parseInt(trimmed.split(" ")[1]);
            return new UnmarkCommand(index);
        } else if (trimmed.startsWith("todo ")) {
            return new AddTodoCommand(trimmed.substring(5));
        } else if (trimmed.startsWith("deadline ")) {
            //split by /by
            String[] information = input.substring(9).split(" /by ", 2);
            if (information.length < 2) {
                throw new InvalidDeadlineException();
            }
            String description = information[0];
            String by = information[1];
            return new AddDeadlineCommand(description, by);
        } else if (trimmed.startsWith("event ")) {
            //split by /from and /to
            String[] information = input.substring(6).split(" /from | /to ", 3);
            if (information.length < 3) {
                throw new InvalidEventException();
            }
            String description = information[0];
            String from = information[1];
            String to = information[2];
            return new AddEventCommand(description, from, to);
        } else if (trimmed.startsWith("delete ")) {
            int index = Integer.parseInt(trimmed.split(" ")[1]);
            return new DeleteCommand(index);
        } else {
            throw new InvalidCommandException();
        }
    }

}
