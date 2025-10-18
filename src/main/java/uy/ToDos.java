package uy;

/**
 * Simple todo task without dates.
 */
public class ToDos extends Task {

    /**
     * Construct a ToDos task with the given description.
     *
     * @param task_name description
     */
    public ToDos(String task_name) {
        super(task_name, false, "T");
    }
}
