package panda;

import java.util.ArrayList;
import java.util.List;

/**
 * PandaException handles all exceptions that arise from incorrect user input.
 */
public class PandaException extends Exception {
    private static final ArrayList<String> errorList = new ArrayList<>(List.of("list", "mark",
            "unmark", "delete", "find", "todo", "deadline", "event", "wrongDateTimeFormat",
            "MismatchedDateTime", "OverlappingEvents"));

    private static final String UNKNOWN_ACTION = "I don't know such an action..."
            + "\nType \"help\" if you are forgot the list of actions!";
    private static final String WRONG_DATETIME_FORMAT = "Please provide a valid date in the format yyyy-MM-dd HH:mm!";
    private static final String MISMATCHED_DATETIME = "Starting dateTime for event cannot be "
            + "equal to or later than ending DateTime!";
    private static final String OVERLAPPING_EVENTS = "Events cannot have overlapping timings!";
    private static final String DEADLINE_TASK_FORMAT = "Please provide the deadline as such: \n"
            + "deadline (insert description) /by (insert deadline dateTime)";
    private static final String EVENT_TASK_FORMAT = "Please provide the event as such: \n"
            + "event (insert description) /from (insert start dateTime) /to (insert end dateTime)";
    private static final String UNKNOWN_ERROR = "OH NO SOMETHING BAD HAS HAPPENED!!!";

    public PandaException(String action, String description) {
        super(createMessage(action, description));
    }

    private static String createMessage(String action, String description) {
        if (!errorList.contains(action)) {
            return UNKNOWN_ACTION;
        }
        if (action.equals("mark") || action.equals("unmark") || action.equals("delete")) {
            return action + " needs to be provided an integer as such: \n"
                    + action + " (insert list no. of task to " + action + ")";
        }
        if (action.equals("wrongDateTimeFormat")) {
            return WRONG_DATETIME_FORMAT;
        }
        if (action.equals("MismatchedDateTime")) {
            return MISMATCHED_DATETIME;
        }
        if (action.equals("OverlappingEvents")) {
            return OVERLAPPING_EVENTS;
        }
        if (description.isEmpty()) {
            return "The description of a " + action + " cannot be empty.";
        }
        if (action.equals("deadline")) {
            return DEADLINE_TASK_FORMAT;
        }
        if (action.equals("event")) {
            return EVENT_TASK_FORMAT;
        }
        return UNKNOWN_ERROR;
    }
}
