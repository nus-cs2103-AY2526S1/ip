package ackermann.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Superclass for all Task objects
 */
public class Task {
    private boolean done;
    private String name;
    private List<String> tags;

    /**
     * Constructor for Task
     * @param name
     */
    public Task(String name) {
        this.done = false;
        this.name = name;
        this.tags = new ArrayList<>();
    }

    /**
     * Formats boolean flag to string
     * @return X if done " " if undone
     */
    public String getStatus() {
        return done ? "X" : " ";
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void tag(String tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.name;
    }
}
