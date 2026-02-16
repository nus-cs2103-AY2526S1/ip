package duke.task;

import duke.exception.IncorrectFormatException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String start;
    private String end;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String name, String start, String end) {
        super(name);
        this.convertToDate(start, end);
    }

    public void convertToDate(String start, String end) {
        // --- Parse start ---
        try {
            this.start = start.trim();
            String[] elems = this.start.split(" ");

            // Parse date
            String[] dates = elems[0].split("/");
            if (dates.length != 3) {
                throw new IncorrectFormatException();
            }
            int year = Integer.parseInt(dates[0]);
            int month = Integer.parseInt(dates[1]);
            int day = Integer.parseInt(dates[2]);

            int hour = 0;
            int minute = 0;

            // Parse time if provided
            if (elems.length == 2) {
                String[] times = elems[1].split(":");
                if (times.length != 2) {
                    throw new IncorrectFormatException();
                }
                hour = Integer.parseInt(times[0]);
                minute = Integer.parseInt(times[1]);
            }

            LocalDateTime startDateTime = LocalDateTime.of(year, month, day, hour, minute);
            this.startDate = startDateTime;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            this.start = startDateTime.format(formatter);

        } catch (IncorrectFormatException | DateTimeException | NumberFormatException e) {
            this.startDate = null; // fallback if parsing fails
        }

        // --- Parse end ---
        try {
            this.end = end.trim();
            String[] elems = this.end.split(" ");

            // Parse date
            String[] dates = elems[0].split("/");
            if (dates.length != 3) {
                throw new IncorrectFormatException();
            }
            int year = Integer.parseInt(dates[0]);
            int month = Integer.parseInt(dates[1]);
            int day = Integer.parseInt(dates[2]);

            int hour = 0;
            int minute = 0;

            // Parse time if provided
            if (elems.length == 2) {
                String[] times = elems[1].split(":");
                if (times.length != 2) {
                    throw new IncorrectFormatException();
                }
                hour = Integer.parseInt(times[0]);
                minute = Integer.parseInt(times[1]);
            }

            LocalDateTime endDateTime = LocalDateTime.of(year, month, day, hour, minute);
            this.endDate = endDateTime;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            this.end = endDateTime.format(formatter);

        } catch (IncorrectFormatException | DateTimeException | NumberFormatException e) {
            this.endDate = null; // fallback if parsing fails
        }
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
