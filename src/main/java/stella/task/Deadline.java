package stella.task;

import stella.exception.ExcessParameterException;
import stella.exception.IncompleteInstructionException;
import stella.exception.InsufficientParameterException;
import stella.exception.StellaException;
import stella.exception.UnknownInstructionException;

import stella.Parser;
import stella.Priority;

/**
 * Represents a type of task that have a deadline. It is represented by
 * two strings, which are the description and deadline.
 */
public class Deadline extends Task {

    public static final String commandKeyword = "deadline ";

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
            Deadline.checkDescription(description);
            if (Task.countParameter(description) == 1) {
                int curSlashIndex = description.indexOf('/');
                String details = description.substring(commandKeyword.length(), curSlashIndex);
                String deadline = description.substring(curSlashIndex + 1);

                return new Deadline(details, deadline);
            }

            if (Task.countParameter(description) == 2) {
                int curSlashIndex = description.indexOf('/');
                int nextSlashIndex = description.indexOf('/', curSlashIndex + 1);

                String details = description.substring(commandKeyword.length(), curSlashIndex);
                String deadline = description.substring(curSlashIndex + 1, nextSlashIndex);
                String priority = description.substring(nextSlashIndex + 1);

                return new Deadline(details, deadline, Priority.valueOf(priority));
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
        if (numberOfParameter > 2) {
            throw new ExcessParameterException(numberOfParameter
                    + " input (excluding task description) is provided,"
                    + " when at most 2 input should be provided. ");
        }
        if (numberOfParameter < 1){
            throw new InsufficientParameterException(numberOfParameter
                    + " input (excluding task description) is provided,"
                    + " when at least 1 input should be provided. ");
        }
    }
}
