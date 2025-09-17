package burrito.tasklist;

public class Task {
    protected String description;
    protected boolean isDone;



    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    /**
     * Returns the status of the Task object, depending on the value of the boolean variable isDone
     *
     * @return Returns either "X" or " " depending on the value of the boolean variable isDone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Setter for the isDone variable
     *
     * @param status The boolean value to be set
     */
    public void setStatus(boolean status) {

        isDone = status;
    }

    /**
     * Getter for the description variable
     *
     * @return String value of the description
     */
    public String getDescription() {

        return description;
    }

    /**
     * Getter for the type of event
     *
     * @return String representation of event type
     */
    public String getType() {

        return " ";
    }

    /**
     * toString function
     *
     * @return String representation of object
     */
    @Override
    public String toString() {

        return "[ ] [" + this.getStatusIcon() + "] " + description;
    }
}
