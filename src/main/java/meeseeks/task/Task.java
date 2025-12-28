package meeseeks.task;

public class Task {
    private static final String DONE_STATUS = "[X]";
    private static final String NOT_DONE_STATUS = "[ ]";
    private static final String DONE_FILE_VALUE = "1";
    private static final String NOT_DONE_FILE_VALUE = "0";
    
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatus() {
        return isDone ? DONE_STATUS : NOT_DONE_STATUS;
    }

    public String getName() { 
        return name; 
    }
    
    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return getStatus() + " " + name;
    }

    public String toFileFormat() {
        return "T | " + (isDone ? DONE_FILE_VALUE : NOT_DONE_FILE_VALUE) + " | " + name;
    }

}


