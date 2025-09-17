package Dan.Parser;

import Dan.Command.*;
import Dan.Task.Task;

import Dan.Task.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.IllegalArgumentException;

public class Parser {

    private static final String MARK_REGEX =  "^mark (-?\\d+)$";
    private static final String DELETE_REGEX =  "^delete (-?\\d+)$";
    private static final String TODO_REGEX = "^todo (.+)$";
    private static final String DEADLINE_REGEX = "^deadline (.+) /by (.+)$";
    private static final String EVENT_REGEX = "^event (.+) /from (.+) /to (.+)$";
    private static final String FIND_REGEX = "^find (.+)$";
    private static final String REMIND_REGEX = "^remind /within (-?\\d+)$";


    private static Task parseDataStringToTask(String dataString) throws IllegalArgumentException  {
        String[] dataStringComponents = dataString.split("\\|");
        String[] taskInfo = Arrays.stream(dataStringComponents).map(String::trim).toArray(String[]::new);
        return Task.createTask(taskInfo);
    }

    /**
     * Parses a list of data strings and converts them into Task objects.
     * Each string in the list represents a task in its stored format.
     *
     * @param stringDataList a list of strings representing tasks in storage format
     * @return an ArrayList of Task objects parsed from the input strings
     */
    public static ArrayList<Task> parseDataStringsToTasks(List<String> stringDataList) {
        ArrayList<Task> tasks = new ArrayList<>();

        for (int i = 0; i < stringDataList.size(); i++) {
            String dataString = stringDataList.get(i);
            Task task = parseDataStringToTask(dataString);
            tasks.add(task);
        }

        return tasks;
    }

    /**
     * Converts a Task object into its string representation for data storage.
     * The format varies depending on the task type (ToDo, Deadline, or Event).
     *
     * @param task the Task object to be converted to a data string
     * @return a string representation of the task suitable for storage
     */
    public static String parseTaskToDataString(Task task) {
        String data = "";

        switch (task.getTaskType()) {
        case TODO:
            ToDo todo = (ToDo) task;
            data += "T | ";
            data += todo.isDone().toString() + " | ";
            data += todo.getDescription().trim();
            break;
        case DEADLINE:
            Deadline deadline = (Deadline) task;
            data += "D | ";
            data += deadline.isDone().toString() + " | ";
            data += deadline.getDescription().trim() + " | ";
            data += deadline.getDeadlineDate();
            break;
        case EVENT:
            Event event = (Event) task;
            data += "E | ";
            data += event.isDone().toString() + " | ";
            data += event.getDescription().trim() + " | ";
            data += event.getFromDate() + " | ";
            data += event.getToDate();
            break;
        }

        return data;
    }

    /**
     * Converts a list of Task objects into a multi-line string suitable for file storage.
     * Each task is converted to its data string format and separated by line separators.
     *
     * @param tasks an ArrayList of Task objects to be converted
     * @return a multi-line string representation of all tasks for storage
     */
    public static String parseTasksToDataString(ArrayList<Task> tasks) {
        String dataString = "";

        for (int i = 0; i < tasks.size(); i++) {
            String taskDataString = parseTaskToDataString(tasks.get(i));
            dataString = dataString + taskDataString + System.lineSeparator();
        }

        return dataString;
    }

    /**
     * Parses user input string and returns the appropriate Command object.
     * Recognizes commands like "list", "mark", "delete", "todo", "deadline", "event", and "bye".
     * Uses regex patterns to validate and extract information from command strings.
     *
     * @param input the user input string to be parsed
     * @return a Command object corresponding to the parsed user input
     * @throws IllegalArgumentException if the input string does not match any valid command format
     */
    public static Command parseUserInput(String input) throws IllegalArgumentException {
        String str = input.trim();
        if (str.equals("list")) {
            return new ListCommand();
        } else if (str.matches(MARK_REGEX)) {
            return parseMark(str);
        } else if (str.matches(DELETE_REGEX)) {
           return parseDelete(str);
        } else if (str.matches(TODO_REGEX)) {
           return parseToDo(str);
        } else if (str.matches(EVENT_REGEX)) {
            return parseEvent(str);
        } else if (str.matches(DEADLINE_REGEX)) {
            return parseDeadline(str);
        } else if (str.matches(FIND_REGEX)) {
            return parseFind(str);
        } else if (str.matches(REMIND_REGEX)) {
            return parseRemind(str);
        } else if (str.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static Command parseMark(String str) {
        String[] temp = str.split(" ");
        int index = Integer.parseInt(temp[1]);
        assert index > 0;
        return new MarkCommand(index);
    }

    private static Command parseDelete(String str) {
        String indexStr = str.replaceFirst("delete ", "");
        int index = Integer.parseInt(indexStr);
        return new DeleteCommand(index);
    }

    private static Command parseToDo(String str) {
        String desc = str.replaceFirst("todo", "");
        String[] taskInfo = {"T", "false", desc};
        Task toDO = Task.createTask(taskInfo);
        return new ToDoCommand(toDO);
    }

    private static Command parseEvent(String str) {
        String withoutEvent = str.replaceFirst("event", "");
        String[] fromSplit = withoutEvent.split("/from", 2);

        if (fromSplit.length != 2) {
            throw new IllegalArgumentException();
        }

        String desc = fromSplit[0];
        String duration = fromSplit[1];
        String[] times = duration.split("/to", 2);

        if (times.length != 2) {
            throw new IllegalArgumentException();
        }

        String from = times[0];
        String to = times[1];

        String[] taskInfo = {"E", "false", desc, from, to};
        Task event = Task.createTask(taskInfo);
        if (event != null) {
            return new EventCommand(event);
        } else {
            String errorMessage = "Please enter in a valid date format: \nd/M/yyyy\ndd/MM/yyyy\nyyyy-MM-dd\nyyyy/MM/dd\nMMM dd yyyy";
            return new ErrorCommand(errorMessage);
        }
    }

    private static Command parseDeadline(String str) {
        String withoutDeadline = str.replaceFirst("deadline", "");
        String[] bySplit = withoutDeadline.split("/by", 2);

        if (bySplit.length != 2) {
            throw new IllegalArgumentException();
        }

        String desc = bySplit[0];
        String by = bySplit[1];

        String[] taskInfo = {"D", "false", desc, by};
        Task deadline = Task.createTask(taskInfo);

        if (deadline != null) {
            return new DeadlineCommand(deadline);
        } else {
            String errorMessage = "Please enter in a valid date format: \nd/M/yyyy\ndd/MM/yyyy\nyyyy-MM-dd\nyyyy/MM/dd\nMMM dd yyyy";
            return new ErrorCommand(errorMessage);
        }
    }

    private static Command parseFind(String str) {
        String searchStr = str.replaceFirst("find", "");
        String cleanStr = searchStr.trim();
        return new FindCommand(cleanStr);
    }

    private static Command parseRemind(String str) {
        String daysFromNowStr = str
                .replaceFirst("remind", "")
                .replaceFirst("/within", "")
                .trim();

        int daysFromNow = Integer.valueOf(daysFromNowStr);

        return new RemindCommand(daysFromNow);
    }
}
