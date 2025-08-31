package piper.parser;

import piper.PiperException;


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
        public final String cmd;
        public final String arg;

        /**
         * Creates a ParsedString.
         *
         * @param cmd command token.
         * @param arg remainder of the user input or null if none.
         */
        public ParsedString(String cmd, String arg) {
            this.cmd = cmd;
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
        String[] substrings = userInput.split("\\s", 2);
        String cmd = substrings[0];

        if (substrings.length < 2) {
            if (cmd.equals("bye")) {
                return new ParsedString(cmd, null);
            } else if (cmd.equals("list")) {
                return new ParsedString(cmd, null);
            } else if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {
                // missing task description
                throw new PiperException("TWEET TWEET! What are we supposed to do? Please specify the description!");
            } else if (cmd.equals("mark") || cmd.equals("unmark") || cmd.equals("delete")) {
                // missing task index
                throw new PiperException("CHIRRUP! Which task should I peck at? Please give me the task index!");
            } else {
                // unrecognisable command
                throw new PiperException("CHIRP CHIRP! I don't recognise the tune called '" + cmd + "'. Try another command?");
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
            throw new PiperException("OOPS! That deadline's off key. Please format the task as 'deadline (description) /by (deadline)'!");
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
            throw new PiperException("EEP! Your event's missing a few notes. Please format the event as 'event (description) /from (start time) /to (end time)'!");
        }
    }
}