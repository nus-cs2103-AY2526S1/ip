package olaf;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import olaf.tasks.Deadline;
import olaf.tasks.Event;
import olaf.tasks.TaskList;
import olaf.tasks.ToDo;

/**
 * Parses user input commands and modifies the task list.
 * Returns strings describing results instead of printing.
 */
public class Parser {

    /**
     * Parses the given user input, executes commands on the provided task list,
     * and returns a String describing the result.
     *
     * @param input The command entered by the user.
     * @param taskList The TaskList object.
     * @return String result of executing the command.
     * @throws OlafException if input command is invalid or in the wrong format.
     */
    public static String parse(String input, TaskList taskList) throws OlafException {
        input = input.trim();

        if (input.equals("list")) {
            return handleList(taskList);
        } else if (input.equals("bye")) {
            return handleBye();
        } else if (input.startsWith("mark")) {
            return handleMark(input, taskList);
        } else if (input.startsWith("unmark")) {
            return handleUnmark(input, taskList);
        } else if (input.startsWith("todo")) {
            return handleTodo(input, taskList);
        } else if (input.startsWith("deadline")) {
            return handleDeadline(input, taskList);
        } else if (input.startsWith("event")) {
            return handleEvent(input, taskList);
        } else if (input.startsWith("delete")) {
            return handleDelete(input, taskList);
        } else if (input.startsWith("find")) {
            return handleFind(input, taskList);
        } else {
            throw new OlafException("OOPS!!! I'm sorry, but I don't know that command :(.");
        }
    }

    /**
     * Handles "handleList" command to mark a task as done.
     *
     * @param taskList The TaskList object.
     * @return String result of executing the list command.
     */
    private static String handleList(TaskList taskList) {
        assert taskList != null : "TaskList should not be null";
        return taskList.listTasks();
    }

    /**
     * Handles "bye" command to mark a task as done.
     *
     * @return String result of executing the mark command.
     */
    private static String handleBye() {
        return "Goodbye! Hope to see you again soon!";
    }

    /**
     * Parses and validates a task number string.
     *
     * @param str The task number in string.
     * @param taskList The taskList object.
     * @return Integer result of the task number in order
     * @throws OlafException if task number is not a valid integer or not within the range
     */
    private static int parseTaskNumber(String str, TaskList taskList) throws OlafException {
        try {
            int num = Integer.parseInt(str);
            if (num < 1 || num > taskList.getCount()) {
                throw new OlafException("OOPS!!! Task number must be between 1 and " + taskList.getCount() + ".");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new OlafException("OOPS!!! Task commands require a valid integer.");
        }
    }

    /**
     * Handles "mark" command to mark a task as done.
     *
     * @param input The mark command entered by the user.
     * @param taskList The TaskList object.
     * @return String result of executing the mark command.
     * @throws OlafException if input command is invalid or in the wrong format.
     */
    private static String handleMark(String input, TaskList taskList) throws OlafException {
        assert input != null : "Input to handleMark should not be null";
        assert taskList != null : "TaskList should not be null";
        if (taskList.getCount() == 0) {
            throw new OlafException("OOPS!!! There are no tasks to mark.");
        }
        String taskStrPos = input.substring(4).trim();
        int taskNum = parseTaskNumber(taskStrPos, taskList);
        return taskList.markTask(taskNum);
    }

    /**
     * Handles "unmark" command to mark a task as done.
     *
     * @param input The unmark command entered by the user.
     * @param taskList The TaskList object.
     * @return String result of executing the unmark command.
     * @throws OlafException if input command is invalid or in the wrong format.
     */
    private static String handleUnmark(String input, TaskList taskList) throws OlafException {
        assert input != null : "Input to handleUnmark should not be null";
        assert taskList != null : "TaskList should not be null";
        if (taskList.getCount() == 0) {
            throw new OlafException("OOPS!!! There are no tasks to unmark.");
        }
        String taskStrPos = input.substring(6).trim();
        int taskNum = parseTaskNumber(taskStrPos, taskList);
        return taskList.unmarkTask(taskNum);
    }

    /**
     * Handles "todo" command to mark a task as done.
     *
     * @param input The todo command entered by the user.
     * @param taskList The TaskList object.
     * @return String result of executing the todo command.
     * @throws OlafException if input command is invalid or in the wrong format.
     */
    private static String handleTodo(String input, TaskList taskList) throws OlafException {
        assert input != null : "Input to handleTodo should not be null";
        assert taskList != null : "TaskList should not be null";
        String desc = input.length() > 4 ? input.substring(5).trim() : "";
        if (desc.isEmpty()) {
            throw new OlafException("OOPS!!! The description of a todo task cannot be empty.");
        }
        return taskList.addTask(new ToDo(desc));
    }

    /**
     * Handles "deadline" command to mark a task as done.
     *
     * @param input The deadline command entered by the user.
     * @param taskList The TaskList object.
     * @return String result of executing the deadline command.
     * @throws OlafException if input command is invalid or in the wrong format.
     */
    private static String handleDeadline(String input, TaskList taskList) throws OlafException {
        assert input != null : "Input to handleDeadline should not be null";
        assert taskList != null : "TaskList should not be null";
        String details = input.length() > 8 ? input.substring(9) : "";
        String[] parts = details.split(" /by ");
        String desc = parts[0].trim();
        if (desc.isEmpty()) {
            throw new OlafException("OOPS!!! The description of a deadline task cannot be empty.");
        }
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new OlafException("OOPS!!! The deadline task requires both a description and a deadline.");
        }
        String deadlineStr = parts[1].trim();
        try {
            LocalDateTime ldt = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return taskList.addTask(new Deadline(desc, ldt));
        } catch (DateTimeParseException e) {
            throw new OlafException("OOPS!!! Please enter the date in this format: d/M/yyyy HHmm (e.g. 2/12/2025 1800).");
        }
    }

    /**
     * Handles "event" command to mark a task as done.
     *
     * @param input The event command entered by the user.
     * @param taskList The TaskList object.
     * @return String result of executing the event command.
     * @throws OlafException if input command is invalid or in the wrong format.
     */
    private static String handleEvent(String input, TaskList taskList) throws OlafException {
        assert input != null : "Input to handleEvent should not be null";
        assert taskList != null : "TaskList should not be null";
        String details = input.length() > 5 ? input.substring(6) : "";
        int fromIndex = details.indexOf("/from ");
        int toIndex = details.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1) {
            throw new OlafException("OOPS!!! An event needs both a start time(/from) and an end time(/to).");
        }
        String desc = details.substring(0, fromIndex).trim();
        String fromStr = details.substring(fromIndex + 6, toIndex).trim();
        String toStr = details.substring(toIndex + 5).trim();
        if (desc.isEmpty() || fromStr.isEmpty() || toStr.isEmpty()) {
            throw new OlafException("OOPS!!! Event description, start or end time cannot be empty.");
        }
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime from = LocalDateTime.parse(fromStr, dtf);
            LocalDateTime to = LocalDateTime.parse(toStr, dtf);
            return taskList.addTask(new Event(desc, from, to));
        } catch (DateTimeParseException e) {
            throw new OlafException("OOPS!!! Please enter both 'from' and 'to' in this format: d/M/yyyy HHmm (eg. /from 2/12/2025 1800 /to 2/12/2025 2000).");
        }
    }

    /**
     * Handles "delete" command to mark a task as done.
     *
     * @param input The delete command entered by the user.
     * @param taskList The TaskList object.
     * @return String result of executing the delete command.
     * @throws OlafException if input command is invalid or in the wrong format.
     */
    private static String handleDelete(String input, TaskList taskList) throws OlafException {
        assert input != null : "Input to handleDelete should not be null";
        assert taskList != null : "TaskList should not be null";
        if (taskList.getCount() == 0) {
            throw new OlafException("OOPS!!! There are no tasks to delete.");
        }
        String taskStrPos = input.length() > 6 ? input.substring(6).trim() : "";
        int taskNum = parseTaskNumber(taskStrPos, taskList);
        return taskList.deleteTask(taskNum);
    }

    /**
     * Handles "find" command to mark a task as done.
     *
     * @param input The find command entered by the user.
     * @param taskList The TaskList object.
     * @return String result of executing the find command.
     * @throws OlafException if input command is invalid or in the wrong format.
     */
    private static String handleFind(String input, TaskList taskList) throws OlafException {
        assert input != null : "Input to handleFind should not be null";
        assert taskList != null : "TaskList should not be null";
        if (taskList.getCount() == 0) {
            throw new OlafException("OOPS!!! There are no tasks to find.");
        }
        String keyword = input.length() > 4 ? input.substring(5).trim() : "";
        if (keyword.isEmpty()) {
            throw new OlafException("OOPS!!! The description of a find cannot be empty.");
        }
        return taskList.findTask(keyword);
    }
}
