package dad;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {

    public LocalDate from;
    public LocalDate to;

    public Event(String task, String from, String to) {
        super(task);
        this.from = LocalDate.parse(from.strip());
        this.to = LocalDate.parse(to.strip());      
    }

    @Override
    public String toRecord() {
        return "E|" + super.toRecord() + "|" + this.from + "|" + this.to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " 
                + this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " | to: " 
                + this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}


