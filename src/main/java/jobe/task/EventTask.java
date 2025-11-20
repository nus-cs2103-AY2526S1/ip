package jobe.task;

import static jobe.dateutils.DateUtils.convertToDateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jobe.dateutils.DateUtils;
import jobe.exception.JobeException;
import jobe.stringutils.StringUtils;

/**
 * Represents an event task.
 */
public class EventTask extends Task {
    
    private String startDate;
    private String endDate;
    
    /**
     * Creates an EventTask object.
     *
     * @param taskDescription Description of task.
     * @param startDate Start date of task.
     * @param endDate End date of task.
     */
    public EventTask(String taskDescription, String startDate, String endDate) {
        super(taskDescription);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    /**
     * Creates an EventTask object if completion status is explicitly defined.
     *
     * @param taskDescription Description of task.
     * @param startDate Start date of task.
     * @param endDate End date of task.
     * @param isDone Completion status of task.
     */
    public EventTask(String taskDescription, String startDate, String endDate, boolean isDone) {
        super(taskDescription, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    /**
     * Creates an EventTask with the formatted arguments.
     *
     * @param taskDescription String description of task.
     * @param startDate Start date/time of event.
     * @param endDate End date/time of event.
     * @return A EventTask object with the corresponding formatted arguments.
     * @throws JobeException If formatting of start or end date/time fails.
     */
    public static EventTask createEventTask(String taskDescription,
                                            String startDate,
                                            String endDate) throws JobeException {
        String startDateString = StringUtils.splitStringAndRemoveFirstWord(startDate);
        String endDateString = StringUtils.splitStringAndRemoveFirstWord(endDate);
        assert startDateString != null : "Start date/time should never be null";
        assert !startDateString.isEmpty() : "Start date/time should never be empty";
        assert endDateString != null : "End date/time should never be null";
        assert !endDateString.isEmpty() : "End date/time should never be empty";
        
        boolean isValidDate = DateUtils.isStartDateBeforeEndDate(startDateString, endDateString);
        if (!isValidDate) {
            throw new JobeException("OOPS!!!! Your end date is before your start date. "
                    + "Please check your input dates!");
        }
        
        String formattedStartDate = convertToDateTime(startDateString);
        String formattedEndDate = convertToDateTime(endDateString);
        assert formattedStartDate != null : "Start date should never be null";
        assert formattedEndDate != null : "End date should never be null";
        
        return new EventTask(taskDescription, formattedStartDate, formattedEndDate);
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
        
        if (!(task instanceof EventTask)) {
            return false;
        }
        
        EventTask otherTask = (EventTask) task;
        boolean isSameStartDate = this.startDate.trim().equals(otherTask.startDate.trim());
        boolean isSameEndDate = this.endDate.trim().equals(otherTask.endDate.trim());
        
        if (isSameStartDate && isSameEndDate) {
            return true;
        }
        
        return isOverlap(otherTask);
    }
    
    /**
     * Checks whether the time range of the current event task overlaps with another task.
     *
     * @param otherTask Task to be compared.
     * @return A boolean representing whether the time range of the two tasks overlaps.
     */
    private boolean isOverlap(EventTask otherTask) {
        DateTimeFormatter formatter = DateUtils.OUTPUT_DATE_TIME_FORMATTER;
        LocalDateTime thisStart = LocalDateTime.parse(this.startDate, formatter);
        LocalDateTime thisEnd = LocalDateTime.parse(this.endDate, formatter);
        LocalDateTime otherStart = LocalDateTime.parse(otherTask.startDate, formatter);
        LocalDateTime otherEnd = LocalDateTime.parse(otherTask.endDate, formatter);
        
        // Overlap exists if thisStart < otherEnd && otherStart < thisEnd
        return thisStart.isBefore(otherEnd) && otherStart.isBefore(thisEnd);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void throwDuplicateTaskException() throws JobeException {
        throw new JobeException("OOPS!!!! An Event with the same description "
                + "or overlapping time already exists!\n"
                + "Please choose a different time period!");
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToFileFormat() {
        return "E" + " / " + super.convertToFileFormat() + " / " + startDate + " / " + endDate;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + startDate + " -> " + endDate + ")";
    }
}
