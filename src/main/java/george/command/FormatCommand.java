package george.command;

import george.exceptions.GeorgeException;
import george.task.TaskManager;

/**
 * Represents a command to display supported date/time formats.
 * Shows examples of valid date formats that can be used with the application.
 */
public class FormatCommand extends Command {
    /**
     * Constructs a FormatCommand.
     */
    public FormatCommand() {
        // No parameters needed for format command
    }

    @Override
    public String execute(TaskManager manager) throws GeorgeException {
        return getFormatMessage();
    }

    @Override
    public String getCommandWord() {
        return "format";
    }

    /**
     * Returns the date format help message based on actual supported formats.
     *
     * @return A formatted string with exact date format examples
     */
    private String getFormatMessage() {
        return "Supported Date/Time Formats:\n\n"
                + "**Date Formats:**\n"
                + "yyyy-MM-dd (e.g. 2024-12-25)\n"
                + "yyyy/MM/dd (e.g. 2024/12/25)\n\n"
                + "**DateTime Formats:**\n"
                + "yyyy-MM-dd HHmm (e.g. 2024-12-25 1430)\n"
                + "yyyy-MM-dd HH:mm (e.g. 2024-12-25 14:30)\n"
                + "yyyy/MM/dd HHmm (e.g. 2024/12/25 1430)\n"
                + "yyyy/MM/dd HH:mm (e.g. 2024/12/25 14:30)\n\n"
                + "**Note:** Dates without time will default to start of day (00:00)";
    }
}