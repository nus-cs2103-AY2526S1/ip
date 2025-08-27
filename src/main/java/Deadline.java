import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
        this.date = parseDate(by);
    }

    public String getBy() {
        return by;
    }

    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        if (date != null) {
            String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return super.toString() + " (by: " + formattedDate + ")";
        } else {
            return super.toString() + " (by: " + by + ")";
        }
    }
}