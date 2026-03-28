package parser;

import java.util.regex.Pattern;

import commands.ArchiveCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import exceptions.DawaeException;
import taskstuff.Deadline;
import taskstuff.Event;
import taskstuff.Task;
import taskstuff.Todo;

public class Parser {
    
    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    
    /**
     * Parses user input into command for execution.
     *
     * @param inputt full user input string.
     * @return the command based on the user input.
     * @throws DawaeException if the command word is unknown or malformed.
     */
    public static Command parse(String inputt) throws DawaeException {
        assert inputt != null : "Input to parse should not be null";
        String[] splitted = inputt.trim().split(" ", 2); //splits into command word n args
        if (inputt.trim().isEmpty()) throw new DawaeException("Brah. Why never say anything.\uD83D\uDE10");
        String commandWord = splitted[0].toLowerCase();
        String args;
        if (splitted.length == 1) {
            args = "";
        } else {
            args = splitted[1];
        }
        assert commandWord != null;
        return switch (commandWord) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "find" -> new FindCommand(args);
            case "mark" -> new MarkCommand(args);
            case "unmark" -> new UnmarkCommand(args);
            case "delete" -> new DeleteCommand(args);
            case "todo" -> new TodoCommand(args);
            case "deadline" -> new DeadlineCommand(args);
            case "event" -> new EventCommand(args);
            case "archive" -> new ArchiveCommand();
            default -> throw new DawaeException("I dont understand u. >:[");
        };
    }
    
    /**
     * Parses a single save-file line into a Task instance.
     * 
     * @param line one line from the save file.
     * @return the reconstructed task.
     * @throws DawaeException if the line is corrupted or uses an unknown type.
     */
    public static Task parseSaved(String line) throws DawaeException {
        // Expected example formats (adjust to match your existing taskstuff.Task#toSaveString):
        // T | 1 | read book
        // D | 0 | return book | 2019-10-15T18:00
        // E | 1 | project meeting | 2019-10-15T18:00 to 2019-10-15T20:00P
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) throw new DawaeException("Corrupted save line: " + line);
        assert parts.length >= 3 : "parts should have at least 3 elements";
        String type = parts[0];
        boolean isDone = parts[1].trim().equals("1");
        String desc = parts[2];
        assert type != null && !type.isEmpty() : "Task type should not be null or empty";
        assert desc != null && !desc.isEmpty() : "Task description should not be null or empty";
        switch (type) {
        case "T":
            Todo t = new Todo(desc);
            if (isDone) t.markDone();
            return t;
        case "D":
            if (parts.length < 4) throw new DawaeException("Corrupted deadline: " + line);
            Deadline d = new Deadline(desc, parts[3]); // assumes your taskstuff.Deadline ctor takes (desc, byString)
            if (isDone) d.markDone();
            return d;
        case "E":
            if (parts.length < 5) throw new DawaeException("Corrupted event: " + line);
            Event e = new Event(desc, parts[3], parts[4]); // assumes (desc, from, to)
            if (isDone) e.markDone();
            return e;
        default:
            throw new DawaeException("Unknown task type in save: " + type);
        }
    }
}
