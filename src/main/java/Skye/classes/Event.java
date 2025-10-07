package Skye.classes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate start;
    protected LocalDate end;

    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        start = startDate;
        end = endDate;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)" , getTaskType(), super.toString(),
                start.format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT)),
                end.format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT)));
    }

    @Override
    public String getTaskData() {
        return String.format("%s|%s|%s", super.getTaskData(),
                start.format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT)),
                end.format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT)));
    }
}
