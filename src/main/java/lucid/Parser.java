package lucid;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class with static fields and methods to handle the retrieval and interpretation of user inputs
 * Contains a TaskList to store information on existing tasks
 */
public class Parser {
    private static final String DATE_FORMAT = "\\d{4}-\\d{2}-\\d{2}";
    private static final String DATETIME_FORMAT = "\\d{4}-\\d{2}-\\d{2}-\\d{4}";
    private static final String FLAG_BY = "/by";
    private static final String FLAG_FROM = "/from";
    private static final String FLAG_TO = "/to";
    private static final Character SPACE_CHAR = ' ';
    private static TaskList taskList = new TaskList();

    /**
     * Retrieves user input and processes it, calling appropriate function to handle it
     */
    public static String parse(String userInput) {
        try {
            if (userInput.contains("|")) {
                return Ui.invalidCharacterDetectedMessage();
            }
            String firstWord = userInput.split(" ")[0];

            if (userInput.equals("bye")) {
                return Ui.farewell();
            } else if (userInput.equals("list")) {
                return taskList.printTasks();
            } else if (userInput.equals("help")) {
                return Ui.printHelpSheet();
            } else if (firstWord.equals("mark")) {
                return handleMarkCommand(userInput);
            } else if (firstWord.equals("unmark")) {
                return handleUnmarkCommand(userInput);
            } else if (firstWord.equals("find")) {
                return handleFindCommand(userInput);
            } else if (firstWord.equals("todo")) {
                return handleToDoCommand(userInput);
            } else if (firstWord.equals("deadline")) {
                return handleDeadlineCommand(userInput);
            } else if (firstWord.equals("event")) {
                return handleEventCommand(userInput);
            } else if (firstWord.equals("delete")) {
                return handleDeleteCommand(userInput);
            } else {
                throw new UnknownCommandException();
            }
        } catch (LucidException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Returns a String array containing separated date and time string representations of the input string
     * If input string has no time information included, index 1 of returned array is null
     *
     * @param dateTimeString String containing date and time information retrieved from data file
     *                       Has form yyyy-mm-dd (or yyyy-mm-dd-xxxx to include time)
     * @return String array where index 0 contains date string, index 1 contains time string if any
     * @throws DateTimeParseException if input string does not adhere to expected format
     */
    public static String[] parseDateTimeString(String dateTimeString) throws DateTimeParseException {
        String[] dateAndTime;
        if (dateTimeString.matches(DATE_FORMAT)) {
            dateAndTime = new String[]{dateTimeString, null};
        } else if (dateTimeString.matches(DATETIME_FORMAT)) {
            dateAndTime = new String[]{dateTimeString.substring(0, 10), dateTimeString.substring(11)};
        } else {
            throw new DateTimeParseException();
        }
        return dateAndTime;
    }

    /**
     * Marks appropriate indexed task as complete
     *
     * @param userInput contains keyword "mark" and index of task to mark as complete
     */
    public static String handleMarkCommand(String userInput) throws MarkUsageException {
        int firstSpaceIndex = userInput.indexOf(SPACE_CHAR);
        String remainingInput = userInput.substring(firstSpaceIndex + 1).trim();
        verifyMarkInput(remainingInput);
        return taskList.markTaskAsComplete(Integer.parseInt(remainingInput));
    }

    /**
     * Verifies validity of user input for mark command
     * @param remainingInput user input substring after mark
     * @throws MarkUsageException if the input is invalid
     */
    private static void verifyMarkInput(String remainingInput) throws MarkUsageException {
        if (remainingInput.equals("mark") || remainingInput.isEmpty()) {
            throw new MarkUsageException();
        }
        try {
            Integer.parseInt(remainingInput);
        } catch (NumberFormatException e) {
            throw new MarkUsageException();
        }
    }

    /**
     * Marks appropriate indexed task as not complete
     *
     * @param userInput contains keyword "unmark" and index of task to mark as complete
     */
    public static String handleUnmarkCommand(String userInput) throws UnmarkUsageException {
        int firstSpaceIndex = userInput.indexOf(SPACE_CHAR);
        String remainingInput = userInput.substring(firstSpaceIndex + 1).trim();
        verifyUnmarkInput(remainingInput);
        return taskList.markTaskAsNotComplete(Integer.parseInt(remainingInput));
    }

    /**
     * Verifies validity of user input for unmark command
     * @param remainingInput user input substring after unmark
     * @throws UnmarkUsageException if the input is invalid
     */
    private static void verifyUnmarkInput(String remainingInput) throws UnmarkUsageException {
        if (remainingInput.equals("unmark") || remainingInput.isEmpty()) {
            throw new UnmarkUsageException();
        }
        try {
            Integer.parseInt(remainingInput);
        } catch (NumberFormatException e) {
            throw new UnmarkUsageException();
        }
    }

    /**
     * Adds a todo to list of tasks
     *
     * @param userInput String containing command and name of task
     * @throws ToDoEmptyException Exception resulting from incorrect string format
     */
    public static String handleToDoCommand(String userInput) throws ToDoEmptyException {
        int firstSpaceIndex = userInput.indexOf(SPACE_CHAR);
        String remainingInput = userInput.substring(firstSpaceIndex + 1).trim();
        if (remainingInput.equals("todo") || remainingInput.isEmpty()) {
            throw new ToDoEmptyException();
        }
        return taskList.addTask(new ToDo(remainingInput));
    }

    /**
     * Adds a deadline to list of tasks
     * @param userInput String containing command and information of deadline task
     * @throws DeadlineUsageException Exception resulting from incorrect string format
     */
    public static String handleDeadlineCommand(String userInput) throws DeadlineUsageException,
            InvalidDateTimeException {
        String[] args = getDeadlineArgs(userInput);
        String name = args[0];
        String due = args[1];
        if (due.matches(DATE_FORMAT)) {
            return taskList.addTask(new Deadline(name, due));
        } else if (due.matches(DATETIME_FORMAT)) {
            return taskList.addTask((new Deadline(name, due.substring(0, 10), due.substring(11))));
        } else {
            throw new DeadlineUsageException();
        }
    }

    /**
     * Verifies and extracts arguments from userInput for the handleDeadlineCommand function
     * @param userInput String containing command and information of deadline task
     * @return String array with first element name, second element due
     * @throws DeadlineUsageException
     */
    private static String[] getDeadlineArgs(String userInput) throws DeadlineUsageException, InvalidDateTimeException {
        if (!userInput.contains(FLAG_BY)) {
            throw new DeadlineUsageException();
        }
        int firstSpaceIndex = userInput.indexOf(SPACE_CHAR);
        String remainingInput = userInput.substring(firstSpaceIndex + 1);
        String[] args = remainingInput.split(FLAG_BY);

        if (args.length == 0 || args.length == 1) {
            throw new DeadlineUsageException();
        }
        String name = args[0].trim();
        String due = args[1].trim();
        if (name.isEmpty() || !isCorrectDateTimeFormat(due)) {
            throw new DeadlineUsageException();
        }
        checkDateTimeValidity(due);
        return new String[] {name, due};
    }
    /**
     * Adds an event to list of tasks
     * @param userInput String containing command and information of event task
     * @throws EventUsageException Exception resulting from incorrect string format
     * @throws InvalidDateTimeException if valid string format, but invalid date detected
     */
    public static String handleEventCommand(String userInput) throws EventUsageException, InvalidDateTimeException {
        String[] args = getEventArgs(userInput);
        String name = args[0];
        String start = args[1];
        String end = args[2];
        checkDateTimeValidity(start);
        checkDateTimeValidity(end);
        return taskList.addTask((new Event(name, start, end)));
    }

    /**
     * Verifies and extracts arguments from userInput for the handleEventCommand function
     * @param userInput String containing command and information of event task
     * @return String array with first element name, second element start, third element end
     * @throws EventUsageException if any invalid name or non-matching string format detected
     */
    private static String[] getEventArgs(String userInput) throws EventUsageException {
        if (!userInput.contains(FLAG_FROM) || !userInput.contains(FLAG_TO)) {
            throw new EventUsageException();
        }
        int firstSpaceIndex = userInput.indexOf(SPACE_CHAR);
        String remainingInput = userInput.substring(firstSpaceIndex + 1);

        String[] args = remainingInput.split(FLAG_FROM);
        if (args.length < 2) {
            throw new EventUsageException();
        }

        String name = args[0].trim();
        String[] times = args[1].split(FLAG_TO);
        if (times.length < 2) {
            throw new EventUsageException();
        }

        String start = times[0].trim();
        String end = times[1].trim();
        if ((name.isEmpty()) || !isCorrectDateTimeFormat(start) || !isCorrectDateTimeFormat(end)) {
            throw new EventUsageException();
        }
        return new String[] {name, start, end};
    }
    /**
     * Deletes a task from the list of tasks
     *
     * @param userInput index of task (according to list) to delete
     * @throws DeleteUsageException Exception resulting from incorrect usage
     */
    public static String handleDeleteCommand(String userInput) throws DeleteUsageException {
        int firstSpaceIndex = userInput.indexOf(SPACE_CHAR);
        String remainingInput = userInput.substring(firstSpaceIndex + 1);
        verifyDeleteInput(remainingInput);
        return taskList.deleteTask(Integer.parseInt(remainingInput));
    }

    /**
     * Verifies validity of user input for delete command
     * @param remainingInput user input substring after delete
     * @throws DeleteUsageException if the input is invalid
     */
    private static void verifyDeleteInput(String remainingInput) throws DeleteUsageException {
        if (remainingInput.equals("delete") || remainingInput.isEmpty()) {
            throw new DeleteUsageException();
        }
        try {
            Integer.parseInt(remainingInput);
        } catch (NumberFormatException e) {
            throw new DeleteUsageException();
        }
    }

    /**
     * Searches and prints tasks that suit the user input
     *
     * @param userInput String containing command and name substring to search for
     * @throws FindUsageException
     */
    public static String handleFindCommand(String userInput) throws FindUsageException {
        int firstSpaceIndex = userInput.indexOf(SPACE_CHAR);
        String remainingInput = userInput.substring(firstSpaceIndex + 1).trim();
        if (remainingInput.equals("find") || remainingInput.isEmpty()) {
            throw new FindUsageException();
        }
        return taskList.findAndPrintTasks(remainingInput.trim());
    }

    /**
     * Checks if a string is appropriately formatted for event and deadline creation
     * @param s String containing date and time information
     * @return true if proper format, false otherwise
     */
    public static boolean isCorrectDateTimeFormat(String s) {
        if (s.matches(DATE_FORMAT) || (s.matches(DATETIME_FORMAT))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a dateTime string represents a valid date and time
     * @param s dateTime string
     * @throws InvalidDateTimeException if string does not represent a valid date and time
     */
    private static void checkDateTimeValidity(String s) throws InvalidDateTimeException {
        try {
            if (s.matches(DATE_FORMAT)) {
                LocalDate.parse(s);
            } else {
                LocalDate.parse(s.substring(0, 10));
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
                LocalTime.parse(s.substring(11), timeFormatter);
            }
        } catch (java.time.format.DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }
}
