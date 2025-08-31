package poopiemeow.parser;

import java.time.format.DateTimeParseException;
import poopiemeow.exception.EmptyDescriptionException;
import poopiemeow.task.Deadline;
import poopiemeow.task.Event;
import poopiemeow.task.Task;
import poopiemeow.task.Todo;

public class Parser {
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