package woop.logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A class for tasks of deadline type.
 */
public class DeadlineTask extends Task {
    private static final String TASK_STRING = "D";
    private LocalDate dueDate;

    /**
     * Initialises a DeadlineTask object using name and dueDate.
     * isFinished is set to false by default.
     * dueDate is parsed into a LocalDate.
     *
     * @param name The name of task.
     * @param dueDate The due date of task.
     * @throws IllegalDescriptorException If due date given is not a valid date.
     */
    public DeadlineTask(String name, String dueDate) throws IllegalDescriptorException {
        super(name);
        try {
            this.dueDate = LocalDate.parse(dueDate);
        } catch (DateTimeParseException e) {
            throw new IllegalDescriptorException();
        }
    }

    /**
     * Initialises a DeadlineTask object using name, tag, dueDate.
     * The boolean is used to set isFinished.
     * dueDate is parsed into a LocalDate.
     * Used by Storage to initialise saved tasks.
     *
     * @param name The name of task.
     * @param isFinished Whether task is finished.
     * @param dueDate The due date of task.
     * @param tag The tag of the task.
     */
    public DeadlineTask(String name, boolean isFinished, String tag, String dueDate) {
        super(name, isFinished, tag);
        this.dueDate = LocalDate.parse(dueDate);
    }

    private String getDateString() {
        return this.dueDate.getMonth().toString().substring(0, 3)
                + " " + this.dueDate.getDayOfMonth()
                + " " + this.dueDate.getYear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSaveInfo() {
        return TASK_STRING + super.getSaveInfo()
                + DIVIDER + this.dueDate.toString();
    }

    @Override
    public String toString() {
        return "[" + TASK_STRING + "]" + super.toString()
                + " (by: " + getDateString() + ") " + getTag();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof DeadlineTask t) {
            return t.dueDate.equals(this.dueDate)
                    && super.equals(t);
        }

        return false;
    }
}
