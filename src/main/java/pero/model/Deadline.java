package pero.model;

import pero.exception.PeroException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline task with a specific deadline.
 * A Deadline has a description, completion status, and a due date and time.
 */
public class Deadline extends Task {

    /** Date and time task has to be completed by */
    protected LocalDateTime byTimeObj;

    private static final String COMMAND_KEYWORD = "deadline";
    private static final String BY_SEPARATOR = " /by ";
    private static final String WRONG_FORMAT_EXCEPTION =
            "Oops! Deadline requires 'deadline [task] 'deadline [task] /by [YYYY-DD-MM HHmm]' format, try again!";

    private static final String DATE_TIME_PATTERN = "yyyy-dd-MM HHmm"; //pattern used for INPUT and STORAGE formatter
    private static final DateTimeFormatter STORAGE_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static final DateTimeFormatter FINAL_DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Deadline(String description, boolean isDone, LocalDateTime byTimeObj){
        super(description, isDone);
        this.byTimeObj = byTimeObj;
    }

    /**
     * Returns new Deadline object.
     *
     * @param input User input line.
     * @return New Deadline task according to input.
     * @throws PeroException If input is in wrong format.
     */
    public static Deadline fromInput(String input) throws PeroException{
        if (input.equals(COMMAND_KEYWORD) || !input.contains(BY_SEPARATOR)) {
            throw new PeroException(WRONG_FORMAT_EXCEPTION);
        }

        //starts at index 9, remove "deadline "
        String[] taskDeadline = input.substring(COMMAND_KEYWORD.length()+1).split(BY_SEPARATOR);

        if (taskDeadline.length < 2) {
            throw new PeroException(WRONG_FORMAT_EXCEPTION);
        }

        String task = taskDeadline[0].trim(); //trim leading/trailing spaces
        if (task.isEmpty()) {
            throw new PeroException("Task description cannot be empty, try again!");
        }
        String by = taskDeadline[1].trim();
        LocalDateTime byTimeObj = parseDateTime(by);

        return new Deadline(task,false, byTimeObj);
    }

    @Override
    public String toStorageString() {
        return "D | " + (isDone? "1" : "0") +
                " | " + this.description + " | " +
                this.byTimeObj.format(STORAGE_FORMATTER); // stores string with same pattern YYYY-DD-MM HHmm
    }

    @Override
    public LocalDate getDueDate() {
        return byTimeObj.toLocalDate(); // extracts local date portion
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byTimeObj.format(FINAL_DISPLAY_FORMATTER) + ")";
    }
}
