package eureka;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eureka.command.Command;
import eureka.command.DeadlineCommand;
import eureka.command.DeleteCommand;
import eureka.command.EventCommand;
import eureka.command.ExitCommand;
import eureka.command.FindCommand;
import eureka.command.InvalidCommand;
import eureka.command.ListCommand;
import eureka.command.MarkCommand;
import eureka.command.TodoCommand;
import eureka.command.UnmarkCommand;

/**
 * Parses raw user input strings into executable {@link Command} objects.
 * <p>
 * Supported commands and formats:
 * <ul>
 *   <li><code>bye</code> – exits the application</li>
 *   <li><code>list</code> – lists all tasks</li>
 *   <li><code>mark &lt;index&gt;</code> – marks the 1-based task at {@code index} as done</li>
 *   <li><code>unmark &lt;index&gt;</code> – marks the 1-based task at {@code index} as not done</li>
 *   <li><code>delete &lt;index&gt;</code> – deletes the 1-based task at {@code index}</li>
 *   <li><code>todo &lt;task name&gt;</code> – adds a todo</li>
 *   <li><code>deadline &lt;task name&gt; /by "yyyy-MM-dd HH:mm"</code> – adds a deadline</li>
 *   <li><code>event &lt;task name&gt; /from "yyyy-MM-dd HH:mm" /to "yyyy-MM-dd HH:mm"</code> – adds an event</li>
 *   <li><code>find &lt;keyword&gt;</code> – finds tasks containing {@code keyword}</li>
 * </ul>
 * Invalid or malformed inputs result in an {@link InvalidCommand}.
 */
public class Parser {

    /**
     * Parses a full command line into a concrete {@link Command}.
     * <p>
     * This method performs simple prefix checks for each command and,
     * where applicable, uses regular expressions to extract arguments.
     * It also validates date/time strings via {@link DateTimeConverter}.
     *
     * @param fullCommand the raw user input line (e.g., {@code "deadline homework /by 2025-09-01 18:00"})
     * @return a {@link Command} instance corresponding to the input; never {@code null}.
     *         Returns {@link InvalidCommand} for unknown or malformed commands.
     */
    public static Command parse(String fullCommand) {
        switch (fullCommand) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        default:
            if (fullCommand.startsWith("mark")) {
                String[] parts = fullCommand.split(" ");
                if (parts.length > 1) {
                    int index = Integer.parseInt(parts[1]) - 1;
                    return new MarkCommand(index);
                }
            } else if (fullCommand.startsWith("unmark")) {
                String[] parts = fullCommand.split(" ");
                if (parts.length > 1) {
                    int index = Integer.parseInt(parts[1]) - 1;
                    return new UnmarkCommand(index);
                }
            } else if (fullCommand.startsWith("delete")) {
                String[] parts = fullCommand.split(" ");
                if (parts.length > 1) {
                    int index = Integer.parseInt(parts[1]) - 1;
                    return new DeleteCommand(index);
                }
            } else if (fullCommand.startsWith("todo")) {
                Pattern p = Pattern.compile("^\\s*(?<cmd>\\w+)\\s+(?<taskName>.+)\\s*$");
                Matcher m = p.matcher(fullCommand);
                if (!m.matches()) {
                    printTodoErrorMessage();
                    break;
                }
                String taskName = m.group("taskName");
                return new TodoCommand(taskName);
            } else if (fullCommand.startsWith("deadline")) {
                Pattern p = Pattern.compile("^\\s*(?<cmd>\\w+)\\s+(?<taskName>.+?)\\s+/by\\s+(?<by>.+)\\s*$");
                Matcher m = p.matcher(fullCommand);
                if (!m.matches()) {
                    printDeadlineErrorMessage();
                    break;
                }
                String taskName = m.group("taskName");

                try {
                    LocalDateTime by = DateTimeConverter.dateTimeConverter(m.group("by"));
                    return new DeadlineCommand(taskName, by);

                } catch (IllegalArgumentException e) {
                    System.out.println("_____________________________");
                    System.out.println(e.getMessage());
                    System.out.println("_____________________________");
                    break;
                }

            } else if (fullCommand.startsWith("event")) {
                Pattern p = Pattern.compile(
                        "^\\s*(?<cmd>\\w+)\\s+(?<taskName>.+?)\\s+/from\\s+(?<from>.+?)\\s+/to\\s+(?<to>.+)\\s*$"
                );
                Matcher m = p.matcher(fullCommand);
                if (!m.matches()) {
                    printEventErrorMessage();
                    break;
                }
                String taskName = m.group("taskName");

                try {
                    LocalDateTime from = DateTimeConverter.dateTimeConverter(m.group("from"));
                    LocalDateTime to = DateTimeConverter.dateTimeConverter(m.group("to"));

                    return new EventCommand(taskName, from, to);

                } catch (IllegalArgumentException e) {
                    System.out.println("_____________________________");
                    System.out.println(e.getMessage());
                    System.out.println("_____________________________");
                    break;
                }
            } else if (fullCommand.startsWith("find")) {
                String[] parts = fullCommand.split(" ");
                if (parts.length > 1) {
                    String keyword = parts[1];
                    return new FindCommand(keyword);
                }
            } else {
                return new InvalidCommand();
            }
        }
        return new InvalidCommand();
    }

    private static void printTodoErrorMessage() {
        System.out.println("_____________________________");
        System.out.println("Invalid todo format. Task Name cannot be empty. Eg: todo homework");
        System.out.println("_____________________________");
    }

    private static void printEventErrorMessage() {
        System.out.println("_____________________________");
        System.out.println(
                "Invalid event format. Eg: event orientation /from \"yyyy-MM-dd HH:mm\" /to \"yyyy-MM-dd HH:mm\""
        );
        System.out.println("_____________________________");
    }

    private static void printDeadlineErrorMessage() {
        System.out.println("_____________________________");
        System.out.println("Invalid deadline format. Eg: deadline homework /by \"yyyy-MM-dd HH:mm\"");
        System.out.println("_____________________________");
    }


}
