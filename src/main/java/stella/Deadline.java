package stella;

import stella.exception.ExcessParameterException;
import stella.exception.IncompleteInstructionException;
import stella.exception.InsufficientParameterException;
import stella.exception.StellaException;
import stella.exception.UnknownInstructionException;

/**
 * Represents a type of task that have a deadline. It is represented by
 * two strings, which are the description and deadline.
 */
public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = Parser.formatTime(deadline);
    }

    public Deadline(String description, String deadline, Priority taskPriority) {
        super(description, taskPriority);
        this.deadline = Parser.formatTime(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")" + " (Priority: " + taskPriority + ")";
    }

    public static Deadline createTask(String description) throws StellaException {
        try {
            if (description.length() <= 9) {
                throw new IncompleteInstructionException(description);
            }
            if (Parser.countParameter(description) == 1) {
                String details = description.substring(9, description.indexOf('/'));
                String deadline = description.substring(description.indexOf('/') + 1);
                return new Deadline(details, deadline);
            } else if (Parser.countParameter(description) == 2) {
                int curSlashIndex = description.indexOf('/');
                String details = description.substring(9, curSlashIndex);
                int nextSlashIndex = description.indexOf('/', curSlashIndex + 1);
                String deadline = description.substring(curSlashIndex + 1, nextSlashIndex);
                String priority = description.substring(nextSlashIndex + 1);
                return new Deadline(details, deadline, Priority.valueOf(priority));
            } else if (Parser.countParameter(description) > 2) {
                throw new ExcessParameterException(Parser.countParameter(description)
                        + " input is provided, when at most 2 input should be provided. ");
            } else {
                throw new InsufficientParameterException(Parser.countParameter((description))
                        + " input is provided, when at least 1 input should be provided. ");
            }
        } catch (IllegalArgumentException e) {
            throw new UnknownInstructionException("Priority value");
        }
    }
}
