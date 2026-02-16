package jooh.parser;

import java.util.Locale;

import jooh.exception.EmptyDescriptionException;
import jooh.exception.InvalidDeadlineException;
import jooh.exception.InvalidEventTimelineException;
import jooh.exception.JoohException;

/**
 * Utility class that interprets raw user input into structured commands.
 * Provides parsing logic that maps strings to command types and arguments,
 * returning {@code Parsed} objects for execution by the application.
 */
public class Parser {
    /**
     * Represents the set of valid command types supported by Jooh.
     */
    public enum Type {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND, FIXED
    }
    /**
     * Immutable result of parsing a user input string.
     * Encapsulates the command type and any associated arguments such as
     * description, deadline, timeline, index, or duration.
     */
    public static final class Parsed {
        public final Type type;
        public final String desc;
        public final String by;
        public final String from;
        public final String to;
        public final Integer index;
        public final String duration;

        private Parsed(Type type, String desc,
                        String by, String from, String to, Integer index, String duration) {
            this.type = type;
            this.desc = desc;
            this.by = by;
            this.from = from;
            this.to = to;
            this.index = index;
            this.duration = duration;
        }

        /**
         * Creates a parsed representation of a 'bye' command.
         *
         * @return A Parsed instance of type BYE.
         */
        public static Parsed bye() {
            return new Parsed(Type.BYE, null, null, null, null, null, null);
        }
        /**
         * Creates a parsed representation of a 'list' command.
         *
         * @return A Parsed instance of type LIST.
         */
        public static Parsed list() {
            return new Parsed(Type.LIST, null, null, null, null, null, null);
        }
        /**
         * Creates a parsed representation of a 'todo' command.
         *
         * @param desc Description of the to-do task.
         * @return A Parsed instance of type TODO with description.
         */
        public static Parsed todo(String desc) {
            return new Parsed(Type.TODO, desc, null, null, null, null, null);
        }
        /**
         * Creates a parsed representation of a 'deadline' command.
         *
         * @param desc Description of the task.
         * @param by   Deadline string.
         * @return A Parsed instance of type DEADLINE with description and deadline.
         */
        public static Parsed deadline(String desc, String by) {
            return new Parsed(Type.DEADLINE, desc, by, null, null, null, null);
        }
        /**
         * Creates a parsed representation of an 'event' command.
         *
         * @param desc Description of the event.
         * @param from Event start time string.
         * @param to   Event end time string.
         * @return A Parsed instance of type EVENT with description and time range.
         */
        public static Parsed event(String desc, String from, String to) {
            return new Parsed(Type.EVENT, desc, null, from, to, null, null);
        }
        /**
         * Creates a parsed representation of a 'mark' command.
         *
         * @param i 1-based index of the task to mark as done.
         * @return A Parsed instance of type MARK with index.
         */
        public static Parsed mark(int i) {
            return new Parsed(Type.MARK, null, null, null, null, i, null);
        }
        /**
         * Creates a parsed representation of an 'unmark' command.
         *
         * @param i 1-based index of the task to mark as not done.
         * @return A Parsed instance of type UNMARK with index.
         */
        public static Parsed unmark(int i) {
            return new Parsed(Type.UNMARK, null, null, null, null, i, null);
        }
        /**
         * Creates a parsed representation of a 'delete' command.
         *
         * @param i 1-based index of the task to delete.
         * @return A Parsed instance of type DELETE with index.
         */
        public static Parsed delete(int i) {
            return new Parsed(Type.DELETE, null, null, null, null, i, null);
        }
        /**
         * Creates a parsed representation of a 'find' command.
         *
         * @param keyword The keyword to search for in task descriptions.
         * @return A {@code Parsed} instance of type FIND containing the keyword.
         */
        public static Parsed find(String keyword) {
            return new Parsed(Type.FIND, keyword, null, null, null, null, null);
        }
        /**
         * Creates a parsed representation of a 'fixed' command.
         * <p>
         * A fixed-duration task represents an unscheduled activity
         * that requires a specified amount of time to complete,
         * but does not have a fixed start or end date.
         * </p>
         *
         * @param desc Description of the task.
         * @param duration Duration string representing how long
         *                 the task requires (e.g., "2 hours").
         * @return A {@code Parsed} instance of type FIXED
         *         containing the description and duration.
         */
        public static Parsed fixed(String desc, String duration) {
            return new Parsed(Type.FIXED, desc, null, null, null, null, duration);
        }
        /**
         * Parses a raw user input string into a structured Parsed object.
         * Splits the input into command keyword and arguments, validates them,
         * and constructs the appropriate Parsed instance.
         *
         * @param input Raw command line entered by the user.
         * @return A Parsed representation of the command.
         * @throws JoohException If the input is empty, malformed, or violates
         *                       expected command syntax.
         */
        public static Parsed parse(String input) throws JoohException {
            if (input == null) {
                input = "";
            }
            input = input.trim(); //trim removes last spacing
            if (input.isEmpty()) {
                throw new JoohException("Please key in something...");
            }

            // \\s+ is the regex for one or more white spaces
            String[] parts = input.split("\\s+", 2);
            assert parts.length >= 1 : "Split must always produce at least one token";
            String cmd = parts[0].toLowerCase(Locale.ROOT);
            String rest = parts.length > 1 ? parts[1].trim() : "";

            switch (cmd) {
                case "bye":
                    return Parsed.bye();

                case "list":
                    return Parsed.list();

                case "todo":
                    if (rest.isEmpty()) {
                        throw new EmptyDescriptionException("todo");
                    }
                    return Parsed.todo(rest);

                case "deadline": {
                    String[] pair = rest.split("(?i)\\s*/by\\s+", 2);
                    String desc = pair.length > 0 ? pair[0].trim() : "";
                    String by = pair.length > 1 ? pair[1].trim() : "";
                    if (desc.isEmpty()) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    if (by.isEmpty()) {
                        throw new InvalidDeadlineException();
                    }
                    return Parsed.deadline(desc, by);
                }

                case "event": {
                    String[] pair = rest.split("(?i)\\s*/from\\s+", 2);
                    String desc = pair.length > 0 ? pair[0].trim() : "";
                    String remaining = pair.length > 1 ? pair[1] : "";
                    String[] fromAndTo = remaining.split("(?i)\\s*/to\\s+", 2);
                    String from = fromAndTo.length > 0 ? fromAndTo[0].trim() : "";
                    String to = fromAndTo.length > 1 ? fromAndTo[1].trim() : "";
                    if (desc.isEmpty()) {
                        throw new EmptyDescriptionException("event");
                    }
                    if (from.isEmpty() || to.isEmpty()) {
                        throw new InvalidEventTimelineException();
                    }
                    return Parsed.event(desc, from, to);
                }

                case "mark":
                case "unmark":
                case "delete": {
                    if (rest.isEmpty()) {
                        throw new JoohException("Jooh.task.Task number must be provided.");
                    }
                    final int n;
                    try {
                        n = Integer.parseInt(rest);
                    } catch (NumberFormatException e) {
                        throw new JoohException("Jooh.task.Task number must be a positive integer.");
                    }
                    if (n < 1) {
                        throw new JoohException("Jooh.task.Task number must be positive.");
                    }
                    if ("mark".equals(cmd)) {
                        return Parsed.mark(n);
                    }
                    if ("unmark".equals(cmd)) {
                        return Parsed.unmark(n);
                    }
                    return Parsed.delete(n);
                }

                case "find":
                    if (rest.isEmpty()) {
                        throw new JoohException("Please provide a description to search for.");
                    }
                    return Parsed.find(rest);

                case "fixed":
                    String[] pair = rest.split("(?i)\\s*/for\\s+", 2);
                    String desc = pair.length > 0 ? pair[0].trim() : "";
                    String duration = pair.length > 1 ? pair[1].trim() : "";
                    if (desc.isEmpty() || duration.isEmpty()) {
                        throw new JoohException("Fixed task requires description and duration.");
                    }
                    return Parsed.fixed(desc, duration);

                default:
                    assert false : "Unhandled command reached: " + cmd;
                    throw new JoohException("Unknown command: " + cmd);
            }
        }
    }
}

