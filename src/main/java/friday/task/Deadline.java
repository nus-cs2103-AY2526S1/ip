package friday.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final String by;
    private final String desc;
    public Deadline(String desc) {
        super(parseDesc(desc));
        this.desc = desc;
        String[] parts= desc.split("/by", 2);
        if (parts.length < 2) {
            throw new DeadlineArgsException("The deadline cannot be empty... it must have the 'by' part.");
        }
        this.by = parseTime(parts[1].trim());

    }

    @Override
    public String getFullDesc() {
        return this.desc;
    }

    @Override
    public String getTypeOfTask() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getTypeOfTask() + " " + getStatusIcon() + " " + getDesc() + " (by: " + this.by + ")";
    }

    /**
     *
     * @param  desc
     * @return String description without the '/by' part
     */
    public static String parseDesc(String desc) {
        String[] parts = desc.split("/by", 2);
        return parts[0].trim();
    }

    /**
     *
     * @param by
     * @return String formatted date if input is in YYYY-MM-DD format, else return input as is
     */
    public static String parseTime(String by) {
        try {
            LocalDate date = LocalDate.parse(by);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return by;
        }
    }
}
