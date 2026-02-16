package task;

import commands.CommandsEnum;
import ineffaexceptions.IneffaException;

/**
 * Contains information a task needs to have.
 */
public abstract class Task {
    private final String fullInfo;
    private final String description;
    private boolean isDone;
    private final String commandCode;

    /**
     * Initialises fields of class.
     */
    public Task(String fullInfo, String description, String commandCode) {
        this.fullInfo = fullInfo;
        this.description = description;
        this.isDone = false;
        this.commandCode = commandCode;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }

    /**
     * Creates task based on the type of task user wants to create.
     *
     * @param command type of task to create.
     * @param taskInfo description and dates (if required) of task.
     * @return Task task.
     * @throws IneffaException If invalid format for parameters.
     */
    public static Task parseTask(CommandsEnum command, String taskInfo) throws IneffaException {
        if (taskInfo.isBlank()) {
            throw new IneffaException("Please enter a description for a " + command + " task.");
        }
        return switch (command) {
        case TODO -> ToDos.createTask(taskInfo);
        case DEADLINE -> Deadlines.createTask(taskInfo);
        case EVENT -> Events.createTask(taskInfo);
        default -> throw new IneffaException("unrecognised cmd type: " + command);
        };
    }

    /**
     * Creates task based on the type of task user wants to create & marks as done if specified.
     *
     * @param command type of task to create.
     * @param isDone mark task as done if true
     * @param taskInfo description and dates (if required) of task.
     * @return Task task.
     * @throws IneffaException If invalid format for parameters.
     */
    public static Task parseTask(CommandsEnum command, boolean isDone, String taskInfo) throws IneffaException {
        Task task = parseTask(command, taskInfo);
        if (isDone) {
            task.setDone();
        }
        assert (task instanceof ToDos)
                || (task instanceof Deadlines)
                || (task instanceof Events) : "Task not of type ToDos, Deadlines, Events";
        return task;
    }

    /**
     * Checks if task description contains description
     * @param description Description method caller wants to find
     * @return boolean
     */
    public boolean containsDescription(String description) {
        return this.description.contains(description);
    }

    /**
     * Generates a suitable string of task to add into file
     *
     * @return Suitable string format of task.
     */
    public String getFileString() {
        String body = this.fullInfo.trim();
        String isDone = this.isDone ? "1" : "0";
        String cmdType = this.commandCode;
        String[] arr = {cmdType, isDone, body};
        return String.join(" | ", arr) + "\n";
    }
}
