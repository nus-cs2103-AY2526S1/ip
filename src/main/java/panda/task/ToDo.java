package panda.task;

/**
 * Todo is a subclass of Task, with additional information of
 * task type.
 */
public class ToDo extends Task {
    protected String type;

    /**
     * Constructs ToDo object with description from user.
     *
     * @param description Description of task.
     */
    public ToDo(String description) {
        super(description);
        this.type = "[T]";
    }

    /**
     * Constructs ToDo object based on info from text file.
     *
     * @param status Status of task completion.
     * @param description Description of task.
     */
    public ToDo(String status, String description) {
        super(status, description);
        this.type = "[T]";
    }

    public String writeToFile() {
        return type + "|" + super.status + "|" + super.description + "\n";
    }

    @Override
    public String toString() {
        return type + super.toString();
    }
}
