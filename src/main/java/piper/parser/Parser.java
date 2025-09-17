package piper.parser;

import piper.PiperException;
import piper.parser.CommandType;

/**
 * Parses raw user input into commands and structured arguments.
 * Provides helpers to split the command token from the rest of the input.
 * Extracts fields for Deadline abd Event classes.
 */
public final class Parser {
    /** Utility class that is not instantiated. */
    private Parser() {}

    /**
     * Result of splitting a raw input into a command and its argument.
     */
    public static final class ParsedString {
        public final CommandType cmdType;
        public final String arg;

        /**
         * Creates a ParsedString.
         *
         * @param cmdType command type.
         * @param arg remainder of the user input or null if none.
         */
        public ParsedString(CommandType cmdType, String arg) {
            this.cmdType = cmdType;
            assert cmdType != null : "ParsedString should be non-null";
            this.arg = arg;
        }
    }

    /**
     * Structured arguments for a deadline command.
     */
    public static final class DeadlineArgs {
        public final String description;
        public final String by;

        /**
         * Creates DeadlineArgs.
         *
         * @param description task description.
         * @param by by deadline text.
         */
        public DeadlineArgs(String description, String by) {
            this.description = description;
            this.by = by;
        }
    }

    /**
     * Structured arguments for an event command.
     */
    public static final class EventArgs {
        public final String description;
        public final String from;
        public final String to;

        /**
         * Creates EventArgs.
         *
         * @param description task description.
         * @param from start timing text.
         * @param to end timing text.
         */
        public EventArgs(String description, String from, String to) {
            this.description = description;
            this.from = from;
            this.to = to;
        }
    }

    /**
     * Parses a raw line into a command token and its argument.
     *
     * @param userInput trimmed line entered by the user.
     * @return a ParsedString containing the command and argument or null.
     * @throws PiperException if the line is empty or the command is invalid or incomplete.
     */
    public static ParsedString parse(String userInput) throws PiperException {
        if (userInput == null || userInput.isEmpty()) {
            throw new PiperException("CHIRP CHIRP! Don't think you said anything there. Try tweeting a command!");
        }
        String[] substrings = userInput.split("\\s+", 2);
        assert substrings.length > 0 : "Command string should not be empty";
        String cmdToken = substrings[0];
        CommandType cmd = CommandType.from(cmdToken);

        if (cmd == null) {
            throw new PiperException(
                    "CHIRP CHIRP! I don't recognise the tune called '" + cmdToken + "'. Try another command?"
            );
        }

        if (substrings.length < 2) {
            if (!cmd.requiresArg()) {
                return new ParsedString(cmd, null);
            }
            switch (cmd) {
            case TODO:
            case DEADLINE:
            case EVENT:
                // missing task description
                throw new PiperException("TWEET TWEET! What are we supposed to do? Please specify the description!");
            case FIND:
                // missing keyword
                throw new PiperException("TWEET TWEET! What should we look for? Please specify a keyword!");
            case MARK:
            case UNMARK:
            case DELETE:
                // missing task index
                throw new PiperException("CHIRRUP! Which task should I peck at? Please give me the task index!");
            default:
                return new ParsedString(cmd, null);
            }
        }

        String arg = substrings[1];
        return new ParsedString(cmd, arg);
    }

    /**
     * Parses a 1-based index from user input.
     *
     * @param index string containing the index.
     * @return parsed integer value.
     * @throws PiperException if the string is not a valid integer.
     */
    public static int parseIndex(String index) throws PiperException {
        try {
            return Integer.parseInt(index.trim());
        } catch (Exception e) {
            throw new PiperException("PEEP! Please give me a numeric task index!");
        }
    }

    /**
     * Extracts description and /by fields for a deadline.
     *
     * @param arg remainder of the user input after the command token.
     * @return structured DeadlineArgs.
     * @throws PiperException if required parts are missing or empty.
     */
    public static DeadlineArgs parseDeadlineArgs(String arg) throws PiperException {
        try {
            String[] descriptionAndBy = arg.split("/by ", 2);
            String description = descriptionAndBy[0].trim();
            if (description.isEmpty()) {
                throw new IllegalArgumentException();
            }
            String by = descriptionAndBy[1].trim();
            if (by.isEmpty()) {
                throw new IllegalArgumentException();
            }
            return new DeadlineArgs(description, by);
        } catch (Exception e) {
            throw new PiperException(
                    "OOPS! That deadline's off key. Please format the task as 'deadline (description) /by (deadline)'!"
            );
        }
    }

    /**
     * Extracts description, /from, and /to fields for an event.
     *
     * @param arg remainder of the user input after the command token.
     * @return structured EventArgs.
     * @throws PiperException if required parts are missing or empty.
     */
    public static EventArgs parseEventArgs(String arg) throws PiperException {
        try {
            String[] descriptionAndFrom = arg.split("/from ", 2);
            String description = descriptionAndFrom[0].trim();
            if (description.isEmpty()) {
                throw new IllegalArgumentException();
            }
            String[] fromAndTo = descriptionAndFrom[1].split("/to ", 2);
            String from = fromAndTo[0].trim();
            if (from.isEmpty()) {
                throw new IllegalArgumentException();
            }
            String to = fromAndTo[1].trim();
            if (to.isEmpty()) {
                throw new IllegalArgumentException();
            }
            return new EventArgs(description, from, to);
        } catch (Exception e) {
            throw new PiperException(
                    "EEP! Your event's missing a few notes. "
                            + "Please format the event as 'event (description) /from (start time) /to (end time)'!"
            );
        }
    }
}
