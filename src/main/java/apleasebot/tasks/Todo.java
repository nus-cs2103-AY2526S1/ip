package apleasebot.tasks;

import java.time.LocalDateTime;

/**
 * Encapsulates the logic for the Todo Task
 */
public class Todo extends Task {
    public Todo(String name, boolean todo) {
        super(name, todo);
    }
    @Override
    public String toString() {
        return "[T] " + (isDone ? "[X] " : "[ ] ") + desc;
    }

    @Override
    public String translateTaskToText() {
        return "T," + this.checkDone() + "," + this.desc;
    }

    @Override
    public LocalDateTime getTime() {
        return null;
    }
}
