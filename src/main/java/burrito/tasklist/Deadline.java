package burrito.tasklist;

public class Deadline extends Task{
    protected String description;
    protected boolean isDone;
    protected String deadline;




    public Deadline(String description, String deadline) {
        super(description);
        this.isDone = false;
        this.deadline = deadline;

    }

    /**
     * Getter for the deadline variable
     *
     * @return String value of the deadline
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Getter for the type of event
     *
     * @return String representation of event type
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * toString function
     *
     * @return String representation of object
     */
    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + deadline + ")";
    }
}

