package model;

/**
 * Represents a Deadline Task
 * Contains a date as deadline
 */
public class Deadline extends Task{
    private String deadline;

   /**
     * Constructs a Deadline Task
     *
     * @param description  A short description of the task
     * @param deadline     The date of task deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the type of task
     *
     * @return type represent as string "D"
     */
    @Override
    public String getType() {
        return "D";
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + getStatus() + " " + description + " (by:" + deadline + ")";
    }
}