package model;


/**
 * Represents a Todo Task
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task
     *
     * @param description  A short description of the task
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Returns the type of todo task
     *
     * @return type represent as string "T"
     */
    @Override
    public String getType() {
        return "T";
    }
}
