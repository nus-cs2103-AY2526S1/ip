package david.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * A task that has starting time and ending time.
 */
public class Event extends Task {
    private static final String TYPE = "E";
    private String from;
    private String to;

    private boolean hasFromTime;
    private boolean isFromValidDate;
    private LocalDate fromDate;
    private LocalDateTime fromDateTime;
    private boolean hasToTime;
    private boolean isToValidDate;
    private LocalDate toDate;
    private LocalDateTime toDateTime;

    /**
     * @param description Description of the task event.
     * @param from Starting time of the event.
     * @param to Ending time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        fromInit();
        toInit();
    }

    private void fromInit() {
        try {
            if (this.from.contains(" ")) { //time?
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                this.hasFromTime = true;
                this.isFromValidDate = true;
                this.fromDateTime = LocalDateTime.parse(this.from, dtFormatter);
            } else {
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                this.hasFromTime = false;
                this.isFromValidDate = true;
                this.fromDate = LocalDate.parse(this.from, dtFormatter);
            }
        } catch (DateTimeParseException e) {
            this.hasFromTime = false;
            this.isFromValidDate = false;
        }
    }

    private void toInit() {
        try {
            if (this.to.contains(" ")) { //time?
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                this.hasToTime = true;
                this.isToValidDate = true;
                this.toDateTime = LocalDateTime.parse(this.to, dtFormatter);
            } else {
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                this.hasToTime = false;
                this.isToValidDate = true;
                this.toDate = LocalDate.parse(this.to, dtFormatter);
            }
        } catch (DateTimeParseException e) {
            this.hasToTime = false;
            this.isToValidDate = false;
        }
    }

    private String printFromDate() {
        if (!isFromValidDate) {
            return this.from;
        }
        if (hasFromTime) {
            DateTimeFormatter outputFormatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy, h:mma", Locale.ENGLISH);
            return fromDateTime.format(outputFormatter);
        } else {
            DateTimeFormatter outputFormatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
            return fromDate.format(outputFormatter);
        }
    }

    private String printToDate() {
        if (!isToValidDate) {
            return this.to;
        }
        if (hasToTime) {
            DateTimeFormatter outputFormatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy, h:mma", Locale.ENGLISH);
            return toDateTime.format(outputFormatter);
        } else {
            DateTimeFormatter outputFormatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
            return toDate.format(outputFormatter);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", TYPE,
                super.toString(), printFromDate(), printToDate());
    }

    @Override
    public String serialize() {
        return String.format("%s | %s | %s - %s", TYPE,
                super.serialize(), printFromDate(), printToDate());
    }

    @Override
    public Task copy() {
        Task t = new Event(this.getDescription(), this.from, this.to);
        if (this.getIsDone()) {
            t.markAsDone();
        }
        return t;
    }
}
