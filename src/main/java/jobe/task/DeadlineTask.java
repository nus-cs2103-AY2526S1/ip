package jobe.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jobe.dateutils.DateUtils;
import jobe.exception.JobeException;
import jobe.stringutils.StringUtils;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {
    
    private String deadline;
    
    /**
     * Creates a DeadlineTask object.
     *
     * @param taskDescription Description of task.
     * @param deadline Deadline of task.
     */
    public DeadlineTask(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }
    
    /**
     * Creates a DeadlineTask object if the task completion status is explicitly specified.
     *
     * @param taskDescription Description of task.
     * @param deadline Deadline of task.
     * @param isDone Completion status of task.
     */
    public DeadlineTask(String taskDescription, String deadline, boolean isDone) {
        super(taskDescription, isDone);
        this.deadline = deadline;
    }
    
    /**
     * Creates a DeadlineTask object with formatted arguments.
     *
     * @param taskDescription String description of task.
     * @param deadline Deadline of task.
     * @return A DeadlineTask with the corresponding formatted arguments.
     * @throws JobeException If formatting of date fails.
     */
    public static DeadlineTask createDeadlineTask(String taskDescription, String deadline) throws JobeException {
        String deadlineString = StringUtils.splitStringAndRemoveFirstWord(deadline);
        assert deadlineString != null : "Deadline should never be null";
        assert !deadlineString.isEmpty() : "Deadline should never be empty";
        
        String formattedDeadline = DateUtils.convertToDateTime(deadlineString);
        assert formattedDeadline != null : "Deadline should never be null";
        
        return new DeadlineTask(taskDescription, formattedDeadline);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDuplicate(Task task) {
        boolean isSameDescription = super.isDuplicate(task);
        
        if (!isSameDescription) {
            return false;
        }
        
        if (!(task instanceof DeadlineTask)) {
            return false;
        }
        
        DeadlineTask otherTask = (DeadlineTask) task;
        String thisDeadlineTrimmed = this.deadline.trim();
        String otherDeadlineTrimmed = otherTask.deadline.trim();
        boolean isSameDeadline = thisDeadlineTrimmed.equals(otherDeadlineTrimmed);
        
        if (isSameDeadline) {
            return true;
        }
        
        return isBeforeDeadline(otherTask);
    }
    
    /**
     * Checks whether the deadline of the current task is before the deadline of the task to be compared.
     *
     * @param otherTask Task to be compared.
     * @return A boolean representing whether the deadline of current task is before the other task.
     */
    private boolean isBeforeDeadline(DeadlineTask otherTask) {
        DateTimeFormatter formatter = DateUtils.OUTPUT_DATE_TIME_FORMATTER;
        LocalDateTime dt1 = LocalDateTime.parse(otherTask.deadline, formatter);
        LocalDateTime dt2 = LocalDateTime.parse(this.deadline, formatter);
        return dt1.isBefore(dt2);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void throwDuplicateTaskException() throws JobeException {
        throw new JobeException("OOPS!!!! A Deadline task with the same description already "
                + "exists with the same or an earlier deadline.\n"
                + "Please choose a different deadline!");
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToFileFormat() {
        return "D" + " / " + super.convertToFileFormat()
                + " / " + deadline;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
