package stella;

import stella.exception.ExcessParameterException;
import stella.exception.IncompleteInstructionException;
import stella.exception.InsufficientParameterException;
import stella.exception.StellaException;
import stella.exception.UnknownInstructionException;

/**
 * Represents a task that have a start date/time and an end date/time. An Event is
 * represented by 3 strings, which are the description, the start and the end.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = Parser.formatTime(start);
        this.end = Parser.formatTime(end);
    }

    public Event(String description, String start, String end, Priority taskPriority) {
        super(description, taskPriority);
        this.start = Parser.formatTime(start);
        this.end = Parser.formatTime(end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " | to: " + this.end + ")"
                + " (Priority: " + taskPriority + ")";
    }

    public static Event createTask(String description) throws StellaException {
        try {
            if (description.length() <= 6) {
                throw new IncompleteInstructionException(description);
            }
            if (Parser.countParameter(description) == 2) {
                int curSlashIndex = description.indexOf('/');
                String details = description.substring(6, curSlashIndex);
                int nextSlashIndex = description.indexOf('/', curSlashIndex + 1);
                String start = description.substring(curSlashIndex + 1, nextSlashIndex);
                String end = description.substring(nextSlashIndex + 1);
                return new Event(details, start, end);
            } else if (Parser.countParameter(description) == 3) {
                int curSlashIndex = description.indexOf('/');
                String details = description.substring(6, curSlashIndex);
                int nextSlashIndex = description.indexOf('/', curSlashIndex + 1);
                String start = description.substring(curSlashIndex + 1, nextSlashIndex);
                curSlashIndex = nextSlashIndex;
                nextSlashIndex = description.indexOf('/', nextSlashIndex + 1);
                String end = description.substring(curSlashIndex + 1, nextSlashIndex);
                String priority = description.substring(nextSlashIndex + 1);
                return new Event(details, start, end, Priority.valueOf(priority));
            } else if (Parser.countParameter(description) > 3) {
                throw new ExcessParameterException(Parser.countParameter(description)
                        + " input is provided, when at most 3 input should be provided. ");
            } else {
                throw new InsufficientParameterException(Parser.countParameter((description))
                        + " input is provided, when at least 2 input should be provided. ");
            }

        } catch (IllegalArgumentException e) {
            throw new UnknownInstructionException("Priority value");
        }

    }
}

