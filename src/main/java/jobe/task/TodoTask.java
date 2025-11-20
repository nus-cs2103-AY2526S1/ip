package jobe.task;

import jobe.exception.JobeException;

/**
 * Represents a todo task.
 */
public class TodoTask extends Task {
    
    public TodoTask(String taskDescription) {
        super(taskDescription);
    }
    
    public TodoTask(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void throwDuplicateTaskException() throws JobeException {
        throw new JobeException("OOPS!!!! A Todo task with the same description already exists!\n"
                + "Please create a different task!");
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToFileFormat() {
        return "T" + " / " + super.convertToFileFormat();
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
