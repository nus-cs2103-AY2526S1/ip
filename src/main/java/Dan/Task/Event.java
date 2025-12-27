package Dan.Task;

import Dan.Parser.DateParser;

import java.time.LocalDate;
import java.util.ArrayList;

public class Event extends Task {
    LocalDate startDate;
    LocalDate endDate;

    /**
     * Constructs a new Event task with the specified completion status, description, and date range.
     *
     * @param isDone a String representation of the task's completion status ("true" or "false")
     * @param description the description of the event task
     * @param from the LocalDate representing the start date of the event
     * @param to the LocalDate representing the end date of the event
     */
    public Event(String isDone, String description, LocalDate from, LocalDate to) {
        super(isDone, description);
        this.startDate = from;
        this.endDate = to;
    }

    /**
     * Creates a new Event task from the provided task information.
     * Expects exactly 4 elements: completion status, description, from date string, and to date string.
     *
     * @param taskInfo an ArrayList containing task information in the order: [isDone, description, fromDateString, toDateString]
     * @return a new Event Task object created from the provided information
     * @throws IllegalArgumentException if taskInfo does not contain exactly 4 elements or if date parsing fails
     */
    public static Task create(ArrayList<String> taskInfo) throws IllegalArgumentException {
        if (taskInfo.size() != 4) {
            throw new IllegalArgumentException();
        }

        String isDone = taskInfo.get(0);
        String description = taskInfo.get(1);

        LocalDate fromDate = DateParser.parseDate(taskInfo.get(2));
        LocalDate toDate = DateParser.parseDate(taskInfo.get(3));

        if (fromDate == null || toDate == null) {
            return null;
        } else {
            return new Event(isDone, description, fromDate, toDate);
        }
    }

    /**
     * Gets the start date of this event.
     *
     * @return the LocalDate representing when this event begins
     */
    public LocalDate getFromDate() {
        return this.startDate;
    }

    /**
     * Gets the end date of this event.
     *
     * @return the LocalDate representing when this event ends
     */
    public LocalDate getToDate() {
        return this.endDate;
    }

    /**
     * Gets the type of this task.
     *
     * @return TaskType.EVENT indicating this is an event task
     */
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    public LocalDate getReminderDate() {
        return this.endDate;
    }

    /**
     * Returns a string representation of the event task, including its type marker,
     * completion status, description, and date range.
     *
     * @return a formatted string in the format "[E][status] description (from:startDate to:endDate)"
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.startDate + " to:" + this.endDate + ")";
    }
}


