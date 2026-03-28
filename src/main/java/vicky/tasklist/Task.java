package vicky.tasklist;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import vicky.parser.Parser;

/**
 * Abstract class representing a task with a name and completion status.
 *
 * @author Rachel
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructor for Task class, initializes the Task with a name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Overloaded constructor for Task class, initializes the Task with a name and a completion status.
     *
     * @param name The name of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Changes the completion status of the task to true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Changes the completion status of the task to false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the task description contains the input keyword.
     *
     * @param str The keyword.
     * @return true if the task description contains the input keyword.
     */
    public boolean contains(String str) {
        return this.name.toLowerCase().contains(str);
    }

    /**
     * Changes the name of the task to the new inputted name.
     *
     * @param name The new name.
     */
    public void changeName(String name) {
        this.name = name;
    }

    /**
     * Parses the storage string into a corresponding task object.
     *
     * @param s The storage string containing the details of a task, formatted as "taskType | taskStatus | taskDescriptor".
     * @return The corresponding task object (Todo, Deadline, or Event) based on the parsed storage string.
     * @throws IllegalArgumentException if the storage string format is invalid or if required data is missing.
     */
    public static Task fromStorageString(String s) throws IllegalArgumentException, DateTimeException {
        String[] temp = s.split("\\s\\|\\s");
        String taskType = temp[0];

        if (temp.length < 3) {
            throw new IllegalArgumentException("Missing data: Only " + temp.length + " fields received.");
        }

        String taskStatus = temp[1];
        boolean isDone = getTaskStatus(taskStatus);

        String taskDescriptor = temp[2];
        if (taskDescriptor.isEmpty()) {
            throw new IllegalArgumentException("Empty task descriptor");
        }

        switch (taskType) {
        case "Todo":
            return new Todo(taskDescriptor, isDone);
        case "Deadline":
            return fromDeadlineStorageString(isDone, taskDescriptor, temp);
        case "Event":
            return fromEventStorageString(isDone, taskDescriptor, temp);
        default:
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }

    /**
     * Retrieves the completion status of a task from the task status string.
     * This method checks if the task status string is valid and returns the corresponding boolean value for the task's completion status.
     *
     * @param taskStatus The task status string, where "0" represents true (completed) and "1" represents false (not completed).
     * @return A boolean indicating whether the task is done or not.
     * @throws IllegalArgumentException if the task status string is invalid or empty.
     */
    private static boolean getTaskStatus(String taskStatus) throws IllegalArgumentException {
        if (taskStatus.isEmpty()) {
            throw new IllegalArgumentException("Missing task status.");
        } else if (taskStatus.equals("0")) {
            return true;
        } else if (taskStatus.equals("1")) {
            return false;
        } else {
            throw new IllegalArgumentException("Corrupted task status value: " + taskStatus);
        }
    }

    /**
     * Parses a storage string for a "Deadline" task and returns the corresponding Deadline object.
     * This method checks the validity of the task fields and constructs a Deadline object with the provided task description and deadline.
     *
     * @param isDone The completion status of the task.
     * @param taskDescriptor The description of the task.
     * @param temp The array containing task data, including the deadline information.
     * @return The corresponding Deadline object with the parsed description and deadline time.
     * @throws IllegalArgumentException if the task data is incomplete or invalid.
     */
    private static Deadline fromDeadlineStorageString(boolean isDone, String taskDescriptor, String[] temp)
            throws IllegalArgumentException {
        if (temp.length != 4 || temp[3].isEmpty()) {
            throw new IllegalArgumentException("Deadline task has missing values.");
        }
        LocalDateTime deadline = Parser.parseOutputString(temp[3]);
        return new Deadline(taskDescriptor, deadline, isDone);
    }

    /**
     * Parses a storage string for an "Event" task and returns the corresponding Event object.
     * This method checks the validity of the task fields and constructs an Event object with the provided task description, start time, and end time.
     *
     * @param isDone The completion status of the task.
     * @param taskDescriptor The description of the task.
     * @param temp The array containing task data, including the event start and end times.
     * @return The corresponding Event object with the parsed description, start time, and end time.
     * @throws IllegalArgumentException if the task data is incomplete or invalid.
     */
    private static Event fromEventStorageString(boolean isDone, String taskDescriptor, String[] temp)
            throws IllegalArgumentException {
        if (temp.length != 5 || temp[3].isEmpty() || temp[4].isEmpty()) {
            throw new IllegalArgumentException("Event task has missing values.");
        }
        LocalDateTime start = Parser.parseOutputString(temp[3]);
        LocalDateTime end = Parser.parseOutputString(temp[4]);
        return new Event(taskDescriptor, start, end, isDone);
    }

    public abstract String toStorageString();

    @Override
    public abstract String toString();

}
