package david.task;

import david.exception.DavidException;
import david.exception.EmptyDescriptionException;
import david.exception.FormatException;
import david.exception.InvalidTypeException;

/**
 * A parent class for all tasks.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Shows whether the task is done.
     *
     * @return 1 if the task is done, 0 otherwise.
     */
    public int getDone() {
        return (isDone ? 1 : 0);
    }

    public String getDoneString() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Creates a task from the command line.
     *
     * @param s The entire command.
     * @return A task given by the command.
     * @throws DavidException If parsing the task fails.
     */
    public static Task of(String s) throws DavidException {
        String[] strArr = s.split(" ", 2);
        assert strArr.length > 0 : "String array should never be empty";
        TaskType type = TaskType.of(strArr[0]);
        validatePresence(strArr);

        switch (type) {
        case TODO:
            return createToDo(strArr[1]);

        case DEADLINE:
            return createDeadline(strArr[1]);

        case EVENT:
            return createEvent(strArr[1]);

        default:
            throw new InvalidTypeException(strArr[0]);
        }
    }

    private static void validatePresence(String[] strArr) throws EmptyDescriptionException {
        if (strArr.length <= 1) {
            throw new EmptyDescriptionException(strArr[0]);
        }
    }

    private static Task createToDo(String description) {
        return new ToDo(description);
    }

    private static Task createDeadline(String input) throws FormatException {
        String[] by = input.split(" /by ", 2);
        if (by.length < 2) {
            String m = "the command format of deadline should be: "
                    + "deadline [task name] /by [time].";
            throw new FormatException(m);
        }
        String description = by[0];
        String end = by[1];
        return new Deadline(description, end);
    }

    private static Task createEvent(String input) throws FormatException {
        String m = "the command format of event should be: "
                + "event [task name] /from [start time] /to [end time].";
        String[] from = input.split(" /from ", 2);
        if (from.length < 2) {
            throw new FormatException(m);
        }
        String[] to = from[1].split(" /to ", 2);
        if (to.length < 2) {
            throw new FormatException(m);
        }
        String description = from[0];
        String startTime = to[0];
        String endTime = to[1];
        return new Event(description, startTime, endTime);
    }

    /**
     * Creates a task from the input file.
     *
     * @param line A line from the input file.
     * @return A task given by the input line.
     * @throws DavidException If parsing the task fails.
     */
    public static Task create(String line) throws DavidException {
        String[] strArr = line.split("\\s*\\|\\s*");
        assert strArr.length > 0 : "String array should never be empty";
        String type = strArr[0];
        validateType(type);
        validateStatus(strArr);
        boolean flag = strArr[1].equals("1"); //true if is done

        switch (type) {
        case "T":
            return createToDoFromFile(strArr, flag);

        case "D":
            return createDeadlineFromFile(strArr, flag);

        case "E":
            return createEventFromFile(strArr, flag);

        default:
            throw new InvalidTypeException(type);
        }
    }

    private static void validateType(String type) throws InvalidTypeException {
        if (!type.equals("T") && !type.equals("D") && !type.equals("E")) {
            throw new InvalidTypeException(type);
        }
    }

    private static void validateStatus(String[] strArr) throws FormatException {
        String status = "wrong status format (1 for done, 0 for undone) in the input line.";
        if (strArr.length <= 1 || (!strArr[1].equals("0") && !strArr[1].equals("1"))) {
            throw new FormatException(status);
        }
    }

    private static Task createToDoFromFile(String[] strArr, boolean flag)
                                                            throws FormatException {
        String todo = "the input format of todo should be: T | 0/1 | [description].";
        if (strArr.length <= 2) {
            throw new FormatException(todo);
        }
        String description = strArr[2];
        Task task = new ToDo(description);
        if (flag) {
            task.markAsDone();
        }
        return task;
    }

    private static Task createDeadlineFromFile(String[] strArr, boolean flag)
                                                            throws FormatException {
        String ddl = "the input format of deadline should be: "
                + "D | 0/1 | [description] | [end time].";
        if (strArr.length <= 3) {
            throw new FormatException(ddl);
        }
        String description = strArr[2];
        String by = strArr[3];
        Task task = new Deadline(description, by);
        if (flag) {
            task.markAsDone();
        }
        return task;
    }

    private static Task createEventFromFile(String[] strArr, boolean flag)
                                                            throws FormatException {
        String event = "the input format for event should be: "
                + "E | 0/1 | [description] | [start time] - [end time].";
        if (strArr.length <= 3) {
            throw new FormatException(event);
        }
        String description = strArr[2];
        String[] timeParts = strArr[3].split("\\s*-\\s*");
        if (timeParts.length != 2) {
            throw new FormatException(event);
        }
        String from = timeParts[0];
        String to = timeParts[1];
        Task task = new Event(description, from, to);
        if (flag) {
            task.markAsDone();
        }
        return task;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getDoneString(), this.description);
    }

    /**
     * @return Input format of the task.
     */
    public String serialize() {
        return String.format("%d | %s", this.getDone(), this.description);
    }

    /**
     * @return The copy of the task.
     */
    public abstract Task copy();
}
