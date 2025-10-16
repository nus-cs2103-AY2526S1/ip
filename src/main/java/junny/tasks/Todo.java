package junny.tasks;

import junny.TaskTypes;

public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskTypes.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        int status;
        if (this.isDone) {
            status = 1;
        } else {
            status = 0;
        }
        return "T | " + status + " | " + this.description;
    }
}

