package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ineffaexceptions.IneffaException;

/**
 * Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 */
public class Deadlines extends Task {
    private static final String commandCode = "D";
    private final LocalDate date;
    private final String time;

    /**
     * Initialises fields of class and parent class instances.
     */
    public Deadlines(String fullInfo, String description, LocalDate date, String time) {
        super(fullInfo, description, commandCode);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        // date printed out in yyyy-MM-dd format
        return "[" + commandCode + "]" + super.toString() + " (by: " + this.date + " " + this.time + ")";
    }

    /**
     * Creates an Deadlines Task
     * @param taskInfo Description of task
     * @return Deadlines Task
     */
    public static Deadlines createTask(String taskInfo) throws IneffaException {
        try {
            // Date format string is in dd-MM-yyyy
            // However task print out in yyyy-MM-dd
            String[] splittedString = splitString(taskInfo);
            String description = splittedString[0];
            String date = splittedString[1];
            String time = splittedString[2];

            LocalDate formatDate = changeDateFormat(date);
            return new Deadlines(taskInfo, description, formatDate, time);
        } catch (DateTimeParseException e) {
            throw new IneffaException("Invalid format for date and time.");
        }
    }

    private static String[] splitString(String taskInfo) throws IneffaException {
        String[] split = taskInfo.split("/by ", 2);
        if (split.length < 2 || split[0].isBlank() || split[1].isBlank()) {
            throw new IneffaException("Invalid format for deadline task.");
        }
        String description = split[0].trim();

        String dateAndTime = split[1].trim();
        String[] dateAndTimeSplit = dateAndTime.split(" ");
        if (dateAndTimeSplit.length < 2 || dateAndTimeSplit[0].isBlank() || dateAndTimeSplit[1].isBlank()) {
            throw new IneffaException("Invalid format for date and time.");
        }
        String date = dateAndTimeSplit[0];
        String time = dateAndTimeSplit[1].trim();
        return new String[]{description, date, time};
    }

    /**
     * Changes date to specific pattern below. ALso changes date from String to LocalDate
     * @param date Date
     * @return LocalDate in new format
     */
    private static LocalDate changeDateFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(
                date.replace("/", "-")
                        .trim(), formatter);

    }
}
