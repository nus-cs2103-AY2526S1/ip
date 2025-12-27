package fatty.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fatty.FattyException;

/**
 * Main Skeleton of a Task
 */
public abstract class Task {
    protected static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    protected static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    protected boolean isMark;
    protected final String description;

    /**
     * Constructor for Task
     * @param description task description
     */
    public Task(String description) {
        this.isMark = false;
        this.description = description;
    }

    /**
     * Override toString to appropriate format.
     * @return string format
     */
    @Override
    public String toString() {
        String check = "[ ]";
        if (this.isMark) {
            check = "[X]";
        }
        return check + " " + this.description;
    }

    /**
     * make isMark true
     */
    public void mark() {

        this.isMark = true;
    }

    public void unmark() {
        this.isMark = false;
    }

    /**
     * Reformat Task into "type | status | description | times (d/M/yyyy HHmm)" to save into local file.
     * @return string
     */
    public abstract String toDataString();

    /**
     * Reads data from loaded file and refactor into appropriate Task Object.
     *
     * @param data Data from loaded file.
     * @return Task
     * @throws FattyException Unrecognised Task
     */
    public static Task fromDataString(String data) throws FattyException {
        String[] parts = data.split(" \\| ");
        String type = parts[0];
        boolean done = parts[1].equals("1");
        switch (type) {
            case "T":
                Task todo = new ToDoTask(parts[2]);
                if (done) {
                    todo.mark();
                }
                return todo;
            case "D":
                LocalDateTime deadlineBy = LocalDateTime.parse(parts[3], SAVE_FORMAT);
                Task deadline = new DeadlineTask(parts[2], deadlineBy);
                if (done) {
                    deadline.mark();
                }
                return deadline;
            case "E":
                LocalDateTime eventBy = LocalDateTime.parse(parts[3], SAVE_FORMAT);
                LocalDateTime eventTo = LocalDateTime.parse(parts[4], SAVE_FORMAT);

                Task event = new EventTask(parts[2], eventBy, eventTo);
                if (done) {
                    event.mark();
                }
                return event;
            default:
                throw new FattyException("Unknown task type in file!");
        }
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isMark() {
        return this.isMark;
    }
}
