package leo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task {
    protected LocalDate date;
    protected String stringDate;

    public Deadline(String description, String by) {
        super(description);
        this.stringDate = by;
        this.date = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }

    @Override
    public String toSaveFormat() {
        return String.join(" | ", "D",
                (isDone ? "1" : "0"), description,
                "by=" + this.stringDate);
    }

    @Override
    public boolean isUpcoming(LocalDate now, LocalDate max) {
        return !this.date.isBefore(now) && !this.date.isAfter(max); // deadline is before now or after max
    }
}
