package bobby;

import bobby.task.Task;

public class DummyTask extends Task {
    public DummyTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + description;
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description;
    }
}