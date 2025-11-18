package LeeKuanYew.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private String startDate;
    private String endDate;

    public EventTask(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void updateDates(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startDate + "to: " + endDate + ")";
    }
}
