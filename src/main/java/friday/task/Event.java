package friday.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String from;
    private String to;
    private String desc;

    // Constructor
    public Event(String desc) {
        super(Event.parseDesc(desc));
        this.desc = desc;
        String[] parts = desc.split("/from", 2);
        if (parts.length < 2) {
            throw new EventArgsException("Event description must include '/from' and '/to' parts.");
        }
        String description = parts[0].trim();
        String[] partsFromTo = parts[1].split("/to", 2);
        if (partsFromTo.length < 2) {
            throw new EventArgsException("Event description must include '/to' part.");
        }

        this.from = parseTime(partsFromTo[0].trim());
        this.to = parseTime(partsFromTo[1].trim());
    }

    @Override
    public String getFullDesc() {
        return this.desc;
    }

    @Override
    public String getTypeOfTask() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getTypeOfTask() + " " + getStatusIcon() + " " + getDesc() +
               " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     *
     * @param desc
     * @return String description without the '/from' and '/by' parts
     */
    public static String parseDesc(String desc) {
        String[] parts = desc.split("/from", 2);
        return parts[0].trim();
    }

    /**
     *
     * @param time
     * @return String formatted date if input is in YYYY-MM-DD format, else return input as is
     */
    public static String parseTime(String time) {
        try {
            LocalDate date = LocalDate.parse(time);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return time;
        }
    }
}
