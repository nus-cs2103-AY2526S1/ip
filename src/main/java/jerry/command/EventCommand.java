package jerry.command;

import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;
import jerry.storage.Storage;
import jerry.task.Event;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * A command to add an event into the task list.
 * It checks the user input format to ensure that it has a description
 * and that the start and end date-times are correctly specified using
 * the '/from' and 'to' keywords.
 * The event is added to the task list, saved to storage and a display will be shown
 * as a confirmation to the user that the task is successfully saved.
 */
public class EventCommand extends Command {

    private static final int PREFIX_LENGTH = 5;
    private final String description;
    private final String fromDate;
    private final String fromTime;
    private final String toDate;
    private final String toTime;

    /**
     * The input must include an 'event' command keyword, description, followed by '/from' keyword
     * with start date and time, and the 'to' keyword with the end date and time.
     * If the format is invalid, an exception is thrown.
     *
     * @param desc user input string to be parsed.
     * @throws InvalidCommandFormatException if the user input format is incorrect.
     */
    public EventCommand(String input) throws InvalidCommandFormatException {
        String[] stringArray = getStrings(input);
        this.description = stringArray[0].trim();
        String[] dateTimeArray = stringArray[1].trim().replaceFirst("/?to", "to").split("to");
        dateTimeFormatChecker(dateTimeArray);

        String[] fromDateTime = dateTimeArray[0].trim().split(" ");
        String[] toDateTime = dateTimeArray[1].trim().split(" ");
        assert !fromDateTime[0].isEmpty() && !fromDateTime[1].isEmpty() : "Start date and time should not be empty";
        assert !toDateTime[0].isEmpty() && !toDateTime[1].isEmpty() : "To date and time should not be empty";

        this.fromDate = fromDateTime[0].trim();
        this.fromTime = fromDateTime[1].trim();
        this.toDate = toDateTime[0].trim();
        this.toTime = toDateTime[1].trim();
    }

    /**
     * Checks that the date and time strings contain exactly a date and a time.
     */
    public static void dateTimeFormatChecker(String[] dateTimeArray)
            throws InvalidCommandFormatException {
        if (dateTimeArray.length != 2 || dateTimeArray[0].trim().split(" ").length != 2
            || dateTimeArray[1].trim().split(" ").length != 2) {
            throw new InvalidCommandFormatException("Invalid format! Expected format:\n" 
            + "/from YYYY-MM-dd HH:mm to YYYY-MM-dd HH:mm\n"
            + "Note that 'to' does not require slash '/'");
        }
    }

    /**
     * Parses the input string into event description and date-time portion.
     * It ensures that the description is not empty, and that both '/from' and
     * 'to' keywords are present in the input.
     *
     * @param desc the full user input string.
     * @return an array with the description at index 0 and date-time string at index 1.
     * @throws InvalidCommandFormatException if the description is empty or required keywords are missing.
     */
    private static String[] getStrings(String input) throws InvalidCommandFormatException {
        String trimmed = input.trim();
        if (trimmed.toLowerCase().startsWith("event")) {
            trimmed = trimmed.substring(PREFIX_LENGTH).trim();
        }
        if (trimmed.isEmpty() || trimmed.startsWith("/from")) {
            throw new InvalidCommandFormatException("Event description cannot be empty...");
        }
        String[] strArray = trimmed.split("/from");
        if (strArray.length < 2) {
            String errorMessage = "Event must have '/from' and 'to' keyword!\n" 
            + "Note that 'to' does not require slash '/'";
            throw new InvalidCommandFormatException(errorMessage);
        }
        return strArray;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JerryException {
        Event event = new Event(this.description, fromDate, fromTime, toDate, toTime);
        this.response = taskList.addTask(event);
        taskList.saveTasks(storage);
        ui.displayOutput(this.response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return this.response;
    }

}
