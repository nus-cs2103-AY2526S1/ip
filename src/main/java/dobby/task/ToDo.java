package dobby.task;

import java.io.Serializable;

public class ToDo extends Task implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String by;
    private TaskType type;

    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

}
