package cattis.task;

import cattis.exception.CattisException;

/**
 * Represents task with only task name and status
 */
public class TodoTask extends Task {
    public static final String ICON = "[T]";

    public TodoTask(String taskName) {
        super(taskName);
    }

    /**
     * Constructor for {@code TodoTask} with specific status
     * primarily used for loading tasks from the file
     * @param taskName task name
     * @param status status [X] or [ ]
     */
    public TodoTask(String taskName, boolean status) {
        super(taskName);
        if (status) {
            this.mark();
        } else {
            this.unmark();
        }
    }


    /**
     * Create an <code>TodoTask</code> from the user-provided input (without keyword <code>todo</code>)
     *
     * @param prompt user prompt without command keyword <code>event</code>
     * @return <code>TodoTask</code> instance
     * @throws CattisException for parsing error
     */
    public static TodoTask createFromPrompt(String prompt) throws CattisException {
        if (prompt.isEmpty()) {
            throw new CattisException(CattisException.EMPTY_FIELD);
        } else {
            return new TodoTask(prompt);
        }
    }

    @Override
    public String toEncodedString() {
        return ICON + Task.SPLITTER + super.getStatusIcon() + Task.SPLITTER + super.getTaskName();
    }

    @Override
    public String toString() {
        return ICON + super.toString();
    }
}
