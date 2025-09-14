package stella.task;

import stella.exception.ExcessParameterException;
import stella.exception.IncompleteInstructionException;
import stella.exception.InsufficientParameterException;
import stella.exception.StellaException;
import stella.exception.UnknownInstructionException;

import stella.Parser;
import stella.Priority;

/**
 * Represents a task that have a start date/time and an end date/time. An Event is
 * represented by 3 strings, which are the description, the start and the end.
 */
public class Event extends Task {

    public static final String commandKeyword = "event ";

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
            Event.checkDescription(description);
            if (Task.countParameter(description) == 2) {
                int curSlashIndex = description.indexOf('/');
                int nextSlashIndex = description.indexOf('/', curSlashIndex + 1);

                String details = description.substring(commandKeyword.length(), curSlashIndex);
                String start = description.substring(curSlashIndex + 1, nextSlashIndex);
                String end = description.substring(nextSlashIndex + 1);

                return new Event(details, start, end);
            }
            if (Task.countParameter(description) == 3) {
                int curSlashIndex = description.indexOf('/');
                int nextSlashIndex = description.indexOf('/', curSlashIndex + 1);

                String details = description.substring(commandKeyword.length(), curSlashIndex);
                String start = description.substring(curSlashIndex + 1, nextSlashIndex);

                curSlashIndex = nextSlashIndex;
                nextSlashIndex = description.indexOf('/', nextSlashIndex + 1);

                String end = description.substring(curSlashIndex + 1, nextSlashIndex);
                String priority = description.substring(nextSlashIndex + 1);

                return new Event(details, start, end, Priority.valueOf(priority));
            }
            return null;
        } catch (IllegalArgumentException e) {
            throw new UnknownInstructionException("Priority value");
        }
    }

    private static void checkDescription(String description) throws StellaException {
        if (description.length() <= commandKeyword.length()) {
            throw new IncompleteInstructionException(description);
        }

        long numberOfParameter = Task.countParameter(description);
        if (numberOfParameter > 3) {
            throw new ExcessParameterException(numberOfParameter
                    + " input (excluding task description) is provided,"
                    + " when at most 3 input should be provided. ");
        }
        if (numberOfParameter < 2) {
            throw new InsufficientParameterException(numberOfParameter
                    + " input (excluding task description) is provided,"
                    + " when at least 2 input should be provided. ");
        }
    }
}

