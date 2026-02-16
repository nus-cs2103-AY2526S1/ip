package bambam.task;

/**
 * Represents todos which is a type of Task.
 */
public class ToDos extends Task {

    public ToDos(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String printTaskString() {
        return "[T]" + super.printTaskString();
    }

    @Override
    public String taskStorageString() {
        return "T | " + super.taskStorageString();
    }
}

