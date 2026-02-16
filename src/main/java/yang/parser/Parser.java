package yang.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import yang.YangException;
import yang.task.Deadline;
import yang.task.Event;
import yang.task.Task;
import yang.task.TaskList;
import yang.task.Todo;

/**
 * Parses raw user input strings into executable commands.
 * <p>
 * The {@code Parser} interprets command keywords such as {@code todo}, {@code deadline},
 * {@code event}, {@code mark}, {@code unmark}, {@code delete}, and {@code list},
 * then returns a corresponding {@link CommandResult}. Parsing errors or malformed
 * arguments are signaled via {@link YangException}.
 * </p>
 */
public class Parser {

    /**
     * Parses the given user input and executes the corresponding command
     * on the provided task list.
     *
     * @param input raw user input string
     * @param tasks the task list to modify
     * @return result of executing the parsed command
     * @throws YangException if the input is invalid or refers to an out-of-range task
     */
    //Consulted chatgpt to improve on my existing working apply method to make it shorter
    public static CommandResult apply(String input, TaskList tasks) throws YangException {
        assert input != null : "input cannot be null";
        assert tasks != null : "tasks list must be initialised";

        final String raw = input;
        input = input.trim();

        String[] tok = input.split("\\s+", 2);
        String cmd = tok[0];
        String rest = tok.length > 1 ? tok[1].trim() : "";

        switch (cmd) {
        case "list":
            return CommandResult.list();

        case "todo": {
            String desc = requireNonEmpty(rest, "☹ Ohno!! The description of a todo cannot be empty.");
            Task t = new Todo(desc);
            tasks.add(t);
            return CommandResult.added(t);
        }

        case "deadline": {
            String[] p = rest.split("/by", 2);
            if (p.length < 2) {
                throw new YangException("Usage: deadline <desc> /by <yyyy-mm-dd>");
            }
            String desc = requireNonEmpty(p[0], "☹ Ohno!! The description of a deadline cannot be empty.");
            String byStr = requireNonEmpty(p[1], "☹ Ohno!!! Deadline date is missing (use yyyy-mm-dd).");
            Task t = new Deadline(desc, parseIsoDate(byStr));
            tasks.add(t);
            return CommandResult.added(t);
        }

        case "event": {
            String[] p = rest.split("/at", 2);
            if (p.length < 2) {
                throw new YangException("Usage: event <desc> /at <yyyy-mm-dd>");
            }
            String desc = requireNonEmpty(p[0], "☹ Ohno!!! The description of an event cannot be empty.");
            String atStr = requireNonEmpty(p[1], "☹ Ohno!!! Event date is missing (use yyyy-mm-dd).");
            Task t = new Event(desc, parseIsoDate(atStr));
            tasks.add(t);
            return CommandResult.added(t);
        }

        case "mark": {
            int idx = parseIndex(input, tasks.size());
            tasks.mark(idx);
            return CommandResult.marked(tasks.get(idx));
        }

        case "unmark": {
            int idx = parseIndex(input, tasks.size());
            tasks.unmark(idx);
            return CommandResult.unmarked(tasks.get(idx));
        }

        case "delete": {
            int idx = parseIndex(input, tasks.size());
            Task removed = tasks.remove(idx);
            return CommandResult.deleted(removed);
        }

        case "find": {
            String keyword = requireNonEmpty(rest, "☹ OOPS!!! The keyword for find cannot be empty.").trim();
            return CommandResult.found(keyword);
        }

        default:
            throw new YangException("Not a valid command: " + raw);
        }
    }

    private static String requireNonEmpty(String s, String msg) throws YangException {
        if (s == null || s.trim().isEmpty()) {
            throw new YangException(msg);
        }
        return s.trim();
    }

    private static LocalDate parseIsoDate(String s) throws YangException {
        try {
            return LocalDate.parse(s.trim());
        } catch (DateTimeParseException e) {
            throw new YangException("☹ OOPS!!! Use date format yyyy-mm-dd.");
        }
    }

    private static int parseIndex(String input, int size) throws YangException {
        String[] parts = input.split("\\s+");
        if (parts.length < 2) {
            throw new YangException("☹ OOPS!!! Please provide an item number.");
        }
        try {
            int idx = Integer.parseInt(parts[1]) - 1;
            if (idx < 0 || idx >= size) {
                throw new YangException("☹ OOPS!!! That task number is out of range.");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new YangException("☹ OOPS!!! Task number must be an integer.");
        }
    }
}
