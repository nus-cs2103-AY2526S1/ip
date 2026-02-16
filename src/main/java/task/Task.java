package task;

import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Pattern;

import utils.Storage;

/**
 * The Task class represents tasks that have a name and whether it is marked as
 * done or not. Task objects must call the associateList function after
 * instantiation
 */
public abstract class Task implements Serializable {
    private String name;
    private boolean isDone;
    private TaskList tl;

    /**
     * Creates a Task object using the name, with the the task marked as undone
     *
     * @param name
     *            The name for this task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /** Returns the name of this class */
    public String getName() {
        return name;
    }

    /**
     * Marks this task as done or not, associateList must be called before this
     * function is called
     */
    public void mark(boolean done) {
        this.isDone = done;
        try {
            Storage.save(tl);
        } catch (IOException e) {
            System.out.println("Unable to backup task list, your data might be lost.");
        }
    }

    /**
     * Associates the given TaskList to this class so that TaskList.save can be
     * called
     */
    public void associateList(TaskList tl) {
        this.tl = tl;
    }

    /** Whether this task is marked as done or not */
    public boolean isDone() {
        return isDone;
    }

    public boolean match(String pattern) {
        return Pattern.matches(pattern, name);
    }

    /** The title of this task */
    public abstract String getTaskTitle();

    protected abstract char typeChar();

    @Override
    public String toString() {
        // typeChar should only be an ASCII uppercase letter
        assert (typeChar() >= 65 && typeChar() <= 91);
        return String.format("[%s][%s] %s", typeChar(), isDone ? "X" : " ", getTaskTitle());
    }
}
