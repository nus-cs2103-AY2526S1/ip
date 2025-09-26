package winnie.exception;

/**
 * Exception thrown when a required time component is missing.
 */
public class MissingTimeException extends WinnieException {
    public MissingTimeException(String taskType, String missingPart) {
        super("Missing " + missingPart + " for " + taskType + ". " + getFormatHelp(taskType));
    }

    private static String getFormatHelp(String taskType) {
        switch (taskType) {
        case "deadline":
            return "Use: deadline <description> /by <time>";
        case "event":
            return "Use: event <description> /from <start> /to <end>";
        default:
            return "";
        }
    }
}
