package jarvis.parser;
import jarvis.commands.*;
import jarvis.tasks.DeadlinesTask;
import jarvis.tasks.EventsTask;
import jarvis.tasks.Task;
import jarvis.tasks.ToDoTask;
import jarvis.ui.DarrenAssistantException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Parses user input (interactive commands) and stored lines (for persistence)
 * into domain objects used by the application.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Convert raw user commands into concrete {@link Command} objects.</li>
 *   <li>Parse date/time strings typed by the user into {@link LocalDateTime}.</li>
 *   <li>Reconstruct {@link Task} objects from storage lines.</li>
 * </ul>
 */
public class Parser {
    /**
     * Parses a single line of user input into a {@link Command}.
     *
     * <p>Supported commands (case-insensitive):</p>
     * <ul>
     *   <li><code>bye</code></li>
     *   <li><code>list</code></li>
     *   <li><code>todo &lt;desc&gt;</code></li>
     *   <li><code>deadline &lt;desc&gt; /by &lt;when&gt;</code></li>
     *   <li><code>event &lt;desc&gt; /from &lt;start&gt; /to &lt;end&gt;</code></li>
     *   <li><code>place &lt;desc&gt; /at &lt;location&gt;</code></li>
     *   <li><code>mark &lt;index&gt;</code>, <code>unmark &lt;index&gt;</code>, <code>delete &lt;index&gt;</code></li>
     *   <li><code>find &lt;keyword&gt;</code></li>
     *   <li><code>help</code></li>
     * </ul>
     *
     * @param line raw user input
     * @return a concrete {@link Command} to be executed
     * @throws DarrenAssistantException if the input is empty, malformed,
     *                                  or corresponds to an unknown command
     */
    public static Command parseTask(String line) throws DarrenAssistantException {
        String input = line.trim();
        if (input.isEmpty()) {
            throw new DarrenAssistantException("Empty command.");
        }

        String[] parts = input.split("\\s+", 2);
        String keyword = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1].trim() : "";
        switch (keyword) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                if (args.isEmpty()) {
                    throw new DarrenAssistantException("Use: todo <desc>");
                }
                return new AddTodoCommand(args);
            case "deadline":
                String[] p = args.split("(?i)\\s*/by\\s+", 2);
                if (p.length < 2) {
                    throw new DarrenAssistantException("Use: deadline <desc> /by <when>");
                }
                return new AddDeadlineCommand(p[0].trim(), p[1].trim());
            case "event":
                String[] f = args.split("(?i)\\s*/from\\s+", 2);
                String[] t = (f.length == 2) ? f[1].split("(?i)\\s*/to\\s+", 2) : new String[0];
                if (f.length < 2 || t.length < 2) {
                    throw new DarrenAssistantException("Use: event <desc> /from <start> /to <end>");
                }
                return new AddEventCommand(f[0].trim(), t[0].trim(), t[1].trim());
            case "place":
                String[] p2 = args.split("(?i)\\s*/at\\s+", 2);
                if (p2.length < 2) {
                    throw new DarrenAssistantException("Use: place <desc> /at <location>");
                }
                return new AddPlaceCommand(p2[0].trim(), p2[1].trim());
            case "mark":
                return new MarkCommand(parseIndex(input));
            case "unmark":
                return new UnmarkCommand(parseIndex(input));
            case "delete":
                return new DeleteCommand(parseIndex(input));
            case "find":
                return new FindCommand(args);
            case "help":
                return new HelpCommand();
            default:
                throw new DarrenAssistantException("Sorry, I don't understand that");
        }

    }

    /*
    * Time formatter for user input d/M/yyyy HHmm
     */
    private static final DateTimeFormatter[] DATE_PATTERNS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
    };

    /**
    * Time formatter for user input d/M/yyyy, time set to 00:00
     */
    private static final DateTimeFormatter[] DAY_PATTERNS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ISO_LOCAL_DATE,
    };

    /**
     * Parses a user-supplied date/time string into a {@link LocalDateTime}.
     *
     * <p>Tries {@link #DATE_PATTERNS} first; if none match,
     * tries {@link #DAY_PATTERNS} and returns the start of that day.</p>
     *
     * @param s the date/time string supplied by the user
     * @return the parsed {@link LocalDateTime}
     * @throws IllegalArgumentException if none of the supported patterns match
     */
    public static LocalDateTime parseDateTime(String s) {
        for (DateTimeFormatter f : DATE_PATTERNS) {
            try {
                return LocalDateTime.parse(s.trim(), f);
            } catch (DateTimeParseException ignored) {}
        }
        for (DateTimeFormatter f : DAY_PATTERNS) {
            try {
                return LocalDate.parse(s.trim(), f).atStartOfDay();
            } catch (DateTimeParseException ignored) {}
        }
        throw new IllegalArgumentException("I couldn't understand the date/time: " + s + " Try formatting in DD/MM/yyyy or DD/MM/yyyy HH:mm");
    }

    /*
    * Helper function to split the input for mark, unmark and delete commands
     */
    private static int parseIndex(String input) throws DarrenAssistantException {
        String[] p = input.trim().split("\\s+");
        if (p.length < 2) throw new DarrenAssistantException("Missing number.");
        try {
            int oneBased = Integer.parseInt(p[1]);
            return oneBased - 1; // convert to 0-based
        } catch (NumberFormatException e) {
            throw new DarrenAssistantException("That’s not a number.");
        }
    }

    /**
     * Parses a single storage line into a concrete {@link Task}.
     *
     * <p>Expected formats (pipe-separated):</p>
     * <ul>
     *   <li><code>T | 0|1 | &lt;desc&gt;</code></li>
     *   <li><code>D | 0|1 | &lt;desc&gt; | &lt;by&gt;</code></li>
     *   <li><code>E | 0|1 | &lt;desc&gt; | &lt;from&gt; | &lt;to&gt;</code></li>
     * </ul>
     *
     * @param line the raw line from the save file
     * @return the reconstructed {@link Task}
     * @throws IllegalArgumentException if the line is malformed or has an unknown type
     */
    public static Task parseStoredTask(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 3) throw new IllegalArgumentException("Bad line");

        String type = parts[0].trim().toUpperCase();
        boolean done = "1".equals(parts[1].trim());
        String desc = parts[2].trim();

        switch (type) {
            case "T": {
                ToDoTask t = new ToDoTask(desc);
                if (done) t.markAsDone();
                return t;
            }
            case "D": {
                if (parts.length < 4) throw new IllegalArgumentException("Deadline missing /by");
                LocalDateTime by = parseDateTime(parts[3].trim());
                DeadlinesTask t = new DeadlinesTask(desc, by);
                if (done) t.markAsDone();
                return t;
            }
            case "E": {
                if (parts.length < 5) throw new IllegalArgumentException("Event missing /from or /to");
                LocalDateTime from = parseDateTime(parts[3].trim());
                LocalDateTime to   = parseDateTime(parts[4].trim());
                EventsTask t = new EventsTask(desc, from, to);
                if (done) t.markAsDone();
                return t;
            }
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}
