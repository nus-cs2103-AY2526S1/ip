package stella.task;

import stella.exception.ExcessParameterException;
import stella.exception.IncompleteInstructionException;
import stella.exception.StellaException;
import stella.exception.UnknownInstructionException;

import stella.Priority;

/**
 * Represents a task with two strings, which are the description and priority.
 * The default value for priority is UNDECIDED.
 */
public class ToDo extends Task {
    public static final String COMMAND_KEYWORD = "todo ";

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

    /**
     * Creates and returns a new todo object based on a valid description.
     *
     * @param description Description of the todo,
     *                    which comes from the user's commands (e.g. todo shopping).
     * @return A newly-created todo object based on the description.
     * @throws StellaException If the description provided is invalid.
     */
    public static ToDo createTask(String description) throws StellaException {
        try {
            ToDo.checkDescription(description);
            if (Task.countParameter(description) == 0) {
                String details = description.substring(COMMAND_KEYWORD.length());

                return new ToDo(details);
            }
            if (Task.countParameter(description) == 1) {
                int slashIndex = description.indexOf('/');

                String details = description.substring(COMMAND_KEYWORD.length(), slashIndex);
                String priority = description.substring(slashIndex + 1);

                return new ToDo(details, Priority.valueOf(priority));
            }
            return null;
        } catch (IllegalArgumentException e) {
            throw new UnknownInstructionException("Priority value");
        }
    }

    private static void checkDescription(String description) throws StellaException {
        if (description.length() <= COMMAND_KEYWORD.length()) {
            throw new IncompleteInstructionException(description);
        }

        long numberOfParameter = Task.countParameter(description);
        if (numberOfParameter > 1) {
            throw new ExcessParameterException(numberOfParameter
                    + " input (excluding task description) is provided,"
                    + " when at most 1 parameter should be provided. ");
        }
    }
}
