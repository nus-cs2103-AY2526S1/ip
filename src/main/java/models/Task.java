package models;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Checks if current task is occuring at {@code inputDate}, or return {@code null} if not applicable. 
     * 
     * @param inputDate user input date. 
     * @return whether current task is occuring at the provided date, or {@code null} if not applicable. 
     */
    public Boolean isOcurringAt(LocalDateTime inputDate) {
        return null;
    }

    /**
     * Marks this task as done. 
     */
    public void markAsDone() {
        isDone = true;
    }
    
    /**
     * Marks this task as undone. 
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns status icon indicate whether this task is marked as done or undone. 
     * 
     * @return {@code "X"} if the task is marked as done, or {@code " "} otherwise. 
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the String that will be stored in the file. 
     */
    public String getFormattedString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns String that will be displayed to user. 
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
