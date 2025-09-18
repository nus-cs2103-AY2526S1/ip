package alune.tasks;

import java.io.Serializable;

/**
 * This class is a parent class of the different tasks.
 * 
 * @author nghnaomi
 */
public class Task implements Serializable {
    private boolean isDone = false;
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public Task(Task other) {
        this.name = other.name;
        this.isDone = other.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public Task cloneTask() {
        return new Task(this);
    }

    @Override
    public String toString() {
        return this.isDone
                ? "[X] " + this.name
                : "[ ] " + this.name;
    }

}
