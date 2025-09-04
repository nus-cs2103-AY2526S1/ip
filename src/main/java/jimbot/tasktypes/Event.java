package jimbot.tasktypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from.toLocalDate();
    }

    public LocalDate getTo() {
        return to.toLocalDate();
    }

    /**
     * Converts a given date and time into String with MMM dd yyyy, HH:mm format.
     *
     * @param dateTime DateTime in dd/MM/yyyy format.
     * @return String represent of given date and time
     */
    private String dateTimeToString(LocalDateTime dateTime) {
        boolean isMidnight = dateTime.toLocalTime().equals(LocalTime.MIDNIGHT);
        if (isMidnight) {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"));
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (FROM: "
                + dateTimeToString(from)
                + " TO: "
                + dateTimeToString(to) + ")";
    }
}
