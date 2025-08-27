package zell.task;

import zell.exception.ZellException;
import zell.storage.Storage;

/**
 * Represents a task for the Zell chatbot
 */
public abstract class Task {
    /** The name of the task */
    private final String name;
    /** Indicates whether a task is completed */
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Converts a string stored in local storage to a task. Is a static method since it does not make sense to
     * create an object does for doing this.
     * <p>
     * We split the string into different components by "|". The task is identified by the first component
     * we should be either (T, D ,E). We also check based on the task is the number of components is correct.
     * </p>
     * @param stringTask The string stored in local storage to be converted back to a task.
     * @return The correct Task based on the task string.
     * @throws ZellException If an invalid task string is provided (wrong format).
     */
    public static Task stringToTask(String stringTask) throws ZellException{
        String[] components = stringTask.split(" \\| ");

        if (components.length < 1) {
            throw new ZellException("Invalid task string provided! Certain parameters are missing.");
        }

        switch (components[0]) {
        case "T":
            if (components.length != 3) {
                throw new ZellException("Invalid task string provided! Certain parameters are missing.");
            }

            return new ToDo(components[2], Boolean.parseBoolean(components[1]));
        case "D":
            if (components.length != 4) {
                throw new ZellException("Invalid task string provided! Certain parameters are missing.");
            }

            return new Deadline(components[2], components[3], Boolean.parseBoolean(components[1]));
        case "E":
            if (components.length != 5) {
                throw new ZellException("Invalid task string provided! Certain parameters are missing.");
            }

            return new Event(components[2], components[3], components[4], Boolean.parseBoolean(components[1]));
        default:
            throw new ZellException("Unknown task type encountered when converting tasks");
        }
    }

    /**
     * Represents an abstract method to convert a task to a string to be stored for local storage. Will
     * be implemented by subclasses that inherit task
     *
     * @return The string representation of the task to be stored.
     */
    public abstract String taskToString();


    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Overrides the default toString
     * <p>
     * Displays the task properly formatted with a checkbox which indicates if a task is completed. Used for
     * display of the task to the user.
     * </p>
     * @return The toString format of the Task
     */
    @Override
    public String toString() {
        String checked = this.isDone ? "X" : " ";
        return String.format("[%s] %s", checked, this.name);
    }
}
