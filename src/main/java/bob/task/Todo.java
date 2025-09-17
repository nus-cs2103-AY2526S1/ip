package bob.task;

import bob.exception.InvalidInputException;

/**
 * Tasks without any date/time attached to it.
 */
public class Todo extends Task {
    private static final String TASK_TYPE_SYMBOL = "[T]";
    private static final String COMMAND_KEYWORD = "todo";
    
    /**
     * Constructs a Todo task.
     *
     * @param description description for the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Parses the input string given into Todo object.
     *
     * @param input should be of the format: todo [description].
     * @return a new Todo task
     * @throws InvalidInputException if the input format is invalid or description is empty
     */
    public static Todo parse(String input) throws InvalidInputException {
        String taskName = input.replaceFirst(COMMAND_KEYWORD, "").trim();
        if (taskName.isEmpty()) {
            throw new InvalidInputException("Invalid todo task (description cannot be empty): " + input
                    + "\nExample of valid format: 'todo borrow book'");
        }
        return new Todo(taskName);
    }

    @Override
    public String toString() {
        return TASK_TYPE_SYMBOL + super.toString();
    }

    @Override
    public String toInputString() {
        return COMMAND_KEYWORD + " " + this.description;
    }
}
