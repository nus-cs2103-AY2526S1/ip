package burrito.tasklist;

public class Event extends Task{
    protected String description;
    protected boolean isDone;
    protected String deadlineStart;
    protected String deadlineEnd;






    public Event(String description, String deadlineStart, String deadlineEnd) {
        super(description);
        this.isDone = false;
        this.deadlineStart = deadlineStart;
        this.deadlineEnd = deadlineEnd;

    }


    /**
     * Getter for the deadlineStart variable
     *
     * @return String value of deadlineStart
     */
    public String getDeadlineStart() {

        return deadlineStart;
    }

    /**
     * Getter for the deadlineEnd variable
     *
     * @return String value of deadlineEnd
     */
    public String getDeadlineEnd() {
        return deadlineEnd; }

    /**
     * Getter for the type of event
     *
     * @return String representation of event type
     */
    @Override
    public String getType() {

        return "E";
    }

    /**
     * toString function
     *
     * @return String representation of object
     */
    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + this.getDescription() +
                " (from: " + deadlineStart + " to: " + deadlineEnd + ")";
    }
}
