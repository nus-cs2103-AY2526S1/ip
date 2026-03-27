package sunday;

import java.util.Objects;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UpdateCommand;
import exceptions.EmptyCommandException;
import exceptions.SundayException;
import exceptions.UnknownException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Parses user input into commands and reconstructs tasks from saved lines.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Parses a raw user input line into a Command.
     *
     * @param fullCommand full user input
     * @return the command
     * @throws SundayException if input is empty or unknown
     */
    public static Command parse(String fullCommand) throws SundayException {
        assert fullCommand != null : "parse called with null input";

        if (fullCommand.isEmpty()) {
            throw new EmptyCommandException();
        }

        String[] parts = fullCommand.trim().split(" ", 2);
        String arg = parts.length > 1 ? parts[1] : "";

        switch(parts[0]) {
        case "bye": return new ByeCommand();
        case "list": return new ListCommand();
        case "mark": return new MarkCommand(true, arg);
        case "unmark": return new MarkCommand(false, arg);
        case "delete": return new DeleteCommand(arg);
        case "todo": return new TodoCommand(arg);
        case "deadline": return new DeadlineCommand(arg);
        case "event": return new EventCommand(arg);
        case "find": return new FindCommand(arg);
        case "update": return new UpdateCommand(arg);
        default: throw new UnknownException();
        }
    }

    /**
     * Parses a serialized task line back into a Task.
     *
     * @param line serialized task line
     * @return reconstructed task
     */
    public static Task parseTaskLine(String line) {
        assert line != null : "parseTaskLine called with null input";

        String[] parts = line.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        if (parts.length < 3) {
            throw new IllegalArgumentException("Wrong line: " + line);
        }

        String type = parts[0];
        boolean done = Objects.equals(parts[1], "1");
        String desc = parts[2];

        switch (type) {
        case ("T"): return new Todo(desc, done);

        case ("D"):
            if (parts.length < 4) {
                throw new IllegalArgumentException("Deadline missing.");
            }
            return new Deadline(desc, DateTimeHelper.parseDate(parts[3]), done);

        case ("E"):
            if (parts.length < 5) {
                throw new IllegalArgumentException("No Start/End time");
            }
            return new Event(desc,
                    DateTimeHelper.parseDateTime(parts[3]),
                    DateTimeHelper.parseDateTime(parts[4]),
                    done);

        default: throw new IllegalArgumentException("Unknown Task.");
        }
    }
}
