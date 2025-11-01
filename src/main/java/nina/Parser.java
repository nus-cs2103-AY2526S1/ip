package nina;

import java.util.Optional;

import nina.command.AddCommand;
import nina.command.Command;
import nina.command.DeleteCommand;
import nina.command.FindCommand;
import nina.command.ListCommand;
import nina.command.MarkCommand;
import nina.command.UnmarkCommand;
import nina.task.DeadlineTask;
import nina.task.EventTask;
import nina.task.TodoTask;

/**
 * Parses raw user input strings and convert them to corresponding commands
 */
public class Parser {
    /**
     * Parses a raw input string into a command object
     * @param raw raw input from user
     * @return Command object corresponding to the input
     * @throws CommandException if the input is empty
     */
    public static Command parse(String raw)
            throws CommandException { //This method is assisted by ChatGPT in shortening
        String s = sanitize(raw);
        Optional<Command> c;
        if ((c = tryList(s)).isPresent()) {
            return c.get();
        }
        if ((c = tryMark(s)).isPresent()) {
            return c.get();
        }
        if ((c = tryUnmark(s)).isPresent()) {
            return c.get();
        }
        if ((c = tryDelete(s)).isPresent()) {
            return c.get();
        }
        if ((c = tryTodo(s)).isPresent()) {
            return c.get();
        }
        if ((c = tryDeadline(s)).isPresent()) {
            return c.get();
        }
        if ((c = tryEvent(s)).isPresent()) {
            return c.get();
        }
        if ((c = tryFind(s)).isPresent()) {
            return c.get();
        }
        throw new InvalidInputException("I cannot find the command OvO");
    }

    //the following 8 methods are broken down from parse by ChatGPT
    private static Optional<Command> tryList(String s) {
        if (!s.equals("list")) {
            return Optional.empty();
        }
        return Optional.of(new ListCommand());
    }

    private static Optional<Command> tryMark(String s) {
        return tryNumbered(s, "mark ",
            n -> new MarkCommand(n),
            "Only number can come after mark!");
    }

    private static Optional<Command> tryUnmark(String s) {
        return tryNumbered(s, "unmark ",
            n -> new UnmarkCommand(n),
            "Only number can come after unmark!");
    }

    private static Optional<Command> tryDelete(String s) {
        return tryNumbered(s, "delete ",
            n -> new DeleteCommand(n),
            "Only number can come after delete!");
    }

    private static Optional<Command> tryTodo(String s) {
        final String p = "todo ";
        if (!s.startsWith(p)) {
            return Optional.empty();
        }
        String desc = afterPrefix(s, p);
        if (desc.isEmpty()) {
            throw new InvalidInputException("Todo description must not be empty");
        }
        return Optional.of(new AddCommand(new TodoTask(desc)));
    }

    private static Optional<Command> tryDeadline(String s) {
        final String p = "deadline ";
        if (!s.startsWith(p)) {
            return Optional.empty();
        }
        String arg = afterPrefix(s, p);
        int cut = arg.indexOf("/by");
        if (cut < 0) {
            throw new InvalidInputException("Format: deadline <desc> /by <when> (YYYY-MM-DD)");
        }
        String desc = arg.substring(0, cut).trim();
        String by = arg.substring(cut + 3).trim();
        if (desc.isEmpty() || by.isEmpty()) {
            throw new InvalidInputException("Format: deadline <desc> /by <when> (YYYY-MM-DD)");
        }
        return Optional.of(new AddCommand(new DeadlineTask(desc, by)));
    }

    private static Optional<Command> tryEvent(String s) {
        final String p = "event ";
        if (!s.startsWith(p)) {
            return Optional.empty();
        }
        String arg = afterPrefix(s, p);
        int i = arg.indexOf("/from");
        int j = arg.indexOf("/to");
        if (i < 0 || j < 0 || j < i) {
            throw new InvalidInputException("Format: event <desc> /from <start> /to <end> (YYYY-MM-DD)");
        }
        String desc = arg.substring(0, i).trim();
        String from = arg.substring(i + 5, j).trim();
        String to = arg.substring(j + 3).trim();
        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new InvalidInputException("Format: event <desc> /from <start> /to <end> (YYYY-MM-DD)");
        }
        return Optional.of(new AddCommand(new EventTask(desc, from, to)));
    }

    private static Optional<Command> tryFind(String s) {
        final String p = "find ";
        if (!s.startsWith(p)) {
            return Optional.empty();
        }
        String kw = afterPrefix(s, p);
        if (kw.isEmpty()) {
            throw new InvalidInputException("FindCommand needs a keyword for searching");
        }
        return Optional.of(new FindCommand(kw));
    }

    // -------- helpers --------

    private static Optional<Command> tryNumbered(
        String s, String prefix, java.util.function.IntFunction<Command> ctor, String err) {
        if (!s.startsWith(prefix)) {
            return Optional.empty();
        }
        String arg = afterPrefix(s, prefix);
        try {
            int n = Integer.parseInt(arg);
            return Optional.of(ctor.apply(n));
        } catch (Exception e) {
            throw new InvalidInputException(err);
        }
    }

    private static String sanitize(String raw) throws CommandException {
        if (raw == null || raw.trim().isEmpty()) {
            throw new CommandException("The command is empty");
        }
        return raw.trim();
    }

    private static String afterPrefix(String s, String prefix) {
        return s.substring(prefix.length()).trim();
    }
}


