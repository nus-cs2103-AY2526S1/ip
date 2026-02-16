package botchat.task;


/**
 * Represents a task that can be done after a certain time or task.
 */
public class DoAfter extends Task {
    private String condition;

    /**
     * Constructs a task that has a description and a condition.
     * @param description the String description of a task.
     * @param condition the String condition of when to do a task.
     */
    public DoAfter(String description, String condition) {
        super(description);
        this.condition = condition;
    }

    @Override
    public String toStorage() {
        return "B | " + (isDone ? "1" : "0") + " | " + description + " | " + condition;
    }

    @Override
    public String toString() {
        return "[B]" + super.toString() + " (After: " + condition + ")";
    }


}
