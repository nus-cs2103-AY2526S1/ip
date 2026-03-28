package handler;

import hachi.CommandCode;
import hachi.Deadline;
import hachi.TaskList;
import hachi.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the {@link CommandCode#DEADLINE} command.
 *
 * <p>This handler parses input of the form:
 * <pre>
 *     deadline &lt;description&gt; /by &lt;dd/MM/yyyy HHmm&gt;
 * </pre>
 * Example:
 * <pre>
 *     deadline submit report /by 21/09/2025 1800
 * </pre>
 *
 * <p>If the input is valid, a new {@link Deadline} task is created and added to
 * the provided {@link TaskList}. Otherwise, a failure message is returned.</p>
 */
public class DeadlineHandler implements Manager {

    private final String input;
    private final Ui ui;

    /**
     * Constructs a {@code DeadlineHandler}.
     *
     * @param input the raw user input string
     * @param ui    the UI utility for success/failure messages
     */
    public DeadlineHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    /**
     * Parses the input, extracts the deadline task description and due date,
     * and adds it to the task list.
     *
     * @param tasks the task list to add the deadline to
     * @return a success message if parsing and addition succeed,
     *         or a failure message otherwise
     */
    @Override
    public String execute(TaskList tasks) {
        Pattern pattern = Pattern.compile(
                "^deadline\\s+(.*?)\\s+/by\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})$",
                Pattern.CASE_INSENSITIVE
        );
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String task = matcher.group(1);
            String by = matcher.group(2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(by, formatter);

            tasks.getTasks().add(new Deadline(task, dateTime));
            return ui.success(CommandCode.DEADLINE);

        } else {
            return Ui.failure(CommandCode.DEADLINE);
        }
    }
}

