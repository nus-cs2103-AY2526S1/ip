package handler;

import hachi.CommandCode;
import hachi.Event;
import hachi.TaskList;
import hachi.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the {@link CommandCode#EVENT} command.
 *
 * <p>This handler expects user input of the form:
 * <pre>
 *     event &lt;description&gt; /from &lt;dd/MM/yyyy HHmm&gt; /to &lt;dd/MM/yyyy HHmm&gt;
 * </pre>
 * Example:
 * <pre>
 *     event project meeting /from 21/09/2025 1400 /to 21/09/2025 1600
 * </pre>
 *
 * <p>If the input matches this format, a new {@link Event} task is created and
 * added to the {@link TaskList}. Otherwise, a failure message is returned.</p>
 */
public class EventHandler implements Manager {
    private final String input;
    private final Ui ui;

    /**
     * Constructs an {@code EventHandler}.
     *
     * @param input the raw user input string
     * @param ui    the UI utility for success/failure messages
     */
    public EventHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    /**
     * Parses the input, extracts the event description, start, and end date-times,
     * and adds a new {@link Event} task to the given {@link TaskList}.
     *
     * @param tasks the task list to operate on
     * @return a success message if parsing and addition succeed,
     *         or a failure message otherwise
     */
    @Override
    public String execute(TaskList tasks) {
        Pattern pattern = Pattern.compile(
                "^event\\s+(.*?)\\s+/from\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})\\s+/to\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})$",
                Pattern.CASE_INSENSITIVE
        );
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String task = matcher.group(1);
            String fromStr = matcher.group(2);
            String toStr = matcher.group(3);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime from = LocalDateTime.parse(fromStr, formatter);
            LocalDateTime to = LocalDateTime.parse(toStr, formatter);

            tasks.getTasks().add(new Event(task, from, to));
            return ui.success(CommandCode.EVENT);
        } else {
            return Ui.failure(CommandCode.EVENT);
        }
    }
}


