package task;

import ineffaexceptions.IneffaException;

/**
 * Events: tasks that start at a specific date/time and ends at a specific date/time.
 */
public class Events extends Task {
    private static final String commandCode = "E";
    private final String fromDate;
    private final String toDate;

    /**
     * Initialises fields of class and parent class instances.
     */
    public Events(String fullInfo, String description, String fromDate, String toDate) {
        super(fullInfo, description, commandCode);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[" + commandCode + "]" + super.toString() + "(from: " + this.fromDate + " to: " + this.toDate + ")";
    }

    /**
     * Creates an Events Task
     * @param taskInfo Description of task
     * @return Events Task
     */
    public static Events createTask(String taskInfo) throws IneffaException {
        String[] splittedString = splitString(taskInfo);
        String description = splittedString[0];
        String fromDate = splittedString[1].trim();
        String toDate = splittedString[2].trim();
        return new Events(taskInfo, description, fromDate, toDate);
    }

    private static String[] splitString(String taskInfo) throws IneffaException {
        String[] s = taskInfo.split("/from ", 2);
        if (s.length < 2 || s[0].isBlank() || s[1].isBlank()) {
            throw new IneffaException("Invalid format for event task.");
        }
        String description = s[0];
        String[] dates = s[1].split("/to", 2);
        if (dates.length < 2 || dates[0].isBlank() || dates[1].isBlank()) {
            throw new IneffaException("Invalid format for start and end date/timings.");
        }
        return new String[]{description, dates[0].trim(), dates[1].trim()};
    }
}
