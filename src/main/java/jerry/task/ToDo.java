package jerry.task;

import jerry.exceptions.InvalidCommandFormatException;

/**
 * A ToDo is a type of Task that does not have any associated date or time.
 * The class ensures that the task description is valid and
 * provides methods to format the task for both file storage and user-friendly display.
 */
public class ToDo extends Task {

    public ToDo(String desc) throws InvalidCommandFormatException {
        super(parseDesc(desc));
    }

    /**
     * Parses the input string to extract a valid description for the to-do task.
     *
     * @param input the raw input string
     * @return a trimmed, valid description
     * @throws InvalidCommandFormatException if the input is empty or only contains the keyword "todo"
     */
    private static String parseDesc(String input) throws InvalidCommandFormatException {
        String desc = input.trim();
        if (desc.toLowerCase().startsWith("todo ")) {
            desc = desc.substring(5).trim();
        }
        if (desc.isEmpty() || desc.equalsIgnoreCase("todo")) {
            throw new InvalidCommandFormatException("You forgot to describe what your todo is...");
        }
        assert !desc.isEmpty() : "Description should not be empty after parsing";
        return desc;
    }

    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    @Override
    public String toString() {
        return "[TODO]" + super.toString();
    }
}
