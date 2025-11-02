package buddy.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task with a single by date.
 */

public class Deadline extends Task {

    protected LocalDate by;
    private static final DateTimeFormatter OUT_FMT = DateTimeFormatter.ofPattern("d MMM uuuu");


    public Deadline(String description, String byStr) {
        super(description);
        assert !byStr.isBlank() : "Deadline description cannot be blank";
        this.by = parseDateFlexible(byStr);
    }

    @Override
    public String getType() {
        return "D";
    }

    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM d uuuu"));
    }

    public String getByIso() {
        return by.toString();
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + by.format(OUT_FMT) + ")";
    }

    private static LocalDate parseDateFlexible(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Date is null");
        }
        String t = s.trim();

        DateTimeFormatter[] fmts = new DateTimeFormatter[] {
                DateTimeFormatter.ISO_LOCAL_DATE,                  // 2019-12-02
                DateTimeFormatter.ofPattern("d/M/uuuu"),           // 2/12/2019
                DateTimeFormatter.ofPattern("d/M/uuuu HHmm"),      // 2/12/2019 1800
                DateTimeFormatter.ofPattern("d MMM uuuu"),         // 2 Dec 2019
                DateTimeFormatter.ofPattern("MMM d uuuu"),         // Dec 2 2019
                DateTimeFormatter.ofPattern("d MMM uuuu HHmm"),    // 2 Dec 2019 1800
                DateTimeFormatter.ofPattern("MMM d uuuu HHmm")     // Dec 2 2019 1800
        };

        String normalized = t.replace(",", "");

        for (DateTimeFormatter f : fmts) {
            try {
                if (f.toString().contains("H")) {
                    LocalDateTime dt = LocalDateTime.parse(normalized, f);
                    return dt.toLocalDate();
                }
                return LocalDate.parse(normalized, f);
            } catch (DateTimeParseException ignored) {}
            try {
                return LocalDate.parse(s, DateTimeFormatter.ofPattern("MMM d uuuu"));
            } catch (DateTimeParseException ignored) {}

        }

        throw new IllegalArgumentException("Unrecognized date: " + s);
    }

}
