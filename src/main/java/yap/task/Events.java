package yap.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A scheduled event with a date, start time, and end time.
 */
public class Events extends Task {
    private static DateTimeFormatter dateOut = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static DateTimeFormatter timeIn = DateTimeFormatter.ofPattern("HHmm"); // e.g., 1800
    private static DateTimeFormatter timeOut = DateTimeFormatter.ofPattern("h:mma"); // e.g., 6:00PM

    private LocalDate date;
    private LocalTime start;
    private LocalTime end;

    /**
     * Creates an event from a name, ISO date, and HHmm start/end times.
     *
     * @param name event name
     * @param dateStr date in ISO format, e.g., "2019-12-02"
     * @param startStr start time in HHmm, e.g., "1800"
     * @param endStr end time in HHmm, e.g., "2000"
     * @throws java.time.format.DateTimeParseException if any input is malformed
     */
    public Events(String name, String dateStr, String startStr, String endStr) {
        super(name);
        assert dateStr != null && startStr != null && endStr != null : "Event fields must be present";
        this.date = LocalDate.parse(dateStr); // yyyy-MM-dd
        this.start = LocalTime.parse(startStr, timeIn); // HHmm
        this.end = LocalTime.parse(endStr, timeIn); // HHmm
        assert !end.isBefore(start) : "Event end must not be before start";
    }

    /**
     * Sets the date of the event.
     *
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the date of the event from an ISO date string.
     *
     * @param iso the ISO date string to parse and set
     */
    public void setDate(String iso) {
        this.date = LocalDate.parse(iso, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Sets the start time of the event.
     *
     * @param hhmm the start time in HHmm format
     */
    public void setStart(String hhmm) {
        this.start = LocalTime.parse(hhmm, DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Sets the end time of the event.
     *
     * @param hhmm the end time in HHmm format
     */
    public void setEnd(String hhmm) {
        this.end = LocalTime.parse(hhmm, DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Returns the date of the event.
     *
     * @return the event date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the event start time
     */
    public LocalTime getStart() {
        return start;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the event end time
     */
    public LocalTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s %s to: %s)",
                super.toString(), date.format(dateOut), start.format(timeOut), end.format(timeOut));
    }
}
