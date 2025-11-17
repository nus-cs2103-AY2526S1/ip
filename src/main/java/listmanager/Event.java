package listmanager;

import customexceptions.IncompleteTaskException;
import parser.DateTimeParser;
import parser.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Subtype of <code>Task</code>, aside from a task name
 * it also has a start and end date
 */
public class Event extends Task {
    private String start;
    private String end;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
    private Parser parser = new Parser();
    private DateTimeParser dateTimeParser = new DateTimeParser();

    private static final DateTimeFormatter DATE_DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter TIME_DISPLAY_FORMAT = DateTimeFormatter.ofPattern("h a");

    private static final int REQUIRED_SEGMENTS_WITH_TIMES = 4;
    private static final int MIN_EXPECTED_SEGMENTS = 2;
    private static final int MAX_EXPECTED_SEGMENTS = 4;


    public Event(String taskDescriptor) throws IncompleteTaskException {
        super(taskDescriptor);
        descriptorProcessor(taskDescriptor);
    }

    /**
     * Displays event task in string format with
     * completion status and start and end date.
     *
     * @return Task name with status and start and end date in string format.
     */
    @Override
    public String getTaskWithStatus() {
        return "[E]"
                + "[" + getStatus() + "] "
                + getName() + " "
                + getEventPeriod()  + " "
                + super.getTags();
    }

    /**
     * Converts task to string format for storing to file.
     *
     * @return String that contains information of the task for storing.
     */
    @Override
    public String toStringFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event,").append(super.taskDescriptor + ",").append(super.isComplete);

        //Append tags
        for (int i = 0; i < super.taskTags.size(); i++) {
            sb.append("," + super.taskTags.get(i).getName());
        }
        return sb.toString();
    }


    public String getEventPeriod() {
        StringBuilder sb = new StringBuilder();
        sb.append("(from: ");
        appendDateTime(sb, startDate, startTime, start);

        sb.append(" to: ");
        appendDateTime(sb, endDate, endTime, end);
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
     * Processes taskDescriptor and splits it into task name and start and end date.
     *
     * @param taskDescriptor String of user input passed into constructor.
     * @return Task name in string format.
     * @throws IncompleteTaskException If taskDescriptor is in known format but incomplete.
     */
    public void descriptorProcessor(String taskDescriptor) throws IncompleteTaskException {
        List<String> words = parser.stringSplitter(taskDescriptor, " ", " /from ", " /to ");
        if (words.size() < MIN_EXPECTED_SEGMENTS) {
            throw new IncompleteTaskException("please include the task name, thank you.");
        } else {

            //words length should at most be 4.
            assert (words.size() <= MAX_EXPECTED_SEGMENTS): "word segments exceed expected amount";

             if (words.size() < REQUIRED_SEGMENTS_WITH_TIMES) {
                throw new IncompleteTaskException("please include start and end time using /from and /to for events");
            }
             
            super.taskName = words.get(1);
            this.start = words.get(2);
            this.end = words.get(3);
            //The idea to abstract date time parsing came from ClaudeAI.
            //Consulted with AI on potential ways to improve code quality.
            this.startDate = dateTimeParser.parseDate(this.start);
            this.startTime = dateTimeParser.parseTime(this.start);
            this.endDate = dateTimeParser.parseDate(this.end);
            this.endTime = dateTimeParser.parseTime(this.end);

        }
    }
}
