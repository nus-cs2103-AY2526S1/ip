package tom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import javafx.util.Pair;

/**
 * Processes the user's input.
 */
public class Parser {
    private final String command;
    private final String[] words;

    /**
     * Constructs a Parser.
     * @param input User input.
     */
    public Parser(String input) {
        String[] line = input.split("\\s+", 2);
        this.command = line[0].strip();
        this.words = line;
    }

    /**
     * Returns a pair consisting of two pairs.
     * The first pair contains the command given by the user and the rest of the input.
     * The second pair consists of two Optionals. The first Optional is the task number for
     * add/delete/mark/unmark commands, while the second is the new task to be added for the other commands.
     * @return Command, task number, and new task.
     * @throws TomException If command is invalid.
     */
    public Pair<Pair<String, String>, Pair<Optional<Integer>, Optional<Task>>> parse() throws TomException {
        Optional<Task> task = Optional.empty();
        Optional<Integer> idx = Optional.empty();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String args = "";
        switch(command) {
        case "bye":
            validateArgs(words.length != 1, "Bye takes no description");
            break;
        case "list":
            validateArgs(words.length != 1, "List takes no description");
            break;
        case "mark", "unmark", "prioritise", "delete":
            idx = parseNumeric();
            break;
        case "todo":
            if (words.length != 2) {
                throw new TomException("Todo requires a description");
            }
            String description = words[1].strip();
            if (description.contains("|")) {
                throw new TomException("Description contains invalid characters");
            }
            task = Optional.of(new Todo(description));
            break;
        case "deadline":
            task = parseDeadline(inputFormatter);
            break;
        case "event":
            task = parseEvent(inputFormatter);
            break;
        case "find":
            args = words[1].strip();
            break;
        default:
            throw new TomException("Command not found!");
        }
        return new Pair<>(new Pair<>(command, args), new Pair<>(idx, task));
    }

    private void validateArgs(boolean condition, String message) throws TomException {
        if (condition) {
            throw new TomException(message);
        }
    }
    private Optional<Integer> parseNumeric() throws TomException {
        validateArgs(words.length != 2, "1 task required to " + command);
        try {
            return Optional.of(Integer.parseInt(words[1].strip()));
        } catch (NumberFormatException e) {
            throw new TomException("Index is not a number!");
        }
    }
    private Optional<Task> parseDeadline(DateTimeFormatter inputFormatter) throws TomException {
        validateArgs(words.length != 2,
                "Deadline requires a date by which the task must be completed");
        String[] parts = words[1].split("/by");
        validateArgs(parts.length != 2,
                "Deadline requires a date by which the task must be completed");
        String description = parts[0].strip();
        if (description.contains("|")) {
            throw new TomException("Description contains invalid characters");
        }
        try {
            LocalDateTime by = LocalDateTime.parse(parts[1].strip(), inputFormatter);
            return Optional.of(new Deadline(description, by));
        } catch (DateTimeParseException e) {
            throw new TomException("Deadline is not a valid date!");
        }
    }
    private Optional<Task> parseEvent(DateTimeFormatter inputFormatter) throws TomException {
        validateArgs(words.length != 2, "Event requires a description, start and end dates");
        if (words[1].indexOf("/from") > words[1].indexOf("/to")) {
            throw new TomException("Event requires a date by which the task must be completed");
        }
        String[] parts1 = words[1].split("/from|/to");
        validateArgs(parts1.length != 3, "Event requires a description, start and end dates");
        String description = parts1[0].strip();
        if (description.contains("|")) {
            throw new TomException("Description contains invalid characters");
        }
        try {
            LocalDateTime from = LocalDateTime.parse(parts1[1].strip(), inputFormatter);
            LocalDateTime to = LocalDateTime.parse(parts1[2].strip(), inputFormatter);
            if (to.isBefore(from)) {
                throw new TomException("Event must start before it ends!");
            }
            return Optional.of(new Event(description, from, to));
        } catch (DateTimeParseException e) {
            throw new TomException("Invalid date format!");
        }
    }
}
