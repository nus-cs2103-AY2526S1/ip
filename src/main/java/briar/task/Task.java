package briar.task;

import briar.exception.WrongFormatException;

/**
 * Represents a task that can be stored in a TaskList.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Represents the type of Task a task is.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Creates a task.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task of the specified type with the given command.
     * @param taskType Type of the task.
     * @param command Details of the task.
     * @return Task of the specified type with the details in command.
     * @throws WrongFormatException If the formatting in command is wrong.
     */
    public static Task createTask(TaskType taskType, String command) throws WrongFormatException {
        assert taskType != null : "taskType should not be null";
        assert command != null : "command should not be null";
        String[] splitCommand;
        switch (taskType) {
        case TODO:
            return createTodoTask(command);
            // No break as there is a return statement
        case DEADLINE:
            return createDeadlineTask(command);
            // No break as there is a return statement
        case EVENT:
            return createEventTask(command);
            // No break as there is a return statement
        default:
            break;
        }
        return null;
    }

    private static Todo createTodoTask(String command) {
        return new Todo(command);
    }

    private static Deadline createDeadlineTask(String command) throws WrongFormatException {
        if (!command.contains("/by")) {
            throw new WrongFormatException();
        }
        String[] splitCommand = command.split("/by");
        String description = splitCommand[0].substring(0, splitCommand[0].length() - 1);
        String date = splitCommand[1].substring(1);
        return new Deadline(description, date);
    }

    private static Event createEventTask(String command) throws WrongFormatException {
        if (!command.contains("/from") || !command.contains("/to")) {
            throw new WrongFormatException();
        }
        String[] splitCommand = command.split("/from");
        String[] fromToString = splitCommand[1].substring(1).split("/to");
        String description = splitCommand[0].substring(0, splitCommand[0].length() - 1);
        String from = fromToString[0].substring(0, fromToString[0].length() - 1);
        String to = fromToString[1].substring(1);
        return new Event(description, from, to);
    }

    /**
     * Returns the isDone status of the task as a string.
     */
    @Override
    public String toString() {
        String str = "[";
        if (isDone) {
            str += "X";
        } else {
            str += " ";
        }
        str += "] " + description;
        return str;
    }

    /**
     * Returns the isDone status of the task as a string to be written into a file as text.
     */
    public String toText() {
        String str = "";
        if (isDone) {
            str += "1";
        } else {
            str += "0";
        }
        str += "|" + description;
        return str;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }
}
