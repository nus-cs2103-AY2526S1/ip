package johnny.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PeriodTask extends Task {
    protected LocalDate start;
    protected LocalDate end;
    protected String formattedStart;
    protected String formattedEnd;
    protected static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Creates a new PeriodTask with the given name, start and end date.
     * 
     * @param name
     * @param start
     * @param end
     */
    public PeriodTask(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
        this.formattedStart = start.format(dateFormatter);
        this.formattedEnd = end.format(dateFormatter);
    }

    /**
     * Creates a new PeriodTask with the given boolean on whether it is done.
     * 
     * @param name
     * @param isCompleted
     * @param start
     * @param end
     */
    public PeriodTask(String name, boolean isCompleted, LocalDate start, LocalDate end) {
        super(name, isCompleted);
        this.start = start;
        this.end = end;
        this.formattedStart = start.format(dateFormatter);
        this.formattedEnd = end.format(dateFormatter);
    }

    public LocalDate getStart() {
        return this.start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    @Override
    public String getStoredString() {
        if (isCompleted) {
            return "P|1|" + this.name + "|" + this.formattedStart + "|" + this.formattedEnd;
        }
        return "P|0|" + this.name + "|" + this.formattedStart + "|" + this.formattedEnd;
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[P][X] " + super.name + " (between: " + this.formattedStart + " and " + formattedEnd;
        } else {
            return "[P][ ] " + super.name + " (between: " + this.formattedStart + " and " + formattedEnd;
        }
    }
}
