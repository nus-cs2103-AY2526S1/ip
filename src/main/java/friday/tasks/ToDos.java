package friday.tasks;

import friday.tasks.Task;


/**
 * Represents a task that needs to be isDone. A <code>Todos</code> object corresponds
 * to a task represented by its description.
 */
public class ToDos extends Task {

    public ToDos(String description, String tag) {
        super(description, tag);
    }

    /**
     * Formats the task into a String ready to print.
     * @return a String of task in the correct format.
     */
    @Override
    public String taskAsString() {
        return "[T]" + getStatusIcon() + " " + getDescription() + " " + getTag();
    }

}
