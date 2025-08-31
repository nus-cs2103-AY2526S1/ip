package poopiemeow.parser;

import java.time.format.DateTimeParseException;
import poopiemeow.exception.EmptyDescriptionException;
import poopiemeow.task.Deadline;
import poopiemeow.task.Event;
import poopiemeow.task.Task;
import poopiemeow.task.Todo;

/**
 * Parser for converting user input strings into Task objects.
 * This class provides static methods to parse different types of task commands
 * and create the appropriate Task subclass instances.
 *
 * @author tch1001
 * @version 1.0
 */
public class Parser {

    /**
     * Parses a user input string and creates the appropriate Task object.
     * This method analyzes the input string to determine the task type and
     * extracts the necessary information to create a Task instance.
     *
     * <p>The method supports the following input formats:</p>
     * <ul>
     *   <li><code>todo &lt;description&gt;</code> - Creates a Todo task</li>
     *   <li><code>deadline &lt;description&gt; /by &lt;deadline&gt;</code> - Creates a Deadline task</li>
     *   <li><code>event &lt;description&gt; /from &lt;startTime&gt; /to &lt;endTime&gt;</code> - Creates an Event task</li>
     * </ul>
     *
     * <p>If the input format is invalid or missing required components, the method
     * throws an appropriate exception with a descriptive error message.</p>
     *
     * @param input the user input string to parse
     * @return a Task object of the appropriate type
     * @throws EmptyDescriptionException if the task description is empty or the command format is invalid
     * @throws DateTimeParseException if the date/time format is invalid
     */
    public static Task parseTask(String input) throws EmptyDescriptionException, DateTimeParseException {
        if (input.startsWith("todo ")) {
            return new Todo(input.substring(5));
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.split(" /by ");
            if (parts.length != 2) {
                throw new EmptyDescriptionException("Please provide a deadline in the format: deadline <description> /by yyyy-MM-dd HHmm");
            }
            return new Deadline(parts[0].substring(9), parts[1]);
        } else if (input.startsWith("event ")) {
            String[] parts = input.split(" /from ");
            if (parts.length != 2) {
                throw new EmptyDescriptionException("Please provide event times in the format: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
            }
            String[] secondParts = parts[1].split(" /to ");
            if (secondParts.length != 2) {
                throw new EmptyDescriptionException("Please provide event times in the format: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
            }
            return new Event(parts[0].substring(6), secondParts[0], secondParts[1]);
        }
        throw new EmptyDescriptionException("Unknown command type!");
    }
}
