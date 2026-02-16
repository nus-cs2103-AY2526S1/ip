package taro.corecomp;

import java.time.LocalDate;
import java.time.LocalTime;

import taro.command.ByeCommand;
import taro.command.Command;
import taro.command.DeadlineCommand;
import taro.command.DeleteCommand;
import taro.command.EventCommand;
import taro.command.FindCommand;
import taro.command.ListCommand;
import taro.command.MarkCommand;
import taro.command.ReminderCommand;
import taro.command.TodoCommand;
import taro.command.UnmarkCommand;
import taro.task.Deadline;
import taro.task.Event;
import taro.task.Task;
import taro.task.Todo;


/**
 * Parses user input and converts it into executable {@link Command} objects.
 */
public class Parser {
    /**
     * Parses a line of text from the storage file and reconstructs the corresponding {@code Task} object.
     * The input line must follow the format used when saving tasks to file, i.e. fields separated by {@code " | "}.
     * @param line the raw text line from the data file
     * @return the corresponding {@link Todo}, {@link Deadline}, or {@link Event} object;
     *         {@code null} if the task type is unrecognized
     * @throws java.time.format.DateTimeParseException if the date/time fields cannot be parsed
     * @throws ArrayIndexOutOfBoundsException if the input line does not contain the expected number of fields
     */
    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isCurrentTaskDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
        case "T":
            return new Todo(description, isCurrentTaskDone);
        case "D":
            LocalDate byTime = LocalDate.parse(parts[3]);
            return new Deadline(description, byTime, isCurrentTaskDone);
        case "E":
            LocalDate date = LocalDate.parse(parts[3]); // yyyy-MM-dd
            String[] period = parts[4].split("-");
            LocalTime start = LocalTime.parse(period[0]);
            LocalTime end = LocalTime.parse(period[1]);
            return new Event(description, date, start, end, isCurrentTaskDone);
        default:
            return null;
        }

    }
    /**
     * Parses a user input string into a corresponding {@link Command} object that can be executed
     * on a {@link TaskList}.
     *
     * Supported commands and their formats:
     *   {@code bye} &mdash; exits the program
     *   {@code list} &mdash; lists all tasks
     *   {@code mark <index>} &mdash; marks the task at the given index as done
     *   {@code unmark <index>} &mdash; marks the task at the given index as not done
     *   {@code delete <index>} &mdash; deletes the task at the given index
     *   {@code todo <description>} &mdash; adds a new {@code Todo}</li>
     *   {@code deadline <description> /by <yyyy-mm-dd>} &mdash; adds a new {@code Deadline}
     *   {@code event <description> /from <yyyy-mm-dd HH:mm> /to <HH:mm>} &mdash; adds a new {@code Event}
     *   {@code find <keyword>} &mdash; searches tasks by keyword in their string representation
     *   {@code reminder <keyword>} &mdash; searches tasks due in 3 days
     *
     *
     * @param input the raw user input string
     * @return a {@link Command} object encapsulating the action to be executed
     * @throws TaroException if the input is invalid, missing arguments, or the command type is unknown
     */
    public static Command parseCommand(String input) throws TaroException {
        input = input.trim();

        try {
            if (input.equals("bye")) {
                return new ByeCommand();
            } else if (input.equals("list")) {
                return new ListCommand();
            } else if (input.startsWith("mark")) {
                return new MarkCommand(input);
            } else if (input.startsWith("unmark")) {
                return new UnmarkCommand(input);
            } else if (input.startsWith("delete")) {
                return new DeleteCommand(input);
            } else if (input.startsWith("todo")) {
                return new TodoCommand(input);
            } else if (input.startsWith("deadline")) {
                return new DeadlineCommand(input);
            } else if (input.startsWith("event")) {
                return new EventCommand(input);
            } else if (input.startsWith("find ")) {
                return new FindCommand(input);
            } else if (input.startsWith("reminder")) {
                return new ReminderCommand();
            } else {
                throw new TaroException("Opps! Unknown command: " + input + "\nPlease retry!");
            }
        } catch (TaroException e) {
            throw e;
        }
    }
}
