package stella.task;

import stella.exception.ExcessParameterException;
import stella.exception.IncompleteInstructionException;
import stella.exception.StellaException;
import stella.exception.UnknownInstructionException;

import stella.Parser;
import stella.Priority;

/**
 * Represent a task with the only detail being the task description
 */
public class ToDo extends Task {

    public static final String commandKeyword = "todo ";

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
            ToDo.checkDescription(description);
            if (Parser.countParameter(description) == 0) {
                String details = description.substring(commandKeyword.length());

                return new ToDo(details);
            }
            if (Parser.countParameter(description) == 1) {
                int slashIndex = description.indexOf('/');

                String details = description.substring(commandKeyword.length(), slashIndex);
                String priority = description.substring(slashIndex + 1);

                return new ToDo(details, Priority.valueOf(priority));
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

        long numberOfParameter = Parser.countParameter(description);
        if (numberOfParameter > 1) {
            throw new ExcessParameterException(numberOfParameter
                    + " input (excluding task description) is provided,"
                    + " when at most 1 parameter should be provided. ");
        }
    }
}
