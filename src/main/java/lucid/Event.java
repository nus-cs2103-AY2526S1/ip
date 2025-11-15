package lucid;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * An event with a start and an end, represented by two LocalDates for start and end dates
 * and two LocalTimes if needed for start and end timings
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Creates an event with the specified characteristics
     * @param name String containing name of the event
     * @param start String specifying event start date and time
     * @param end String specifying event end date and time
     */
    public Event(String name, String start, String end) {
        super(name);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            String[] startDateTime = Parser.parseDateTimeString(start);
            String[] endDateTime = Parser.parseDateTimeString(end);
            this.startDate = LocalDate.parse(startDateTime[0]);
            this.endDate = LocalDate.parse(endDateTime[0]);
            if (startDateTime[1] != null) {
                this.startTime = LocalTime.parse(startDateTime[1], timeFormatter);
            }
            if (endDateTime[1] != null) {
                this.endTime = LocalTime.parse(endDateTime[1], timeFormatter);
            }

        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateTimeToString(startDate, startTime) + " to: "
                + dateTimeToString(endDate, endTime) + ")";
    }

    @Override
    public String toDataString() {
        return Storage.TYPE_EVENT + " | " + (this.isComplete() ? Storage.STATUS_DONE : Storage.STATUS_NOT_DONE) + " | "
                + this.getName() + " | " + this.startDate + (this.startTime == null ? "" : "-"
                + this.startTime.format(DateTimeFormatter.ofPattern("HHmm"))) + " | "
                + this.endDate + (this.endTime == null ? "" : "-"
                + this.endTime.format(DateTimeFormatter.ofPattern("HHmm"))) + "\n";
    }

    /**
     * Returns string representation of date and time parameters
     * @param date date to convert to string
     * @param time time to convert to string
     * @return string representation of date and time together
     */
    public String dateTimeToString(LocalDate date, LocalTime time) {
        return date.getMonth() + " " + date.getDayOfMonth() + " " + date.getYear() + (time == null ? "" : " " + time);
    }
}
