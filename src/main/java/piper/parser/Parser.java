package piper.parser;

import piper.PiperException;

public final class Parser {
    private Parser() {}

    public static final class ParsedString {
        public final String cmd;
        public final String arg;

        public ParsedString(String cmd, String arg) {
            this.cmd = cmd;
            this.arg = arg;
        }
    }

    public static final class DeadlineArgs {
        public final String description;
        public final String by;
        public DeadlineArgs(String description, String by) {
            this.description = description;
            this.by = by;
        }
    }

    public static final class EventArgs {
        public final String description;
        public final String from;
        public final String to;
        public EventArgs(String description, String from, String to) {
            this.description = description;
            this.from = from;
            this.to = to;
        }
    }

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

    public static int parseIndex(String index) throws PiperException {
        try {
            return Integer.parseInt(index.trim());
        } catch (Exception e) {
            throw new PiperException("PEEP! Please give me a numeric task index!");
        }
    }

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