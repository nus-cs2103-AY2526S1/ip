package burrito.tasklist;

public class Todo extends Task{
    protected String description;
    protected boolean isDone;




    public Todo(String description) {
        super(description);
        this.isDone = false;

    }


    /**
     * Getter for the type of event
     *
     * @return String representation of event type
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * toString function
     *
     * @return String representation of object
     */
    @Override
    public String toString() {
        return "[T] [" + this.getStatusIcon() + "] " + this.getDescription();
    }
}

