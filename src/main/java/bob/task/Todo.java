package bob.task;

/**
 * Represent the todo task
 */
public class Todo extends Task {

    /**
     * Constructor for todo task
     * 
     * @param s Name of the task
     * @param isDone Indicate whether the task is done
     * @param id Id of the task
     */
    public Todo(String s, boolean isDone, int id) {
        super(s, "T", isDone, id);
    }

    /**
     * String for saving and loading of task
     * 
     * @return String used for saving and loading
     */
    @Override
    public String saveString() {
        return String.format("%s|%s|%s|%s", "T", super.getTaskName(), super.returnDone(),
                Integer.toString(super.getId()));
    }

    /**
     * toString method to return task string
     * 
     * @return Returns task string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
