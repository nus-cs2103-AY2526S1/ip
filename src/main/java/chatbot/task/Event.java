package chatbot.task;

import chatbot.exception.EmptyArgumentException;

/**
 * Represents an event task with a description, start time, and end time.
 * Stores raw "from" and "to" strings and formats them for display.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates an event with the given description, start, and end.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Parses "description /from START /to END" into an Event.
     */
    public static Event parse(String arguments) {
        String[] fromSplit = arguments.split(" /from ", 2);
        String description = fromSplit[0];

        String from = "";
        String to = "";

        try {
            if (fromSplit.length > 1) {
                String[] toSplit = fromSplit[1].split(" /to ", 2);
                from = toSplit[0];
                if (toSplit.length > 1) {
                    to = toSplit[1];
                } else {
                    throw new EmptyArgumentException("event /from date to /date");
                }
            } else {
                throw new EmptyArgumentException("event /from date to /date");
            }
        } catch (EmptyArgumentException e) {
            System.out.println(e.getMessage());
        }

        return new Event(description, from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " from: " + from + " to: " + to;
    }
}
