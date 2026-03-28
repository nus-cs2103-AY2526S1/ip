package dad;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Deadline extends Task {

    public LocalDate by;

    public Deadline(String task, String by) {
        super(task);
        this.by = LocalDate.parse(by.strip());
    }

    @Override
    public String toRecord() {
        return "D|" + super.toRecord() + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " 
                + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
