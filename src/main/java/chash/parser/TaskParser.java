package chash.parser;

import chash.exception.ChashException;
import chash.command.CommandTypeEnum;
import chash.task.Deadline;
import chash.task.Event;
import chash.task.Task;
import chash.task.Todo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Parses tasks from CHASH-specific formats. */
public class TaskParser {
    //todo borrow book (does not need a pattern)
    //deadline return book /by Sunday
    private static final Pattern REGEX_DEADLINE =
            Pattern.compile("^(.+?)\\s+/by\\s+(.+)$");
    //event project meeting /from Mon 2pm /to 4pm
    private static final Pattern REGEX_EVENT =
            Pattern.compile("^(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)$");

    /**
     * Reconstructs a task from its export string.
     *
     * @param line Export string
     * @return Task instance
     * @throws ChashException If string format is invalid
     */
    public static Task fromExportString(String line) throws ChashException {
        assert line != null;

        String[] parts = line.split(" \\| ");
        //Sanity check
        if (parts.length < 3) {
            throw new ChashException("Invalid line");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case Todo.TASKTYPE:
            //Sanity check
            if (parts.length != 3) {
                throw new ChashException("Todo task invalid");
            }
            task = new Todo(description);
            break;

        case Deadline.TASKTYPE:
            //Sanity check
            if (parts.length != 4) {
                throw new ChashException("Deadline task invalid");
            }
            task = new Deadline(description, parts[3]);
            break;

        case Event.TASKTYPE:
            //Sanity check
            if (parts.length != 5) {
                throw new ChashException("Event task invalid");
            }
            task = new Event(description, parts[3], parts[4]);
            break;

        default:
            throw new ChashException("Invalid task type: " + type);
        }

        return task.setDone(isDone);
    }

    /**
     * Creates a task from a command type and argument string.
     *
     * @param type Command type
     * @param args Argument string
     * @return Task instance
     * @throws ChashException If arguments are invalid
     */
    public static Task fromChashString(CommandTypeEnum type, String args) throws ChashException {
        assert type != null;
        assert args != null;

        //Blocks use of invalid char '|' as it can affect export string parsing
        if (args.contains("|")) {
            throw new ChashException("Invalid character '|' detected in task details.\n");
        }

        Matcher m;
        switch (type) {
        case TODO:
            return new Todo(args);

        case DEADLINE:
            m = TaskParser.REGEX_DEADLINE.matcher(args);
            if (m.matches()) {
                return new Deadline(m.group(1), m.group(2));
            } else {
                throw new ChashException("Invalid deadline arguments");
            }

        case EVENT:
            m = TaskParser.REGEX_EVENT.matcher(args);
            if (m.matches()) {
                return new Event(m.group(1), m.group(2), m.group(3));
            } else {
                throw new ChashException("Invalid event arguments");
            }

        default:
            throw new ChashException("Invalid command type: " + type.toString());
        }
    }
}
