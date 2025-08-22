import org.w3c.dom.Text;

import javax.print.attribute.TextSyntax;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, boolean isMarked, LocalDateTime deadline) {
        super(description, isMarked);
        this.deadline = deadline;
    }

    public String getDeadline() {
        String output = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));

        return String.format("by: %s",
                output);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + this.getDeadline();
    }

    @Override
    public String getData() {
        String deadlineOutput = this.deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return String.format("D|%s|%s|%s",
                super.getDescription(), super.checked(), deadlineOutput);
    }

}
