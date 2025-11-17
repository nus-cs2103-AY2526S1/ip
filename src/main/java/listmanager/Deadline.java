package listmanager;

import customexceptions.IncompleteTaskException;

import parser.DateTimeParser;
import parser.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Subtype of <code>Task</code>, aside from a task name
 * it also has a deadline date.
 */
public class Deadline extends Task {
    private String deadline;
    private LocalDate date;
    private LocalTime time;
    private Parser parser = new Parser();
    private DateTimeParser dateTimeParser = new DateTimeParser();

    private static final DateTimeFormatter DATE_DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter TIME_DISPLAY_FORMAT = DateTimeFormatter.ofPattern("h a");

    private static final int REQUIRED_SEGMENTS_WITH_TIMES = 3;
    private static final int MIN_EXPECTED_SEGMENTS = 2;
    private static final int MAX_EXPECTED_SEGMENTS = 3;

    public Deadline(String taskDescriptor) throws IncompleteTaskException {
        super(taskDescriptor);
        descriptorProcessor(taskDescriptor);
    }

    /**
     * Displays deadline task in String format complete with status and deadline.
     *
     * @return Task name, with completion status and deadline.
     */
    @Override
    public String getTaskWithStatus() {
        return "[D]"
                + "[" + getStatus() + "] "
                + getName() + " "
                + getDeadline() + " "
                + super.getTags();
    }

    /**
     * Converts task to a String format for storage.
     *
     * @return String to be stored into save file.
     */
    @Override
    public String toStringFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deadline,").append(super.taskDescriptor + ",").append(super.isComplete);

        //Append tags
        for (int i = 0; i < super.taskTags.size(); i++) {
            sb.append("," + super.taskTags.get(i).getName());
        }
        return sb.toString();

    }

    public String getDeadline() {
        StringBuilder sb = new StringBuilder();
        sb.append("(by: ");
        appendDateTime(sb, date, time, deadline);
        sb.append(")");
        return sb.toString();
    }

    private void appendDateTime(StringBuilder sb, LocalDate ld, LocalTime lt, String originalString) {
        if (ld == null) {
            sb.append(originalString);
        } else if (lt == null) {
            sb.append(ld.format(DATE_DISPLAY_FORMAT));
        } else {
            sb.append(ld.format(DATE_DISPLAY_FORMAT))
                    .append(" ")
                    .append(lt.format(TIME_DISPLAY_FORMAT));
        }
    }

    /**
     * Processes taskDescriptor and splits it into task name and deadline time.
     *
     * @param taskDescriptor String of user input passed into constructor.
     * @return Task name in string format.
     * @throws IncompleteTaskException If taskDescriptor is in known format but incomplete.
     */
    public void descriptorProcessor(String taskDescriptor) throws IncompleteTaskException {
        List<String> words = parser.stringSplitter(taskDescriptor, " ", " /by ");
        if (words.size() < MIN_EXPECTED_SEGMENTS) {
            throw new IncompleteTaskException("please include the task name, thank you.");
        }

        if (words.size() < REQUIRED_SEGMENTS_WITH_TIMES) {
            throw new IncompleteTaskException("Please add a deadline.\n Example: deadline go home /by 2pm");
        }

        //words length should at most be 3.
        assert (words.size() <= MAX_EXPECTED_SEGMENTS): "word segments exceed expected amount";

        super.taskName = words.get(1);
        this.deadline = words.get(2);
        this.date = dateTimeParser.parseDate(this.deadline);
        this.time = dateTimeParser.parseTime(this.deadline);
    }
}
