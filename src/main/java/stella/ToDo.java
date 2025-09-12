package stella;

import stella.exception.ExcessParameterException;
import stella.exception.IncompleteInstructionException;
import stella.exception.StellaException;
import stella.exception.UnknownInstructionException;

/**
 * Represent a task with the only detail being the task description
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, Priority taskPriority) {
        super(description, taskPriority);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " (Priority: " + taskPriority + ")";
    }

    public static ToDo createTask(String description) throws StellaException {
        try {
            if (description.length() <= 5) {
                throw new IncompleteInstructionException(description);
            }
            if (Parser.countParameter(description) > 1) {
                throw new ExcessParameterException(Parser.countParameter(description)
                        + " input is provided, when at most 1 parameter should be provided. ");
            } else if (Parser.countParameter(description) == 0) {
                String details = description.substring(5);
                return new ToDo(details);
            } else if (Parser.countParameter(description) == 1) {
                String details = description.substring(5, description.indexOf('/'));
                String priority = description.substring(description.indexOf('/') + 1);
                return new ToDo(details, Priority.valueOf(priority));
            } else {
                System.out.println("There should not be negative number of input provided.");
                return null;
            }
        }

        catch (IllegalArgumentException e) {
            throw new UnknownInstructionException("Priority value");
        }

    }
}
