package mikey.task;

public class Todo extends Task {

    /**
     * Initializes a Todo instance
     * @param description Description of task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSaveString() {
        String result = "T | " + (isDone ? "1" : "0") + " | " + description;
        if (isTagged()) {
            return result + " | " + tag;
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "[T]" + super.toString();
        return result;
    }
}
